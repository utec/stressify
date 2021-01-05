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
    log("");
    if (csvDataPath == null || csvDataPath.equals("")) {
      log("Data csv file is not configured. Stresss will not use variables."
          + " Go to data section and configure it if you want to use variables!");
    }
    
    if (assertScript == null || assertScript.equals("")) {
      log("assert script is not configured.");
      assertScript = "return true";
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
    HashMap<String, Object> csvStepParameters = new HashMap<String, Object>();
    csvStepParameters.put("csvDataPath", csvDataPath);
    Object csvRecords =  csvReaderStep.execute(csvStepParameters);

    assertScript = String.format("%s%n%n%s", ScriptImports.getDefaultImports(), assertScript);

    StressorWithClientStep stressorWithClientStep = new StressorWithClientStep();
    HashMap<String, Object> stressorStepParameters = new HashMap<String, Object>();
    stressorStepParameters.put("method", method);
    stressorStepParameters.put("url", url);
    stressorStepParameters.put("body", body);
    stressorStepParameters.put("headers", headers);
    stressorStepParameters.put("assertScript", assertScript);
    stressorStepParameters.put("mode", mode);
    stressorStepParameters.put("threads", threads);
    stressorStepParameters.put("csvRecords", csvRecords);
    List<List<String>> dataStress =
        (List<List<String>>) stressorWithClientStep.execute(stressorStepParameters);

    ReportStep reportStep = new ReportStep();
    List<String> reportColumnValues = Arrays.asList(reportColumns.split(","));
    HashMap<String, Object> reportStepParameters = new HashMap<String, Object>();
    reportStepParameters.put("dataStress", dataStress);
    reportStepParameters.put("reportColumnValues", reportColumnValues);
    reportStepParameters.put("reportPath", reportPath);
    Object result = reportStep.execute(reportStepParameters);

    long after = new Date().getTime();

    log("Stress completed.");
    log("");
    log("Status:" + result);
    log("Elapsed time:" + MeasureUtil.convertMillisMessage(after - before));

  }
}
