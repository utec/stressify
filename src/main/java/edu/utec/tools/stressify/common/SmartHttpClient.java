package edu.utec.tools.stressify.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SmartHttpClient {

  private final Logger logger = LogManager.getLogger(this.getClass());

  public HashMap<String, Object> performRequest(String method, String url, String body,
      HashMap<String, ?> headers) throws Exception {

    switch (method.toUpperCase()) {

      case "GET":
        return performRequest(url, headers, body, "GET");
      case "POST":
        return performRequest(url, headers, body, "POST");
      case "PUT":
        return performRequest(url, headers, body, "PUT");
      case "DELETE":
        return performRequest(url, headers, body, "DELETE");
      default:
        throw new Exception("Error: Method not implemented yet:" + method);
    }
  }

  // https://stackoverflow.com/questions/25011927/how-to-get-response-body-using-httpurlconnection-when-code-other-than-2xx-is-re/25012003
  private HashMap<String, Object> performRequest(String url, HashMap<String, ?> headers,
      String body, String method) throws Exception {
    try {

      URL obj = new URL(url);
      HttpURLConnection con = (HttpURLConnection) obj.openConnection();
      con.setDoOutput(true);

      con.setRequestMethod(method);

      // add request headers, if exist
      if (headers != null && !headers.isEmpty()) {
        for (Entry<String, ?> entry : headers.entrySet()) {
          con.setRequestProperty(entry.getKey(), (String) entry.getValue());
        }
      }

      // add request body, if exist
      if (body != null && !body.isEmpty()) {
        try (OutputStream os = con.getOutputStream()) {
          byte[] input = body.getBytes("utf-8");
          os.write(input, 0, input.length);
        }
      }

      int responseCode = con.getResponseCode();
      logger.debug("Sending '" + method + "' request to URL : " + url);
      logger.debug("Response Code : " + responseCode);


      BufferedReader br = null;

      if (200 <= responseCode && responseCode <= 399) {
        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
      } else {
        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
      }

      String responseLine;
      StringBuffer response = new StringBuffer();

      while ((responseLine = br.readLine()) != null) {
        response.append(responseLine);
      }

      br.close();

      logger.debug("Response body : " + response.toString());

      HashMap<String, Object> smartHttpClientResponse = new HashMap<String, Object>();
      smartHttpClientResponse.put("status", responseCode);
      smartHttpClientResponse.put("body", response.toString());

      // get response headers
      Map<String, List<String>> map = con.getHeaderFields();
      for (Map.Entry<String, List<String>> entry : map.entrySet()) {
        logger.debug(
            "Key : " + entry.getKey() + " ; Value : " + mergeHeadersWithSameKey(entry.getValue()));
        smartHttpClientResponse.put((entry.getKey() != null ? entry.getKey() : "default"),
            mergeHeadersWithSameKey(entry.getValue()));
      }

      return smartHttpClientResponse;

    } catch (Exception ex) {
      logger.debug("Response error : " + ex.getMessage());
      throw new Exception(String.format(
          "Low level error was detected when an http invocation was being executed. Url:%s Headers:%s Method:%s Body:%s",
          url, headers, method, body), ex);
    }
  }

  private String mergeHeadersWithSameKey(List<String> values) {
    if (values.size() == 1) {
      return values.get(0);
    } else {
      return String.join(",", values);
    }
  }


}
