package edu.utec.tools.stressify.common;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

public class AssertsHelper {

  public static void ensureNotNullEmptyString(String instance, String fieldName) throws Exception {
    if (instance == null || instance.isEmpty()) {
      throw new Exception(String.format("%s is required", fieldName));
    }
  }

  public static boolean evaluateSimpleAssert(String response, String script) {
    Binding binding = new Binding();
    return evaluateSimpleAssert(response, script, binding);
  }

  public static boolean evaluateSimpleAssert(String response, String script, Binding binding) {
    if (binding == null) {
      binding = new Binding();
    }
    binding.setVariable("response", response);
    GroovyShell shell = new GroovyShell(binding);
    shell.evaluate(script);
    // if no errors were thrown
    return true;
  }

}
