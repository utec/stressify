package edu.utec.tools.stressify.steps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.commons.csv.CSVRecord;

import edu.utec.common.variable.VariableUtil;
import edu.utec.tools.stressify.common.VariablesHelper;
import edu.utec.tools.stressify.core.BaseScriptExecutor;
import edu.utec.tools.stressify.core.ExecutableStep;
import edu.utec.tools.stressify.rest.ContinuosRestClient;
import edu.utec.tools.stressify.rest.ParrallelRestClient;

public class StressorWithClientStep implements ExecutableStep {

  @SuppressWarnings("unchecked")
  public Object execute(HashMap<String, Object> parameters) throws Exception {

    String method = (String) parameters.get("method");
    String url = (String) parameters.get("url");
    String body = (String) parameters.get("body");
    ArrayList<HashMap<String, String>> headers =
        (ArrayList<HashMap<String, String>>) parameters.get("headers");
    
    String assertScript = null;
    if(parameters.get("assertScript")!=null) {
      assertScript = (String) parameters.get("assertScript");
    }
    String mode = (String) parameters.get("mode");
    String threadsValue = (String) parameters.get("threads");
    List<?> csvRecords = null;
    CSVRecord csvHeader = null;
    if (parameters.get("csvRecords") != null && parameters.get("csvRecords") instanceof List) {
      csvRecords = (List<CSVRecord>) parameters.get("csvRecords");
      if (csvRecords.size() > 1) {
        csvHeader = (CSVRecord) csvRecords.get(0);
      }
    }

    int threads = 1;
    if (threadsValue != null) {
      threads = Integer.parseInt(threadsValue);
    } else {
      throw new Exception("Internal error. An integer number is required in virtual users field.");
    }

    List<BaseScriptExecutor> executors = new ArrayList<BaseScriptExecutor>();

    CountDownLatch countDownLatch = null;

    int iterations = 1;
    int extraIterations = 0;
    int onlyRecordsCsvSize = (csvRecords == null) ? 0 : csvRecords.size() - 1;// exclude header

    if (onlyRecordsCsvSize == 0) {
      iterations = threads;
    } else if (threads > onlyRecordsCsvSize) {
      iterations = threads / (onlyRecordsCsvSize);
      extraIterations = threads % (onlyRecordsCsvSize);
      countDownLatch = new CountDownLatch(threads);
    } else {
      countDownLatch = new CountDownLatch(onlyRecordsCsvSize);
    }

    // for (int row = 1; row < csvRecords.size(); row++) {

    for (int threadIteration = 0; threadIteration < iterations; threadIteration++) {

      HashMap<String, String> variables = null;
      if (csvRecords != null) {
        variables =
            VariablesHelper.csvRowToVariables(csvHeader, (CSVRecord)csvRecords.get(threadIteration + 1));
      }

      if (mode.equals("parallel")) {

        ParrallelRestClient client = new ParrallelRestClient();
        client.setAssertScript(VariableUtil.replaceVariablesInString(assertScript, variables));
        client.setMethod(method);
        client.setUrl(VariableUtil.replaceVariablesInString(url, variables));
        client.setBody(VariableUtil.replaceVariablesInString(body, variables));
        client.setHeaders(VariableUtil.replaceInHeaderValues(headers, variables));
        client.setCountDownLatch(countDownLatch);
        executors.add(client);
        client.start();

      } else if (mode.equals("sequential")) {
        ContinuosRestClient client = new ContinuosRestClient();
        executors.add(client);
        client.performRequest(method, VariableUtil.replaceVariablesInString(url, variables),
            VariableUtil.replaceVariablesInString(body, variables),
            VariableUtil.replaceInHeaderValues(headers, variables),
            VariableUtil.replaceVariablesInString(assertScript, variables));
      }
    }

    // if (row == csvRecords.size() - 1) {
    // for (int extraIthreadIteration =
    // 0; extraIthreadIteration < extraIterations; extraIthreadIteration++) {
    // if (mode.equals("parallel")) {
    // ParrallelRestClient client = new ParrallelRestClient();
    // client.setAssertScript(VariableUtil.replaceVariablesInString(assertScript, variables));
    // client.setMethod(method);
    // client.setUrl(VariableUtil.replaceVariablesInString(url, variables));
    // client.setBody(VariableUtil.replaceVariablesInString(body, variables));
    // client.setHeaders(VariableUtil.replaceInHeaderValues(headers, variables));
    // client.setCountDownLatch(countDownLatch);
    // executors.add(client);
    // client.start();
    // } else if (mode.equals("continuous")) {
    // ContinuosRestClient client = new ContinuosRestClient();
    // executors.add(client);
    // client.performRequest(method, VariableUtil.replaceVariablesInString(url, variables),
    // VariableUtil.replaceVariablesInString(body, variables),
    // VariableUtil.replaceInHeaderValues(headers, variables),
    // VariableUtil.replaceVariablesInString(assertScript, variables));
    // }
    // }
    // }
    // }

    if (mode.equals("parallel")) {
      countDownLatch.await();
    }

    List<List<String>> dataStress = new ArrayList<List<String>>();

    for (BaseScriptExecutor scriptExecutor : executors) {
      String[] output = (String[]) scriptExecutor.getOutput();
      dataStress.add(Arrays.asList(output));
    }

    return dataStress;
  }



}
