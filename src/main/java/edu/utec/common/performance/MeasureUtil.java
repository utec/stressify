package edu.utec.common.performance;

public class MeasureUtil {

  public static String convertMillisMessage(long milliseconds) {
    int h = (int) ((milliseconds / 1000) / 3600);
    int m = (int) (((milliseconds / 1000) / 60) % 60);
    int s = (int) ((milliseconds / 1000) % 60);

    return String.format("%s 'hours', %s 'mins,' %s 'seconds', %s 'millis'", h, m, s,
            milliseconds % 1000);
  }
}
