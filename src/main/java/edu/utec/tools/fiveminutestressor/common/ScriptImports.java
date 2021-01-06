package edu.utec.tools.fiveminutestressor.common;

public class ScriptImports {

  public static String getDefaultImports() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("import com.jayway.jsonpath.JsonPath;\n");
    stringBuilder.append("import static org.assertj.core.api.Assertions.*;\n");
    return stringBuilder.toString();
  }
  
  public static String getDefaultFunctions() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("def jsonPath(expresion){return JsonPath.parse(response).read(expresion)}\n");
    return stringBuilder.toString();
  }
}
