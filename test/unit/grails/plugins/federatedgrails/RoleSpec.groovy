package grails.plugins.federatedgrails

import grails.plugin.spock.UnitSpec

class RoleSpec extends UnitSpec {

  def 'Ensure Role wont validate with null name'() {
    setup:
    mockDomain(Role)

    when:
    def r = new Role().validate()

    then:
    !r
  }

  def 'Ensure Role wont validate with blank name'() {
    setup:
    mockDomain(Role)

    when:
    def r = new Role(name:'').validate()

    then:
    !r
  }

  def 'Ensure Role wont validate with non-unique name'() {
    setup:
    mockDomain(Role)

    when:
    new Role(name:'testrole').save()
    def r = new Role(name:'testrole').validate()

    then:
    !r
  }

  def 'Ensure Role will validate'() {
    setup:
    mockDomain(Role)

    when:
    def r = new Role(name:'testrole').validate()

    then:
    r
  }

}
