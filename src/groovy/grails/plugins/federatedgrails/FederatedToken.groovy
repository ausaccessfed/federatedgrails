package grails.plugins.federatedgrails

import org.apache.shiro.authc.AuthenticationToken

class FederatedToken implements AuthenticationToken {

  def principal, credential, attributes, remoteHost, userAgent

  Object getCredentials() {
    return credential
  }

  String toString() {
    "grails.plugins.federatedgrails.FederatedToken [principal:$principal, credential:$credential, attributes:$attributes, remoteHost:$remoteHost, userAgent:$userAgent]"
  }
}
