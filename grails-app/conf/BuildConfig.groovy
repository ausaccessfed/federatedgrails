grails.project.work.dir = 'target'

grails.project.dependency.resolution = {

  inherits("global") {
    excludes 'shiro-cas'
  }
  log 'warn'

  repositories {
    grailsCentral()
    mavenLocal()
    mavenCentral()
  }

  plugins {
    build ':release:2.2.1', ':rest-client-builder:1.0.3', {
      export = false
    }

    compile ":shiro:1.1.3"

    runtime ":hibernate:$grailsVersion", {
      export = false
    }

    test(":spock:0.7") {
      exclude "spock-grails-support"
    }
  }
}
