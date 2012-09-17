eventCompileStart = {
  projectCompiler.srcDirectories << "$basedir/src/injected-src/controllers"
  projectCompiler.srcDirectories << "$basedir/src/injected-src/realms" 
} 
eventAllTestsStart = { 
  classLoader.addURL(new File("$basedir/src/injected-src/controllers").toURL()) 
  classLoader.addURL(new File("$basedir/src/injected-src/realms").toURL()) 
}
