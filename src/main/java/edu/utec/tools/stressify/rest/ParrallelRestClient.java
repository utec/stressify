package edu.utec.tools.stressify.rest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import edu.utec.tools.stressify.common.AssertsHelper;
import edu.utec.tools.stressify.common.SmartHttpClient;
import edu.utec.tools.stressify.common.TimeHelper;
import edu.utec.tools.stressify.core.BaseScriptExecutor;
import groovy.lang.Binding;

public class ParrallelRestClient extends Thread implements BaseScriptExecutor {

  private final Logger logger = LogManager.getLogger(this.getClass());

  private Binding binding;
  private CountDownLatch countDownLatch;
  private String method, url, assertScript, body;
  private HashMap<String, String> headers;

  private SmartHttpClient smartHttpClient = new SmartHttpClient();
  private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd-HH:mm:ss:SSS");
  private HashMap<String, Object> response = null;

  @Override
  public void run() {
    performRequest();
    this.countDownLatch.countDown();
  }

  public void performRequest() {

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
      AssertsHelper.evaluateSimpleAssert((String) response.get("responseBody"), assertScript, getBinding());
      response.put("asserts", true);
    } catch (Exception e) {
      logger.error("Failed to execute asserts on http response with id: " + id, e);
      logger.error("http response with id: \n" + (String) response.get("responseBody"), e);
      response.put("asserts", false);
      response.put("log", "Assert error:" + e.getMessage());
      return;
    }
  }

  public void setMethod(String method) {
    this.method = method;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public void setAssertScript(String assertScript) {
    this.assertScript = assertScript;
  }

  public void setHeaders(HashMap<String, String> headers) {
    this.headers = headers;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public void setCountDownLatch(CountDownLatch countDownLatch) {
    this.countDownLatch = countDownLatch;
  }

  @Override
  public Object getResponse() {
    return this.response;
  }

  public Binding getBinding() {
    return binding;
  }

  public void setBinding(Binding binding) {
    this.binding = binding;
  }

}
