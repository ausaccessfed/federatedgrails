package grails.plugins.federatedgrails

class SessionRecord {

  static belongsTo = [subject: SubjectBase]

  String credential
  String remoteHost
  String userAgent

  Date dateCreated

  static constraints = {
    credential(blank: false)
    remoteHost(blank: false)
    userAgent(blank: false)
  }
}
