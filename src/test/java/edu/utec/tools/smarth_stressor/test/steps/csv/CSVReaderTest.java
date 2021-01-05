package edu.utec.tools.smarth_stressor.test.steps.csv;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import edu.utec.common.csv.CSVUtil;
import edu.utec.tools.fiveminutestressor.steps.CSVReaderStep;
import junit.framework.TestCase;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CSVReaderTest extends TestCase {

  public static List<?> csvRecords = null;

  @Test
  public void test_001_csvRecords() throws Exception {

    String path = System.getProperty("java.io.tmpdir") + File.separator + "test.csv";

    FileWriter writer = new FileWriter(path);

    List<String> header = new ArrayList<>();
    header.add("firstname");
    header.add("lastname");
    header.add("code");

    List<String> row1 = new ArrayList<>();
    row1.add("Tony");
    row1.add("Stark");
    row1.add("AVENGER-001");

    List<List<String>> dataList = new ArrayList<>();
    dataList.add(header);
    dataList.add(row1);

    CSVUtil csvUtil = new CSVUtil();

    writer.write(csvUtil.convertDataListToCSVString(dataList, "\n", ","));
    writer.close();

    CSVReaderStep csvReaderStep = new CSVReaderStep();
    HashMap<String, Object> csvStepParameters = new HashMap<String, Object>();
    csvStepParameters.put("csvDataPath", path);
    csvRecords = (List<?>) csvReaderStep.execute(csvStepParameters);

    // validate not null
    assertNotNull(csvRecords);

    // validate not empty
    assertTrue(!csvRecords.isEmpty());

    new File(path).delete();

  }

}
