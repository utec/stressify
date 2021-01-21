package edu.utec.tools.stressify.mode;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import edu.utec.common.performance.MeasureUtil;
import edu.utec.tools.stressify.common.ScriptImports;
import edu.utec.tools.stressify.steps.CSVReaderStep;
import edu.utec.tools.stressify.steps.ChartStep;
import edu.utec.tools.stressify.steps.ReportStep;
import edu.utec.tools.stressify.steps.StressorStep;
import edu.utec.tools.stressify.ui.MainView;

public class SimpleGraphicStressor {

  private final Logger logger = LogManager.getLogger(this.getClass());

  private JTextArea jTextAreaLog;

  public SimpleGraphicStressor(JTextArea jTextAreaLog) {
    super();
    this.jTextAreaLog = jTextAreaLog;
  }

  public void log(String message) {
    this.jTextAreaLog.append(message + "\n");
    logger.info(message);
  }

  public void clearLog() {
    this.jTextAreaLog.setText("");
  }

  @SuppressWarnings("unchecked")
  public void perform(MainView mainView, String uuid, String csvDataPath, String reportFolderPath,
      String reportName, boolean addMetadataToReport, boolean generateImageCharts,
      String reportColumns, String mode, String threads, String url, String method, String body,
      HashMap<String, String> headers, String assertScript) throws Exception {

    clearLog();
    long before = new Date().getTime();

    log("Stress starting...");
    if (csvDataPath == null || csvDataPath.equals("")) {
      log("Data csv file is not configured. Stresss will not use variables."
          + " Go to data section and configure it if you want to use variables!");
    }

    if (assertScript == null || assertScript.equals("")) {
      log("assert script is not configured.");
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
    log("report columns:" + reportColumns);
    log("mode:" + mode);
    log("virtual users:" + threads);

    CSVReaderStep csvReaderStep = new CSVReaderStep();
    HashMap<String, Object> csvStepParameters = new HashMap<String, Object>();
    csvStepParameters.put("csvDataPath", csvDataPath);
    Object csvRecords = csvReaderStep.execute(csvStepParameters);

    if (assertScript != null && !assertScript.isEmpty()) {
      StringBuilder scriptBuilder = new StringBuilder();
      scriptBuilder.append(ScriptImports.getDefaultImports() + "\n");
      scriptBuilder.append(ScriptImports.getDefaultFunctions() + "\n");
      scriptBuilder.append(assertScript + "\n");
      assertScript = scriptBuilder.toString();
    }

    StressorStep stressorWithClientStep = new StressorStep();
    HashMap<String, Object> stressorStepParameters = new HashMap<String, Object>();
    stressorStepParameters.put("method", method);
    stressorStepParameters.put("url", url);
    stressorStepParameters.put("body", body);
    stressorStepParameters.put("headers", headers);
    stressorStepParameters.put("assertScript", assertScript);
    stressorStepParameters.put("mode", mode);
    stressorStepParameters.put("threads", threads);
    stressorStepParameters.put("csvRecords", csvRecords);
    List<HashMap<String, Object>> dataStress =
        (List<HashMap<String, Object>>) stressorWithClientStep.execute(stressorStepParameters);

    if (dataStress == null || dataStress.isEmpty()) {
      throw new Exception("Stressor does not generated data for report.");
    }

    ReportStep reportStep = new ReportStep();
    List<String> reportColumnValues = Arrays.asList(reportColumns.split(","));
    HashMap<String, Object> reportStepParameters = new HashMap<String, Object>();
    reportStepParameters.put("dataStress", dataStress);
    reportStepParameters.put("reportColumnValues", reportColumnValues);
    reportStepParameters.put("reportFolderPath", reportFolderPath);
    reportStepParameters.put("reportName", reportName + "-" + uuid);
    reportStepParameters.put("addMetadataToReport", addMetadataToReport);
    reportStepParameters.put("method", method);
    reportStepParameters.put("url", url);
    reportStepParameters.put("mode", mode);
    reportStepParameters.put("threads", threads);
    Object result = reportStep.execute(reportStepParameters);

    if (generateImageCharts) {
      ChartStep chartStep = new ChartStep();
      HashMap<String, Object> chartStepParameters = new HashMap<String, Object>();
      chartStepParameters.put("dataStress", dataStress);
      chartStepParameters.put("reportFolderPath", reportFolderPath);
      chartStepParameters.put("mode", mode);
      chartStepParameters.put("threads", threads);
      chartStepParameters.put("uuid", uuid);
      chartStep.execute(chartStepParameters);
    }

    long after = new Date().getTime();

    log("Stress completed.");
    log("");
    log("Status:" + result);
    log("Elapsed time:" + MeasureUtil.convertMillisMessage(after - before));

    JOptionPane.showMessageDialog(mainView, "Stress completed", "Stressify",
        JOptionPane.INFORMATION_MESSAGE);

  }
}
