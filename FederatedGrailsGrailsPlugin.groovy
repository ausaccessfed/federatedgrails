
import org.codehaus.groovy.grails.commons.GrailsApplication

import org.apache.shiro.SecurityUtils
import grails.plugins.federatedgrails.SubjectBase

class FederatedGrailsGrailsPlugin {
  def observe = ['controllers', 'services', 'filters']
  def version = "0.6.0"
  def grailsVersion = "2.2.4 > *"

  def pluginExcludes = [
    "grails-app/views/error.gsp"
  ]

  def title = "Federated Grails plugin"
  def description = ''' \
For application developers Federated environments can be somewhat daunting and complex.

This plugin allows Grails applications (particuarly those protected by Shibboleth service providers [http://shibboleth.net/products/service-provider.html] ) to easily integrate into federated authentication.

The plugin utilizes Shiro as its internal authentication and access control layer.
'''

  def documentation = "http://wiki.aaf.edu.au/tech-info/development-libraries-and-guides"

  def license = 'APACHE'
  def developers = [[
	  name: 'Bradley Beddoes', email: 'bradleybeddoes@gmail.com']
  ]
  def issueManagement = [system: 'GITHUB', url: 'https://github.com/ausaccessfed/federatedgrails/issues']
  def scm = [url: 'https://github.com/ausaccessfed/federatedgrails']

  def doWithSpring = {
    loadFederatedConfig(application, log)
  }

  def doWithDynamicMethods = { ctx ->
    // Supply authenticated subject to filters
    application.filtersClasses.each { filter ->
      // Should be used after verified call to 'accessControl'
      injectAuthn(filter.clazz, application)
    }

    // Supply authenticated subject to controllers
    application.controllerClasses?.each { controller ->
      injectAuthn(controller.clazz, application)
    }

    // Supply authenticated subject to services
    application.serviceClasses?.each { service ->
      injectAuthn(service.clazz, application)
    }
  }

  def onChange = { event ->
    injectAuthn(event.source, event.application)
  }

  // Supplies the authenticated subject object
  private void injectAuthn(clazz, GrailsApplication grailsApplication) {
    def config = grailsApplication.config
    GroovyClassLoader classLoader = new GroovyClassLoader(getClass().classLoader)

    clazz.metaClass.getPrincipal = { -> SecurityUtils.getSubject() }

    clazz.metaClass.getSubject = { ->
      def subject
      def principal = SecurityUtils.subject?.principal

      if(principal) {
        if(config.federation.app.subject) {
          subject = classLoader.loadClass(config.federation.app.subject).get(principal)
          log.debug "returning $subject"
          subject
        }
        else{
          subject = SubjectBase.get(principal)
          log.debug "returning base $subject"
          subject
        }
      }
    }
  }

  // Allows federation configuration to be seperately maintained in client app
  private ConfigObject loadFederatedConfig(GrailsApplication grailsApplication, log) {
    def config = grailsApplication.config
    GroovyClassLoader classLoader = new GroovyClassLoader(getClass().classLoader)

    // Merge federated config into main config environment if present otherwise expect client-app to supply using some other method
    try {
        config.merge(new ConfigSlurper(GrailsUtil.environment).parse(classLoader.loadClass('FederationConfig')))
    } catch (Exception ignored) {
      log.debug "FederationConfig.groovy was not found or failed to load. Expecting federation config is provided by other means for Shiro SP integrator."
    }

    config
  }
}
