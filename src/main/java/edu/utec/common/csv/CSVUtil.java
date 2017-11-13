package edu.utec.common.csv;

import java.util.List;

public class CSVUtil {

  public String convertDataListToCSVString(List<List<String>> dataList, String rowDelimiter,
          String columnDelimiter) {

    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < dataList.size(); i++) {
      List<String> rowList = dataList.get(i);
      String rowString = convertListToString(rowList, columnDelimiter);
      sb = sb.append(rowString);
      if (i != dataList.size() - 1) {
        sb.append(rowDelimiter);
      }
    }

    return sb.toString();
  }

  public String convertListToString(List<String> incomingArray, String delimiter) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < incomingArray.size(); i++) {
      sb = sb.append(incomingArray.get(i));
      if (i != incomingArray.size() - 1) {
        sb.append(delimiter);
      }
    }
    return sb.toString();
  }

}
