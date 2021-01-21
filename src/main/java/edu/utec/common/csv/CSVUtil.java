package edu.utec.common.csv;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class CSVUtil {

  public String convertListMapToCsvString(List<HashMap<String, Object>> dataList,
      String rowDelimiter, String columnDelimiter, List<String> headers) throws Exception {

    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < dataList.size(); i++) {
      HashMap<String, Object> rowMap = dataList.get(i);
      if (rowMap != null && !rowMap.isEmpty()) {
        String rowString = convertMapToStringWithOrder(rowMap, columnDelimiter, headers);
        sb = sb.append(rowString);
        if (i != dataList.size() - 1) {
          sb.append(rowDelimiter);
        }
      }
    }

    String csvString = sb.toString();
    if (csvString == null || csvString.isEmpty()) {
      throw new Exception("csv generated is null or empty.");
    }

    return sb.toString();
  }

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

  public String convertMapToStringWithOrder(HashMap<String, Object> incomingMap, String delimiter,
      List<String> headers) {
    StringBuilder sb = new StringBuilder();
    int size = incomingMap.size();
    int i = 0;

    for (String headerName : headers) {
      if (incomingMap.get(headerName) != null) {
        sb = sb.append(incomingMap.get(headerName));
        if (i != size - 1) {
          sb.append(delimiter);
        }
      }
      i++;
    }

    return sb.toString();
  }

  public String convertMapToString(HashMap<String, Object> incomingMap, String delimiter) {
    StringBuilder sb = new StringBuilder();
    int size = incomingMap.size();
    int i = 0;
    for (Entry<String, Object> entry : incomingMap.entrySet()) {
      sb = sb.append(entry.getValue());
      if (i != size - 1) {
        sb.append(delimiter);
      }
      i++;
    }

    return sb.toString();
  }

}
