package edu.utec.tools.fiveminutestressor.common;

import java.util.HashMap;
import org.apache.commons.csv.CSVRecord;

public class VariablesHelper {

  public static HashMap<String, String> csvRowToVariables(CSVRecord header, CSVRecord csvRecord) {

    HashMap<String, String> variables = new HashMap<String, String>();
    for (int col = 0; col < header.size(); col++) {
      variables.put(header.get(col), csvRecord.get(col));
    }
    return variables;
  }
}
