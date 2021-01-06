package edu.utec.tools.fiveminutestressor.common;

public class StringHelper {
  public static String sanitizeUrlToFileLocation(String url) throws Exception {
    return url.replaceAll("http://", "").replaceAll("/", "_").replaceAll(":", "_");
  }
}
