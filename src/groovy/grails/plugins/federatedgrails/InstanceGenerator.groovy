
package grails.plugins.federatedgrails

import org.codehaus.groovy.grails.commons.ConfigurationHolder
import grails.util.Holders

import grails.plugins.federatedgrails.SubjectBase

class InstanceGenerator {

    static subject = { 
      try { 
        if(Holders.getConfig()?.federation?.app?.subject) {
          InstanceGenerator.class.classLoader.loadClass(Holders.getConfig().federation.app.subject).newInstance()
        } else {
          SubjectBase.newInstance()
      }
      } catch(ClassNotFoundException e){ throw new RuntimeException(e)} 
    }

}

