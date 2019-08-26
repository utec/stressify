package edu.utec.tools.fiveminutestressor.mode;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.JTextArea;

import edu.utec.common.performance.MeasureUtil;
import edu.utec.tools.fiveminutestressor.common.ScriptImports;
import edu.utec.tools.fiveminutestressor.steps.CSVReaderStep;
import edu.utec.tools.fiveminutestressor.steps.ReportStep;
import edu.utec.tools.fiveminutestressor.steps.StressorWithClientStep;

public class SimpleGraphicStressor {

  private JTextArea jTextAreaLog;

  public SimpleGraphicStressor(JTextArea jTextAreaLog) {
    super();
    this.jTextAreaLog = jTextAreaLog;
  }

  public void log(String message) {
    this.jTextAreaLog.append(message + "\n");
  }

  public void clearLog() {
    this.jTextAreaLog.setText("");
  }

  @SuppressWarnings("unchecked")
  public void perform(String csvDataPath, String reportPath, String reportColumns, String mode,
          String threads, String url, String method, String body,
          ArrayList<HashMap<String, String>> headers, String assertScript) throws Exception {

    clearLog();
    long before = new Date().getTime();

    log("Stress starting...");

    if (csvDataPath == null || csvDataPath.equals("")) {
      log("Data csv file is required. Go to data section and configure it!");
      return;
    }

    if (reportPath == null || reportPath.equals("") || reportPath.equals(File.separator)) {
      log("Report path file is required. Go to report section and configure it!");
      return;
    }

    if (reportColumns == null || reportColumns.equals("")) {
      log("Columns of report are required. Go to report section and configure it!");
      return;
    }

    if (mode == null || mode.equals("")) {
      log("Stress mode is required.");
      return;
    }

    if (threads == null || threads.equals("")) {
      log("Virtual users number is required");
      return;
    }

    log("Parameters:");
    log("data file:" + csvDataPath);
    log("report file:" + reportPath);
    log("report columns:" + reportColumns);
    log("mode:" + mode);
    log("virtual users:" + threads);

    CSVReaderStep csvReaderStep = new CSVReaderStep();
    List<?> csvRecords = (List<?>) csvReaderStep.execute(new String[] { csvDataPath });
    System.out.println("csvRecords:" + csvRecords.size());

    assertScript = String.format("%s%n%n%s", ScriptImports.getDefaultImports(), assertScript);
    
    StressorWithClientStep stressorWithClientStep = new StressorWithClientStep();
    List<List<String>> dataStress = (List<List<String>>) stressorWithClientStep.execute(
            new Object[] { method, url, body, headers, assertScript, mode, threads, csvRecords });

    ReportStep reportStep = new ReportStep();
    List<String> reportColumnValues = Arrays.asList(reportColumns.split(","));
    Object result = reportStep.execute(new Object[] { dataStress, reportColumnValues, reportPath });

    long after = new Date().getTime();

    log("Stress completed.");
    log("\tStatus:" + result);
    log("\tElapsed time:" + MeasureUtil.convertMillisMessage(after - before));

  }
}
