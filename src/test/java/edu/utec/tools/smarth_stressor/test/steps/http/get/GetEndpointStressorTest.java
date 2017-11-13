package edu.utec.tools.smarth_stressor.test.steps.http.get;

import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import edu.utec.common.file.FileUtil;
import edu.utec.tools.smarth_stressor.steps.CSVReaderStep;
import edu.utec.tools.smarth_stressor.steps.ReportStep;
import edu.utec.tools.smarth_stressor.steps.StressorStep;
import junit.framework.TestCase;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GetEndpointStressorTest extends TestCase {

  public static List<?> csvRecords = null;
  public static List<List<String>> dataStress;
  private static String scriptFile = "http-get-demo.txt";
  private static String dataFile = "data.csv";
  private static String headerString = "date,start time,end time,http response code,custom logic,elapsed time";

  @Test
  public void test_001_csvRecords() throws Exception {

    String csvTempPath = System.getProperty("java.io.tmpdir") + File.separator + dataFile;
    InputStream csvSource = GetEndpointStressorTest.class.getResourceAsStream(dataFile);
    FileUtil.inputStreamToFile(csvSource, new File(csvTempPath));

    CSVReaderStep csvReaderStep = new CSVReaderStep();
    csvRecords = (List<?>) csvReaderStep.execute(new String[] { csvTempPath });

    // validate not null
    assertNotNull(csvRecords);

    // validate not empty
    assertTrue(!csvRecords.isEmpty());

    new File(csvTempPath).delete();

  }

  @SuppressWarnings("unchecked")
  public void test_002_stressor() throws Exception {

    String scriptTempPath = System.getProperty("java.io.tmpdir") + File.separator + scriptFile;
    InputStream scriptSource = GetEndpointStressorTest.class.getResourceAsStream(scriptFile);
    FileUtil.inputStreamToFile(scriptSource, new File(scriptTempPath));

    StressorStep stressorStep = new StressorStep();
    dataStress = (List<List<String>>) stressorStep
            .execute(new Object[] { scriptTempPath, csvRecords, "continuous" });

    new File(scriptTempPath).delete();

    assertTrue(!dataStress.isEmpty());

  }

  public void test_003_report() throws Exception {

    String reportPath = System.getProperty("java.io.tmpdir") + File.separator + "report.csv";

    List<String> headers = Arrays.asList(headerString.split(","));

    ReportStep reportStep = new ReportStep();
    Object result = reportStep.execute(new Object[] { dataStress, headers, reportPath });

    assertTrue(result.equals("success"));

    assertTrue("Reporte deberia existir", new File(reportPath).exists());

    System.out.println("Test completed.");

    new File(reportPath).delete();

  }

}
