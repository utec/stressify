package edu.utec.tools.smarth_stressor.oauth1;

public class Oauth1Request {

  private String realmID = null;
  private String consumerKey = null;
  private String consumerSecret = null;
  private String accessToken = null;
  private String accessTokenSecret = null;
  private boolean enableTLS12;

  public String getRealmID() {
    return realmID;
  }

  public void setRealmID(String realmID) {
    this.realmID = realmID;
  }

  public String getConsumerKey() {
    return consumerKey;
  }

  public void setConsumerKey(String consumerKey) {
    this.consumerKey = consumerKey;
  }

  public String getConsumerSecret() {
    return consumerSecret;
  }

  public void setConsumerSecret(String consumerSecret) {
    this.consumerSecret = consumerSecret;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public String getAccessTokenSecret() {
    return accessTokenSecret;
  }

  public void setAccessTokenSecret(String accessTokenSecret) {
    this.accessTokenSecret = accessTokenSecret;
  }

  public boolean isEnableTLS12() {
    return enableTLS12;
  }

  public void setEnableTLS12(boolean enableTLS12) {
    this.enableTLS12 = enableTLS12;
  }
}
