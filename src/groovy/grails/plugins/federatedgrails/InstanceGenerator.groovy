package grails.plugins.federatedgrails

import grails.util.Holders

class InstanceGenerator {

   static subject = {
      if (Holders.getConfig()?.federation?.app?.subject) {
         InstanceGenerator.classLoader.loadClass(Holders.getConfig().federation.app.subject).newInstance()
      }
      else {
         SubjectBase.newInstance()
      }
   }
}
