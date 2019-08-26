package edu.utec.common.variable;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import junit.framework.TestCase;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VariableUtilTest extends TestCase {

  @Test
  public void test_01_no_variables() throws Exception {
    String rawString = "https://bitbucket.org/utecsup/ceditec-web/pull-requests";
    String evaluatedString = VariableUtil.replaceVariablesInString(rawString,
            new HashMap<String, String>());
    assertEquals(rawString, evaluatedString);
  }

  @Test
  public void test_02_one_variable() throws Exception {
    String rawString = "https://bitbucket.org/utecsup${a}/ceditec-web/pull-requests";
    String expectedString = "https://bitbucket.org/utecsup5/ceditec-web/pull-requests";

    HashMap<String, String> variables = new HashMap<String, String>();
    variables.put("a", "5");

    String evaluatedString = VariableUtil.replaceVariablesInString(rawString, variables);
    assertEquals(evaluatedString, expectedString);
  }

  @Test
  public void test_03_several_variables() throws Exception {
    String rawString = "https://bitbucket.org/utecsup/${a}/ceditec-web/${b}/pull-requests";
    String expectedString = "https://bitbucket.org/utecsup/5/ceditec-web/6/pull-requests";

    HashMap<String, String> variables = new HashMap<String, String>();
    variables.put("a", "5");
    variables.put("b", "6");

    String evaluatedString = VariableUtil.replaceVariablesInString(rawString, variables);
    assertEquals(evaluatedString, expectedString);
  }

  @Test
  public void test_04_key_number_variables() throws Exception {
    String rawString = "https://bitbucket.org/utecsup/${100}/ceditec-web/pull-requests";
    String expectedString = "https://bitbucket.org/utecsup/5/ceditec-web/pull-requests";

    HashMap<String, String> variables = new HashMap<String, String>();
    variables.put("100", "5");

    String evaluatedString = VariableUtil.replaceVariablesInString(rawString, variables);
    assertEquals(evaluatedString, expectedString);
  }

  @Test
  public void test_05_invalidKeys1() throws Exception {
    String rawString = "https://bitbucket.org/utecsup/${aa{sd}/ceditec-web/pull-requests";
    String expectedString = "https://bitbucket.org/utecsup/${aa{sd}/ceditec-web/pull-requests";

    HashMap<String, String> variables = new HashMap<String, String>();
    variables.put("a", "b");

    String evaluatedString = VariableUtil.replaceVariablesInString(rawString, variables);
    assertEquals(evaluatedString, expectedString);
  }

  @Test
  public void test_06_invalidKeys2() throws Exception {
    String rawString = "https://bitbucket.org/utecsup/${asda dasd}/ceditec-web/pull-requests";
    String expectedString = "https://bitbucket.org/utecsup/${asda dasd}/ceditec-web/pull-requests";

    HashMap<String, String> variables = new HashMap<String, String>();
    variables.put("a", "b");

    String evaluatedString = VariableUtil.replaceVariablesInString(rawString, variables);
    assertEquals(evaluatedString, expectedString);
  }

  @Test
  public void test_07_randomPositiveInteger() throws Exception {
    String rawString = "https://bitbucket.org/utecsup/${randomPositiveInteger}/ceditec-web/pull-requests";

    HashMap<String, String> variables = new HashMap<String, String>();
    variables.put("a", "b");

    String evaluatedString = VariableUtil.replaceVariablesInString(rawString, variables);

    Pattern p = Pattern.compile("([0-9])+");
    Matcher m = p.matcher(evaluatedString);
    assertTrue("Expected string must contains a integer: "+evaluatedString, m.find());
  }
  
  @Test
  public void test_08_randomPositiveDouble() throws Exception {
    String rawString = "https://bitbucket.org/utecsup/${randomPositiveDouble}/ceditec-web/pull-requests";
    
    HashMap<String, String> variables = new HashMap<String, String>();
    variables.put("a", "b");
    
    String evaluatedString = VariableUtil.replaceVariablesInString(rawString, variables);
    
    Pattern p = Pattern.compile("\\d+\\.\\d+");
    Matcher m = p.matcher(evaluatedString);
    assertTrue("Expected string must contains a double: "+evaluatedString, m.find());
  }

}
