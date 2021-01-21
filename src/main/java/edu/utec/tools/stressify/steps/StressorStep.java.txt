package edu.utec.tools.fiveminutestressor.steps;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.commons.csv.CSVRecord;

import edu.utec.common.file.FileUtil;
import edu.utec.tools.fiveminutestressor.common.ScriptImports;
import edu.utec.tools.fiveminutestressor.core.BaseScriptExecutor;
import edu.utec.tools.fiveminutestressor.core.ExecutableStep;
import edu.utec.tools.fiveminutestressor.executor.SimpleContinuousScriptExecutor;
import edu.utec.tools.fiveminutestressor.executor.SimpleParallelScriptExecutor;
import groovy.lang.Binding;

public class StressorStep implements ExecutableStep {

  @SuppressWarnings("unchecked")
  public Object execute(Object[] args) throws Exception {

    String scriptGroovyFilePath = "" + args[0];

    if (scriptGroovyFilePath == null || !new File(scriptGroovyFilePath).exists()) {
      throw new Exception(
              "Script file was not found or file path is wrong:" + scriptGroovyFilePath);
    }

    String initialScript = FileUtil.readFileToString(scriptGroovyFilePath, Charset.forName("UTF-8"));
    String script = String.format("%s%n%n%s", ScriptImports.getDefaultImports(), initialScript);

    List<CSVRecord> csvRecords = (List<CSVRecord>) args[1];
    String mode = "" + args[2];

    int threads = 1;
    if (args.length > 3) {
      threads = Integer.parseInt("" + args[3]);
    }

    List<BaseScriptExecutor> executors = new ArrayList<BaseScriptExecutor>();

    CSVRecord header = (CSVRecord) csvRecords.get(0);

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

    for (int row = 1; row < csvRecords.size(); row++) {

      Binding binding = new Binding();

      CSVRecord csvRecord = csvRecords.get(row);
      for (int col = 0; col < header.size(); col++) {
        binding.setVariable(header.get(col), csvRecord.get(col));
      }

      for (int threadIteration = 0; threadIteration < iterations; threadIteration++) {

        if (mode.equals("parallel")) {
          SimpleParallelScriptExecutor executor = new SimpleParallelScriptExecutor();
          executor.setBinding(binding);
          executor.setScript(script);
          executor.setCountDownLatch(countDownLatch);
          executors.add(executor);
          executor.start();
        } else if (mode.equals("continuous")) {
          SimpleContinuousScriptExecutor executor = new SimpleContinuousScriptExecutor();
          executor.setBinding(binding);
          executor.setScript(script);
          executors.add(executor);
          executor.start();
        }
      }

      if (row == csvRecords.size() - 1) {
        for (int extraIthreadIteration = 0; extraIthreadIteration < extraIterations; extraIthreadIteration++) {
          if (mode.equals("parallel")) {
            SimpleParallelScriptExecutor executor = new SimpleParallelScriptExecutor();
            executor.setBinding(binding);
            executor.setScript(script);
            executor.setCountDownLatch(countDownLatch);
            executors.add(executor);
            executor.start();
          } else if (mode.equals("continuous")) {
            SimpleContinuousScriptExecutor executor = new SimpleContinuousScriptExecutor();
            executor.setBinding(binding);
            executor.setScript(script);
            executors.add(executor);
            executor.start();
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
