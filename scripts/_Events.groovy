eventCompileStart = {
  projectCompiler.srcDirectories << "$basedir/src/injected-src/controllers".toString()
  projectCompiler.srcDirectories << "$basedir/src/injected-src/realms".toString()
}
eventAllTestsStart = {
  classLoader.addURL(new File(basedir, "src/injected-src/controllers").toURI().toURL())
  classLoader.addURL(new File(basedir, "src/injected-src/realms").toURI().toURL())
}
