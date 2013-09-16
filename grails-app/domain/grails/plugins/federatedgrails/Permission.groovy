package grails.plugins.federatedgrails

import grails.plugins.federatedgrails.Role

/**
 * Our permission object encapsulates details that a normal Shiro deployment
 * would put into mapping tables to make life a little easier.
 *
 * @author Bradley Beddoes
 */
class Permission implements Serializable {
  static auditable = true

  static public final String defaultPerm = "grails.plugins.federatedgrails.WildcardPermission"
  static public final String wildcardPerm = "grails.plugins.federatedgrails.WildcardPermission"
  static public final String adminPerm = "grails.plugins.federatedgrails.AllPermission"

    String type
    String possibleActions = "*"
    String actions = "*"
    String target
    boolean managed

    SubjectBase subject
    Role role

    static belongsTo = [subject: SubjectBase, role: Role]

    static transients = [ "owner" ]

    static constraints = {
        type(blank: false)
        possibleActions(blank: false)
        actions(blank: false)
        target(blank: false)

        subject(nullable:true)
        role(nullable:true)
    }

    void setOwner(owner) {
        if (owner instanceof SubjectBase)
        subject = owner

        if (owner instanceof Role)
        role = owner
    }

    def getOwner() {
        if(subject != null)
        return subject

        if(role != null)
        return role

        return null
    }

    def getDisplayType() {
      def components = type.tokenize('.')
      if(components)
        components[components.size() - 1]
      else
        type
    }
}
