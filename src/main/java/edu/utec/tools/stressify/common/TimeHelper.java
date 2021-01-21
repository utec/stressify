package edu.utec.tools.stressify.common;

import java.util.Date;

public class TimeHelper {
  
  public static long getDateAsLong(Date date) {
    return date.getTime();
  }
  
  public static long getDateAsLong() {
    Date now = new Date();
    return getDateAsLong(now);
  }
  
  public static Date millisToDate(Long miliSec) {
    return new Date(miliSec); 
  }

}
