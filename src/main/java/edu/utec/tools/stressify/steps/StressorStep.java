package edu.utec.tools.stressify.steps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import org.apache.commons.csv.CSVRecord;
import edu.utec.common.variable.VariableUtil;
import edu.utec.tools.stressify.core.BaseScriptExecutor;
import edu.utec.tools.stressify.core.ExecutableStep;
import edu.utec.tools.stressify.rest.ContinuosRestClient;
import edu.utec.tools.stressify.rest.ParrallelRestClient;
import groovy.lang.Binding;

public class StressorStep implements ExecutableStep {

  @SuppressWarnings("unchecked")
  public Object execute(HashMap<String, Object> parameters) throws Exception {

    String method = (String) parameters.get("method");
    String url = (String) parameters.get("url");
    String body = (String) parameters.get("body");
    HashMap<String, String> headers = (HashMap<String, String>) parameters.get("headers");

    String assertScript = null;
    if (parameters.get("assertScript") != null) {
      assertScript = (String) parameters.get("assertScript");
    }
    String mode = (String) parameters.get("mode");
    String threadsValue = (String) parameters.get("threads");
    List<?> csvRecords = null;
    // CSVRecord csvHeader = null;
    if (parameters.get("csvRecords") != null && parameters.get("csvRecords") instanceof List) {
      csvRecords = (List<CSVRecord>) parameters.get("csvRecords");
      if (csvRecords.size() == 1) {
        // csvHeader = (CSVRecord) csvRecords.get(0);
        throw new Exception("Csv has only one row");
      }
    }

    int threads = 1;
    if (threadsValue != null) {
      threads = Integer.parseInt(threadsValue);
    } else {
      throw new Exception("Internal error. An integer number is required in virtual users field.");
    }

//    List<BaseScriptExecutor> executors = new ArrayList<BaseScriptExecutor>();

    CountDownLatch countDownLatch = null;

    int iterations = 1;
//    int extraIterations = 0;
    int onlyRecordsCsvSize = (csvRecords == null) ? 0 : csvRecords.size() - 1;// exclude header
//    List<HashMap<String, Object>> dataStress = null;

    if (onlyRecordsCsvSize == 0) {
      iterations = threads;
      countDownLatch = new CountDownLatch(threads);
      return stressWithoutVariables(iterations, countDownLatch, mode, method, assertScript, url,
          body, headers);
    } else if (threads > onlyRecordsCsvSize) {
      iterations = threads;
      countDownLatch = new CountDownLatch(threads);
      return stressWithVariablesV2(iterations, countDownLatch, csvRecords, mode, method,
          assertScript, url, body, headers);
    } else {
      iterations = onlyRecordsCsvSize;
      countDownLatch = new CountDownLatch(onlyRecordsCsvSize);
      return stressWithVariables(iterations, countDownLatch, csvRecords, mode, method, assertScript,
          url, body, headers);
    }
  }

  private List<HashMap<String, Object>> stressWithVariablesV2(int iterations,
      CountDownLatch countDownLatch, List<?> csvRecords, String mode, String method,
      String assertScript, String url, String body, HashMap<String, String> headers)
      throws InterruptedException {

    List<BaseScriptExecutor> executors = new ArrayList<BaseScriptExecutor>();
    CSVRecord header = (CSVRecord) csvRecords.get(0);

    int row = 1;
    for (int a = 0; a < iterations; a++) {

      if (a == 0 || a % (csvRecords.size() - 1) == 0) {
        row = 1;
      }

      CSVRecord csvRecord = (CSVRecord) csvRecords.get(row);
      HashMap<String, String> variables = new HashMap<String, String>();
      for (int col = 0; col < header.size(); col++) {
        variables.put(header.get(col), csvRecord.get(col));
      }

      Binding binding = new Binding();
      for (int col = 0; col < header.size(); col++) {
        binding.setVariable(header.get(col), csvRecord.get(col));
      }

      String evaluatedUrl = VariableUtil.replaceVariablesInString(url, variables);
      String evaluatedBody = VariableUtil.replaceVariablesInString(body, variables);
      HashMap<String, String> evaluatedHeaders =
          VariableUtil.replaceInHeaderValues(headers, variables);
      String evaluatedAssertScript = VariableUtil.replaceVariablesInString(assertScript, variables);

      if (mode.equals("parallel")) {

        ParrallelRestClient client = new ParrallelRestClient();
        client.setBinding(binding);
        client.setAssertScript(evaluatedAssertScript);
        client.setMethod(method);
        client.setUrl(evaluatedUrl);
        client.setBody(evaluatedBody);
        client.setHeaders(evaluatedHeaders);
        client.setCountDownLatch(countDownLatch);
        executors.add(client);
        client.start();

      } else if (mode.equals("sequential")) {
        ContinuosRestClient client = new ContinuosRestClient();
        executors.add(client);
        client.performRequest(method, evaluatedUrl, evaluatedBody, evaluatedHeaders,
            evaluatedAssertScript, binding);
      }
      row++;
    }

    if (mode.equals("parallel")) {
      countDownLatch.await();
    }

    List<HashMap<String, Object>> dataStress = new ArrayList<HashMap<String, Object>>();

    for (BaseScriptExecutor scriptExecutor : executors) {
      dataStress.add((HashMap<String, Object>) scriptExecutor.getResponse());
    }

    return dataStress;

  }

