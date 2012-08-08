package grails.plugins.federatedgrails

public class FederatedToken implements org.apache.shiro.authc.AuthenticationToken {

  def principal, credential, attributes, remoteHost, userAgent

  public Object getCredentials() {
    return this.credential
  }
  
  public Object getPrincipal() {
    return this.principal
  }

  public String toString() {
    "grails.plugins.federatedgrails.FederatedToken [principal:$principal, credential:$credential, attributes:$attributes, remoteHost:$remoteHost, userAgent:$userAgent]"
  }
  
}