lazy val `baccarat-display` = project.in(file("."))
  .settings(commonSettings)
  .settings(warnUnusedImport)
  .aggregate(`baccarat-scribe`)

lazy val `baccarat-scribe` = project
  .settings(universalSettings)
  .settings(addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full))
  .settings(libraryDependencies += "com.github.pathikrit" %% "better-files" % "3.6.0")
  .settings(libraryDependencies += "com.github.pureconfig" %% "pureconfig" % "0.10.2")
  .settings(libraryDependencies += "org.reactfx" % "reactfx" % "2.0-M5")
  .settings(libraryDependencies += "org.scodec" %% "scodec-bits" % "1.1.5")
  .settings(libraryDependencies += "com.tykhe.fx" %% "fx-io" % "5.0.0")
  .settings(libraryDependencies += "co.fs2" %% "fs2-core" % "0.10.4")
  .settings(libraryDependencies += "org.scalafx" %% "scalafxml-core-sfx8" % "0.4")
  .settings(libraryDependencies += "com.tykhe.host" %% "host-security" % "0.0.2")
  .settings(
    mainClass in assembly := Some("BaccaratApp"),
    assemblyJarName in assembly := "baccaratDisplay.jar")
  .enablePlugins(JavaAppPackaging)

lazy val universalSettings = Seq(
  // basic settings
  packageName in Universal    := name.value,
  maintainer                  := "tykhegaming.com",
  assemblyJarName in assembly := "fat.jar",
  // remove all jar mappings in universal and append the fat jar
  mappings in Universal := (mappings in Universal).value.filterNot(_._2.endsWith(".jar")),
  mappings in Universal += (assembly in Compile).map(f => f -> s"lib/${f.name}").value,
  // bash scripts classpath only needs the fat jar
  scriptClasspath := Seq((assemblyJarName in assembly).value, ".")
)

lazy val warnUnusedImport = Seq(
  scalacOptions ++= {
    CrossVersion.partialVersion(scalaVersion.value) match {
      case Some((2, 10))           =>
        Seq()
      case Some((2, n)) if n >= 11 =>
        Seq("-Ywarn-unused-import")
    }
  },
  scalacOptions in(Compile, console) ~= {
    _.filterNot("-Ywarn-unused-import" == _)
  },
  scalacOptions in(Test, console) ~= {
    _.filterNot("-Ywarn-unused-import" == _)
  }
)

lazy val commonSettings = Seq(
  organization := "com.tykhe.baccarat",
  version := "0.1.0",
  scalaVersion := "2.12.4",
  crossScalaVersions := Seq("2.11.12", "2.12.4"),

  scalacOptions ++= Seq(
    // warnings
    "-unchecked", // able additional warnings where generated code depends on assumptions
    "-deprecation", // emit warning for usages of deprecated APIs
    "-feature", // emit warning usages of features that should be imported explicitly
    // Features enabled by default
    "-language:higherKinds",
    "-language:implicitConversions",
    "-language:experimental.macros",
    // possibly deprecated options
    "-Ywarn-inaccessible"
  ),

  // Force building with Java 8
  initialize := {
    val required = "1.8"
    val current = sys.props("java.specification.version")
    assert(current == required, s"Unsupported build JDK: java.specification.version $current != $required")
  },

  // Linter
  scalacOptions ++= (CrossVersion.partialVersion(scalaVersion.value) match {
    case Some((2, majorVersion)) if majorVersion >= 11 =>
      Seq(
        // Turns all warnings into errors ;-)
        "-Xfatal-warnings",
        // Enables linter options
        "-Xlint:adapted-args", // warn if an argument list is modified to match the receiver
        "-Xlint:nullary-unit", // warn when nullary methods return Unit
        "-Xlint:inaccessible", // warn about inaccessible types in method signatures
        "-Xlint:nullary-override", // warn when non-nullary `def f()' overrides nullary `def f'
        "-Xlint:infer-any", // warn when a type argument is inferred to be `Any`
        "-Xlint:missing-interpolator", // a string literal appears to be missing an interpolator id
        "-Xlint:doc-detached", // a ScalaDoc comment appears to be detached from its element
        "-Xlint:private-shadow", // a private field (or class parameter) shadows a superclass field
        "-Xlint:type-parameter-shadow", // a local type parameter shadows a type already in scope
        "-Xlint:poly-implicit-overload", // parameterized overloaded implicit methods are not visible as view bounds
        "-Xlint:option-implicit", // Option.apply used implicit view
        "-Xlint:delayedinit-select", // Selecting member of DelayedInit
        "-Xlint:by-name-right-associative", // By-name parameter of right associative operator
        "-Xlint:package-object-classes", // Class or object defined in package object
        "-Xlint:unsound-match" // Pattern match may not be typesafe
      )
    case _                                             =>
      Seq.empty
  }),

  licenses := Seq("TykheLicense" -> url("http://tykhegaming.github.io/LICENSE.txt")),
  startYear := Some(2018),
  headerLicense := Some(HeaderLicense.Custom(
    """|Copyright (c) 2018 by Tykhe Gaming Private Limited
       |
       |Licensed under the Software License Agreement (the "License") of Tykhe Gaming Private Limited.
       |You may not use this file except in compliance with the License.
       |You may obtain a copy of the License at
       |
       |    http://tykhegaming.github.io/LICENSE.txt.
       |
       |NOTICE
       |ALL INFORMATION CONTAINED HEREIN IS, AND REMAINS THE PROPERTY OF TYKHE GAMING PRIVATE LIMITED.
       |THE INTELLECTUAL AND TECHNICAL CONCEPTS CONTAINED HEREIN ARE PROPRIETARY TO TYKHE GAMING PRIVATE LIMITED AND
       |ARE PROTECTED BY TRADE SECRET OR COPYRIGHT LAW. DISSEMINATION OF THIS INFORMATION OR REPRODUCTION OF THIS
       |MATERIAL IS STRICTLY FORBIDDEN UNLESS PRIOR WRITTEN PERMISSION IS OBTAINED FROM TYKHE GAMING PRIVATE LIMITED."""
      .stripMargin)),
  homepage := Some(url("https://github.com/wilsonks/scalafx-baccarat-display")),
  scmInfo := Some(ScmInfo(
    url("https://github.com/wilsonks/scalafx-baccarat-display"),
    "scm:git@github.com:wilsonks/scalafx-baccarat-display.git"
  )),

  // formatting
  scalafmtOnCompile := true,
  developers := List()
)