  private List<HashMap<String, Object>> stressWithVariables(int iterations,
      CountDownLatch countDownLatch, List<?> csvRecords, String mode, String method,
      String assertScript, String url, String body, HashMap<String, String> headers)
      throws InterruptedException {

    List<BaseScriptExecutor> executors = new ArrayList<BaseScriptExecutor>();
    CSVRecord header = (CSVRecord) csvRecords.get(0);

    for (int row = 1; row < csvRecords.size(); row++) {

      CSVRecord csvRecord = (CSVRecord) csvRecords.get(row);
      HashMap<String, String> variables = new HashMap<String, String>();
      for (int col = 0; col < header.size(); col++) {
        variables.put(header.get(col), csvRecord.get(col));
      }

      Binding binding = new Binding();
      for (int col = 0; col < header.size(); col++) {
        binding.setVariable(header.get(col), csvRecord.get(col));
      }

      String evaluatedUrl = VariableUtil.replaceVariablesInString(url, variables);
      String evaluatedBody = VariableUtil.replaceVariablesInString(body, variables);
      HashMap<String, String> evaluatedHeaders =
          VariableUtil.replaceInHeaderValues(headers, variables);
      String evaluatedAssertScript = VariableUtil.replaceVariablesInString(assertScript, variables);

      if (mode.equals("parallel")) {

        ParrallelRestClient client = new ParrallelRestClient();
        client.setBinding(binding);
        client.setAssertScript(evaluatedAssertScript);
        client.setMethod(method);
        client.setUrl(evaluatedUrl);
        client.setBody(evaluatedBody);
        client.setHeaders(evaluatedHeaders);
        client.setCountDownLatch(countDownLatch);
        executors.add(client);
        client.start();

      } else if (mode.equals("sequential")) {
        ContinuosRestClient client = new ContinuosRestClient();
        executors.add(client);
        client.performRequest(method, evaluatedUrl, evaluatedBody, evaluatedHeaders,
            evaluatedAssertScript, binding);
      }
    }

    if (mode.equals("parallel")) {
      countDownLatch.await();
    }

    List<HashMap<String, Object>> dataStress = new ArrayList<HashMap<String, Object>>();

    for (BaseScriptExecutor scriptExecutor : executors) {
      dataStress.add((HashMap<String, Object>) scriptExecutor.getResponse());
    }

    return dataStress;

  }

  private List<HashMap<String, Object>> stressWithoutVariables(int iterations,
      CountDownLatch countDownLatch, String mode, String method, String assertScript, String url,
      String body, HashMap<String, String> headers) throws InterruptedException {

    List<BaseScriptExecutor> executors = new ArrayList<BaseScriptExecutor>();

    for (int threadIteration = 0; threadIteration < iterations; threadIteration++) {

      if (mode.equals("parallel")) {

        ParrallelRestClient client = new ParrallelRestClient();
        client.setAssertScript(VariableUtil.replaceVariablesInString(assertScript, null));
        client.setMethod(method);
        client.setUrl(VariableUtil.replaceVariablesInString(url, null));
        client.setBody(VariableUtil.replaceVariablesInString(body, null));
        client.setHeaders(VariableUtil.replaceInHeaderValues(headers, null));
        client.setCountDownLatch(countDownLatch);
        executors.add(client);
        client.start();

      } else if (mode.equals("sequential")) {
        ContinuosRestClient client = new ContinuosRestClient();
        executors.add(client);
        client.performRequest(method, VariableUtil.replaceVariablesInString(url, null),
            VariableUtil.replaceVariablesInString(body, null),
            VariableUtil.replaceInHeaderValues(headers, null),
            VariableUtil.replaceVariablesInString(assertScript, null), null);
      }
    }

    if (mode.equals("parallel")) {
      countDownLatch.await();
    }

    List<HashMap<String, Object>> dataStress = new ArrayList<HashMap<String, Object>>();

    for (BaseScriptExecutor scriptExecutor : executors) {
      dataStress.add((HashMap<String, Object>) scriptExecutor.getResponse());
    }

    return dataStress;
  }



}
