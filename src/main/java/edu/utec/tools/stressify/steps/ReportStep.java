package edu.utec.tools.stressify.steps;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import edu.utec.common.csv.CSVUtil;
import edu.utec.tools.stressify.common.StringHelper;
import edu.utec.tools.stressify.core.ExecutableStep;

public class ReportStep implements ExecutableStep {

  @SuppressWarnings("unchecked")
  public Object execute(HashMap<String, Object> parameters) throws Exception {

    List<HashMap<String, Object>> dataStress =
        (List<HashMap<String, Object>>) parameters.get("dataStress");
    List<String> headers = (List<String>) parameters.get("reportColumnValues");
    String reportFolderPath = (String) parameters.get("reportFolderPath");
    String reportName = createReportName(parameters);
    String reportFilePath = reportFolderPath + File.separator + reportName;

    FileWriter writer = new FileWriter(reportFilePath);

    CSVUtil csvUtil = new CSVUtil();

    String headerString = csvUtil.convertListToString(headers, ",");

    String reportData = csvUtil.convertListMapToCsvString(dataStress, "\n", ",", headers);
    String headerAndReport = String.format("%s\n%s", headerString, reportData);

    writer.write(headerAndReport);
    writer.close();

    return "success";
  }

  private String createReportName(HashMap<String, Object> parameters) throws Exception {
    Boolean addMetadataToReport = (Boolean) parameters.get("addMetadataToReport");
    String reportName = null;
    if (addMetadataToReport.booleanValue()) {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
      String date = sdf.format(new Date());
      String url = StringHelper.sanitizeUrlToFileLocation((String) parameters.get("url"));
      String method = (String) parameters.get("method");
      String mode = (String) parameters.get("mode");
      String threads = (String) parameters.get("threads");
      reportName = String.format("%s-%s-%s-%s-%s-date-%s.csv",
          (String) parameters.get("reportName"), url, method, mode, threads, date);
    } else {
      reportName = (String) parameters.get("reportName") + ".csv";
    }

    return reportName;
  }

}
