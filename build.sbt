version := "1.0"
scalaVersion := "2.13.12"
name := "ValidationProject"

Compile / packageBin / mainClass := Some("il.ac.hit.validation.Main")

// Change the final JAR name (assembly output) to "validation.jar"
assembly / assemblyJarName := "validation.jar"