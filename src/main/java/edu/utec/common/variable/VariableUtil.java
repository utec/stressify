package edu.utec.common.variable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VariableUtil {

  public static String replaceVariablesInString(String rawString,
      HashMap<String, String> variables) {

    if (variables == null || variables.isEmpty()) {
      return rawString;
    }

    String regex = "(\\$\\{[\\w\\^\\$\\s]+\\})";
    Matcher m = Pattern.compile(regex).matcher(rawString);
    while (m.find()) {
      String key = m.group(0).replace("${", "").replace("}", "");

      if (key == null || key.equals("")) {
        continue;
      }

      if (variables.containsKey(key)) {
        rawString = rawString.replace(String.format("${%s}", key), variables.get(key));
      } else if (containsJockers(key)) {
        rawString = rawString.replace(String.format("${%s}", key), parseJocker(key));
      }
    }
    return rawString;
  }

  public static boolean containsJockers(String key) {
    return key.contentEquals("randomPositiveInteger") || key.contentEquals("randomPositiveDouble");
  }

  public static String parseJocker(String key) {
    try {
      if (key.contentEquals("randomPositiveInteger")) {
        return "" + Math.abs(getRandomIntegerInRange(1000, 10000));
      } else if (key.contentEquals("randomPositiveDouble")) {
        return "" + Math.abs(getRandomDoubleInRange(1000, 10000));
      } else {
        return key;
      }
    } catch (Exception e) {
      e.printStackTrace();// TODO: delete this ugly line
      return key;
    }
  }

  private static int getRandomIntegerInRange(int min, int max) {

    if (min >= max) {
      throw new IllegalArgumentException("max must be greater than min");
    }

    Random r = new Random();
    return r.nextInt((max - min) + 1) + min;
  }

  private static double getRandomDoubleInRange(int min, int max) {

    if (min >= max) {
      throw new IllegalArgumentException("max must be greater than min");
    }

    Random r = new Random();
    return min + (max - min) * r.nextDouble();
  }

  public static ArrayList<HashMap<String, String>> replaceInHeaderValues(
      ArrayList<HashMap<String, String>> headers, HashMap<String, String> variables) {

    ArrayList<HashMap<String, String>> newHeaders = new ArrayList<HashMap<String, String>>();

    for (HashMap<String, String> headerData : headers) {
      HashMap<String, String> header = new HashMap<String, String>();
      Iterator<?> it = headerData.entrySet().iterator();
      while (it.hasNext()) {
        Map.Entry<?, ?> pair = (Map.Entry<?, ?>) it.next();
        header.put("" + pair.getKey(),
            VariableUtil.replaceVariablesInString("" + pair.getValue(), variables));
      }

      newHeaders.add(header);
    }

    return newHeaders;
  }

}
