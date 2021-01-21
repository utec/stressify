package edu.utec.tools.stressify.model;

import java.util.ArrayList;

public class Stress {

  private String url;
  private String method;
  private ArrayList<HttpHeader> httpHeaders;
  private String body;
  private String assertScript;
  private String reportFolderPath;
  private String reportName;
  private String csvDataPath;
  private String mode;
  private int threadNumber;

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
  }

  public ArrayList<HttpHeader> getHttpHeaders() {
    return httpHeaders;
  }

  public void setHttpHeaders(ArrayList<HttpHeader> httpHeaders) {
    this.httpHeaders = httpHeaders;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public String getAssertScript() {
    return assertScript;
  }

  public void setAssertScript(String assertScript) {
    this.assertScript = assertScript;
  }

  public String getReportFolderPath() {
    return reportFolderPath;
  }

  public void setReportFolderPath(String reportFolderPath) {
    this.reportFolderPath = reportFolderPath;
  }

  public String getReportName() {
    return reportName;
  }

  public void setReportName(String reportName) {
    this.reportName = reportName;
  }

  public String getCsvDataPath() {
    return csvDataPath;
  }

  public void setCsvDataPath(String csvDataPath) {
    this.csvDataPath = csvDataPath;
  }

  public String getMode() {
    return mode;
  }

  public void setMode(String mode) {
    this.mode = mode;
  }

  public int getThreadNumber() {
    return threadNumber;
  }

  public void setThreadNumber(int threadNumber) {
    this.threadNumber = threadNumber;
  }
}
