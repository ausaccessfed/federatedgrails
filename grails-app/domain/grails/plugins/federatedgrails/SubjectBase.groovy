package grails.plugins.federatedgrails

class SubjectBase {
  static auditable = true

  String principal
  boolean enabled

  static belongsTo = Role

  static hasMany = [
    sessionRecords: SessionRecord,
    roles: Role,
    permissions: Permission
  ]

  static constraints = {
    principal(blank: false, unique:true)
  }

}
