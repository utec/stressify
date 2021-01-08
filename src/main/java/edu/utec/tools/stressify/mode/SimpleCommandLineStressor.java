package edu.utec.tools.stressify.mode;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import edu.utec.common.performance.MeasureUtil;
import edu.utec.tools.stressify.steps.CSVReaderStep;
import edu.utec.tools.stressify.steps.ReportStep;

@Deprecated
public class SimpleCommandLineStressor {

  private final Logger logger = LogManager.getLogger(this.getClass());

  @SuppressWarnings("unchecked")
  public void perform(String[] args) throws Exception {

    long before = new Date().getTime();

    logger.info("Stress starting...");
    System.out.println("Stress starting...");

    if (args.length < 4) {
      System.err.println("Some parameters are missing. Stressor needs 4 parameters as minimum : "
          + "csvTempPath,scriptTempPath,reportPath,headerString,headerString. threads parameter is optional");
      System.exit(1);
    }

    String csvDataPath = args[0];
    String scriptTempPath = args[1];
    String reportPath = args[2];
    String headerString = args[3];
    String mode = args[4];

    String threads = "1";

    if (args.length > 5) {
      threads = args[5];
    }

    /*
     * CSVReaderStep csvReaderStep = new CSVReaderStep(); HashMap<String, Object> stepParameters =
     * new HashMap<String, Object>(); stepParameters.put("csvDataPath", csvDataPath); List<?>
     * csvRecords = (List<?>) csvReaderStep.execute(stepParameters);
     * 
     * StressorStep stressorStep = new StressorStep(); List<List<String>> dataStress =
     * (List<List<String>>) stressorStep .execute(new Object[] {scriptTempPath, csvRecords, mode,
     * threads});
     * 
     * ReportStep reportStep = new ReportStep(); List<String> headers =
     * Arrays.asList(headerString.split(",")); Object result = reportStep.execute(new Object[]
     * {dataStress, headers, reportPath});
     * 
     * long after = new Date().getTime();
     * 
     * System.out.println("Stress completed."); System.out.println("\tStatus:" + result);
     * System.out.println("\tElapsed time:" + MeasureUtil.convertMillisMessage(after - before));
     */

  }
}
