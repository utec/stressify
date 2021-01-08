package edu.utec.tools.stressify.common;

public class AssertsHelper {
  
  public static void ensureNotNullEmptyString(String instance, String fieldName) throws Exception {
    if(instance== null || instance.isEmpty()) {
      throw new Exception(String.format("%s is required", fieldName));
    }
  }

}
