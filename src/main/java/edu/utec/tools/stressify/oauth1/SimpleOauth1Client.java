package edu.utec.tools.stressify.oauth1;

import java.io.InputStream;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.http.HttpParameters;
import oauth.signpost.signature.AuthorizationHeaderSigningStrategy;

public class SimpleOauth1Client {

  private OAuthConsumer oAuthConsumer = null;
  private boolean enableTLS12;
  private Object response = null;
  private int responseStatus;
  private String url = null;
  private HashMap<String, String> headers = null;

  public void setupContext(Oauth1Request oauth1Request) {
    this.oAuthConsumer = new CommonsHttpOAuthConsumer(oauth1Request.getConsumerKey(),
            oauth1Request.getConsumerSecret());
    oAuthConsumer.setTokenWithSecret(oauth1Request.getAccessToken(),
            oauth1Request.getAccessTokenSecret());
    oAuthConsumer.setSigningStrategy(new AuthorizationHeaderSigningStrategy());

    HttpParameters oauthParameters = new HttpParameters();
    oauthParameters.put("realm", oauth1Request.getRealmID());
    oAuthConsumer.setAdditionalParameters(oauthParameters);
    this.enableTLS12 = oauth1Request.isEnableTLS12();
  }

  public void authorize(HttpRequestBase httpRequest) throws OAuthMessageSignerException,
          OAuthExpectationFailedException, OAuthCommunicationException {
    oAuthConsumer.sign(httpRequest);
  }

  public void executePostRequest(String bodyRequest) throws Exception {

    HttpClient client = null;

    try {
      if (this.enableTLS12) {
        SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
        sslContext.init(null, null, null);
        client = HttpClientBuilder.create().setSSLContext(sslContext).build();
      } else {
        client = HttpClientBuilder.create().build();
      }
    } catch (Exception e) {
      throw new Exception("Failed when configuring TLSv1.2", e);
    }

    HttpPost postRequest = null;
    URI uri = null;

    try {
      uri = new URI(this.url);
    } catch (URISyntaxException e) {
      throw new Exception("Failed when configuring URL:" + this.url, e);
    }

    postRequest = new HttpPost(uri);
    
    if(headers!=null && headers.size()>0) {
      for(Entry<String,String> entry : headers.entrySet()) {
        postRequest.addHeader(entry.getKey(), entry.getValue());    
      }
    }
    

    StringEntity stringEntity = new StringEntity(bodyRequest,
            ContentType.create("text/plain", "UTF-8"));

    postRequest.setEntity(stringEntity);

    try {
      authorize(postRequest);
    } catch (Exception e) {
      throw new Exception("Failed when authorizing this endpoint URL:" + this.url, e);
    }

    HttpResponse httpResponse = null;

    try {
      HttpHost target = new HttpHost(uri.getHost(), -1, uri.getScheme());
      httpResponse = client.execute(target, postRequest);

      InputStream inputStraem = httpResponse.getEntity().getContent();

      StringWriter writer = new StringWriter();
      IOUtils.copy(inputStraem, writer, "UTF-8");
      response = writer.toString();

      responseStatus = httpResponse.getStatusLine().getStatusCode();

    } catch (Exception e) {
      throw new Exception(String.format("Failed when invoking this endpoint:%n %s %n %s", this.url,
              bodyRequest), e);
    }
  }

  public Object getResponse() {
    return response;
  }

  public void setResponse(Object response) {
    this.response = response;
  }

  public int getResponseStatus() {
    return responseStatus;
  }

  public void setResponseStatus(int responseStatus) {
    this.responseStatus = responseStatus;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public HashMap<String, String> getHeaders() {
    return headers;
  }

  public void setHeaders(HashMap<String, String> headers) {
    this.headers = headers;
  }

}
