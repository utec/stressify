package edu.utec.common.test;

import java.io.IOException;
import java.util.Properties;

public class TestProperties {

  private static Properties properties = null;
  private static String DEFAULT_TEST_PROPERTIES_FILE_NAME = "test.properties";

  public void initialize(Class<?> testClass) {

    try {
      properties = new Properties();
      // load a properties file
      properties.load(testClass.getResourceAsStream(DEFAULT_TEST_PROPERTIES_FILE_NAME));

    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public String getProperty(String key) throws Exception {
    if (properties == null) {
      throw new Exception(String.format("Failed when getting key from properties.", key,
              DEFAULT_TEST_PROPERTIES_FILE_NAME));
    }

    return properties.getProperty(key);
  }

}
