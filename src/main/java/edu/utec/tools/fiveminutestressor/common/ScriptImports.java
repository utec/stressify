package edu.utec.tools.fiveminutestressor.common;

public class ScriptImports {

  public static String getDefaultImports() {
    String imports = String.format("import %s; %n",
            "com.jayway.jsonpath.JsonPath");

    return imports;
  }
}
