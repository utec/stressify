package edu.utec.tools.stressify.rest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.ComparisonFailure;
import edu.utec.tools.stressify.common.AssertsHelper;
import edu.utec.tools.stressify.common.SmartHttpClient;
import edu.utec.tools.stressify.common.TimeHelper;
import edu.utec.tools.stressify.core.BaseScriptExecutor;
import groovy.lang.Binding;

public class ContinuosRestClient implements BaseScriptExecutor {

  private final Logger logger = LogManager.getLogger(this.getClass());

  private SmartHttpClient smartHttpClient = new SmartHttpClient();
  private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd-HH:mm:ss:SSS");
  private HashMap<String, Object> response = null;

  public void performRequest(String method, String url, String body,
      HashMap<String, String> headers, String assertScript, Binding binding) {

    String id = UUID.randomUUID().toString();
    Date dateOnError = new Date();
    try {
      response = smartHttpClient.performRequest(method, url, body, headers);
      logger.info("http invocation was completed. Id: " + id);
      logger.info(id + " Request values : "
          + String.format("Url:%s Headers:%s Method:%s Body:%s", url, headers, method, body));
      logger.info(id + " Response values : " + response);
    } catch (Exception e) {
      logger.error("Failed to execute http invocation with id: " + id, e);
      response = new HashMap<String, Object>();
      response.put("startDate", dateFormat.format(TimeHelper.millisToDate(dateOnError.getTime())));
      response.put("log", "Connection error:" + e.getMessage());
      return;
    }

    response.put("id", id);
    response.put("startDate",
        dateFormat.format(TimeHelper.millisToDate((Long) response.get("startMillisDate"))));
    response.put("endDate",
        dateFormat.format(TimeHelper.millisToDate((Long) response.get("endMillisDate"))));

    try {
      AssertsHelper.evaluateSimpleAssert((String) response.get("responseBody"), assertScript, binding);
      response.put("asserts", true);
    } catch (ComparisonFailure e) {
      logger.error("Failed to execute asserts on http response with id: " + id, e);
      logger.error("http response with id: \n" + (String) response.get("responseBody"), e);
      response.put("asserts", false);
      response.put("log", "Assert error:" + e.getMessage());
      return;
    } catch (AssertionError e) {
      logger.error("Failed to execute asserts on http response with id: " + id, e);
      logger.error("http response with id: \n" + (String) response.get("responseBody"), e);
      response.put("asserts", false);
      response.put("log", "Assert error:" + e.getMessage());
      return;
    } catch (Exception e) {
      logger.error("Failed to execute asserts on http response with id: " + id, e);
      logger.error("http response with id: \n" + (String) response.get("responseBody"), e);
      response.put("asserts", false);
      response.put("log", "Assert error:" + e.getMessage());
      return;
    }
  }

  @Override
  public HashMap<String, Object> getResponse() {
    return response;
  }

}
