package grails.plugins.federatedgrails

import grails.plugin.spock.UnitSpec

class InstanceGeneratorSpec extends UnitSpec {

  def 'InstanceGenerator returns SubjectBase when no configuration supplied'() {
    setup:
    mockConfig '''
    '''

    when:
    def subject = InstanceGenerator.subject()

    then:
    !(subject instanceof TestSubject)
    subject instanceof SubjectBase
  }

  def 'InstanceGenerator returns TestSubject when configured as such'() {
    setup:
    mockConfig '''
      federation.app.subject = 'grails.plugins.federatedgrails.TestSubject'
    '''

    when:
    def subject = InstanceGenerator.subject()

    then:
    subject instanceof TestSubject
    subject instanceof SubjectBase
  }

  def 'InstanceGenerator throws RuntimeException when invalid class set'() {
    setup:
    mockConfig '''
      federation.app.subject = 'grails.plugins.federatedgrails.NullSubject'
    '''

    when:
    def subject = InstanceGenerator.subject()

    then:
    RuntimeException e = thrown()
  }
}
