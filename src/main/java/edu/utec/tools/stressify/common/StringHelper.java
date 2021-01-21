package edu.utec.tools.stressify.common;

public class StringHelper {
  public static String sanitizeUrlToFileLocation(String url) throws Exception {
    return url.replaceAll("http://", "").replaceAll("/", "_").replaceAll(":", "_");
  }
}
