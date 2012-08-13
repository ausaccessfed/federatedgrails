grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"

grails.project.dependency.resolution = {
  inherits("global") {}

  log "warn"
  checksums true
  
  repositories {
    inherits true

    grailsPlugins()
    grailsHome()
    grailsCentral()

    mavenLocal()
    mavenCentral()

    mavenRepo "http://snapshots.repository.codehaus.org"
    mavenRepo "http://repository.codehaus.org"
    mavenRepo "http://download.java.net/maven/2/"
    mavenRepo "http://repository.jboss.com/maven2/"
  }

  plugins {
    build ":release:2.0.4"

    compile ":shiro:1.1.4"

    runtime ":hibernate:$grailsVersion"

    test ":spock:0.6"
  }
}
