package grails.plugins.federatedgrails

class Role {
  static auditable = true

  String name
  String description

  boolean protect

  static hasMany = [ subjects: SubjectBase, permissions: Permission ]

  static constraints = {
      name(blank: false, unique: true)
      description(nullable:true)
  }
}
