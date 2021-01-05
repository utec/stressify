package edu.utec.tools.fiveminutestressor.steps;

import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;

import edu.utec.common.csv.CSVUtil;
import edu.utec.tools.fiveminutestressor.core.ExecutableStep;

public class ReportStep implements ExecutableStep {

  @SuppressWarnings("unchecked")
  public Object execute(HashMap<String, Object> parameters) throws Exception {

    List<List<String>> dataStress = (List<List<String>>) parameters.get("dataStress");
    List<String> headers = (List<String>) parameters.get("reportColumnValues");
    String path = (String) parameters.get("reportPath");

    FileWriter writer = new FileWriter(path);

    CSVUtil csvUtil = new CSVUtil();

    String headerString = csvUtil.convertListToString(headers, ",");

    String reportData = csvUtil.convertDataListToCSVString(dataStress, "\n", ",");
    String headerAndReport = String.format("%s\n%s", headerString, reportData);

    writer.write(headerAndReport);
    writer.close();

    return "success";
  }

}
