package edu.utec.common.test;

import java.io.File;

import edu.utec.common.file.FileUtil;

public class TestUtil {
  public static File lookupFileForTesting(String fileName) throws Exception {
    String currentPath = FileUtil.getPathFromWhereApplicationIsRunning();
    String srcTestPath = String.format("%s%s%s%s", currentPath, File.separator,
            "src/test/resources/", fileName);
    return new File(srcTestPath);
  }
}
