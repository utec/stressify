package edu.utec.tools.smarth_stressor.common;

public class ScriptImports {

  public static String getDefaultImports() {
    String imports = String.format("import %s; %n import %s; %n import %s; %n import %s;",
            "edu.utec.tools.smarth_stressor.oauth1.Oauth1Request",
            "edu.utec.tools.smarth_stressor.oauth1.SimpleOauth1Client",
            "com.jayway.jsonpath.JsonPath", "com.jayway.jsonpath.Configuration");

    return imports;
  }
}
