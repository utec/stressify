package edu.utec.tools.fiveminutestressor.steps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.commons.csv.CSVRecord;

import edu.utec.common.variable.VariableUtil;
import edu.utec.tools.fiveminutestressor.core.BaseScriptExecutor;
import edu.utec.tools.fiveminutestressor.core.ExecutableStep;
import edu.utec.tools.fiveminutestressor.rest.ContinuosRestClient;
import edu.utec.tools.fiveminutestressor.rest.ParrallelRestClient;

public class StressorWithClientStep implements ExecutableStep {

  @SuppressWarnings("unchecked")
  public Object execute(Object[] args) throws Exception {

    String method = "" + args[0];
    String url = "" + args[1];
    String body = "" + args[2];
    ArrayList<HashMap<String, String>> headers = (ArrayList<HashMap<String, String>>) args[3];
    String assertScript = "" + args[4];
    String mode = "" + args[5];
    String threadsValue = "" + args[6];
    List<CSVRecord> csvRecords = (List<CSVRecord>) args[7];

    int threads = 1;
    if (args.length > 3) {
      threads = Integer.parseInt(threadsValue);
    }

    List<BaseScriptExecutor> executors = new ArrayList<BaseScriptExecutor>();

    CountDownLatch countDownLatch = null;

    int iterations = 1;
    int extraIterations = 0;
    int onlyRecordsCsvSize = csvRecords.size() - 1;// exclude header

    if (threads > onlyRecordsCsvSize) {
      iterations = threads / (onlyRecordsCsvSize);
      extraIterations = threads % (onlyRecordsCsvSize);
      countDownLatch = new CountDownLatch(threads);
    } else {
      countDownLatch = new CountDownLatch(onlyRecordsCsvSize);
    }

    CSVRecord header = (CSVRecord) csvRecords.get(0);

    for (int row = 1; row < csvRecords.size(); row++) {

      CSVRecord csvRecord = csvRecords.get(row);
      HashMap<String, String> variables = new HashMap<String, String>();
      for (int col = 0; col < header.size(); col++) {
        variables.put(header.get(col), csvRecord.get(col));
      }

      for (int threadIteration = 0; threadIteration < iterations; threadIteration++) {

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

        } else if (mode.equals("continuous")) {
          ContinuosRestClient client = new ContinuosRestClient();
          executors.add(client);
          client.performRequest(method, VariableUtil.replaceVariablesInString(url, variables),
                  VariableUtil.replaceVariablesInString(body, variables),
                  VariableUtil.replaceInHeaderValues(headers, variables),
                  VariableUtil.replaceVariablesInString(assertScript, variables));
        }
      }

      if (row == csvRecords.size() - 1) {
        for (int extraIthreadIteration = 0; extraIthreadIteration < extraIterations; extraIthreadIteration++) {
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
          } else if (mode.equals("continuous")) {
            ContinuosRestClient client = new ContinuosRestClient();
            executors.add(client);
            client.performRequest(method, VariableUtil.replaceVariablesInString(url, variables),
                    VariableUtil.replaceVariablesInString(body, variables),
                    VariableUtil.replaceInHeaderValues(headers, variables),
                    VariableUtil.replaceVariablesInString(assertScript, variables));
          }
        }
      }
    }

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
