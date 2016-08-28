package com.github.akzero53

import sbt._
import sbt.Keys._

case class CommonSettings(
  organization: String,
  version: String,
  scalaVersion: String,
  scalacOptions: Seq[String] = Seq("-deprecation", "-feature", "-unchecked")
)

class ProjectFactory(commonSettings: CommonSettings) {
  private val settings = Seq(
    organization := commonSettings.organization,
    version := commonSettings.version,
    scalaVersion := commonSettings.scalaVersion
  )

  def createRootProject(aProjectName: String) = Project(id = aProjectName, base = file("."))
    .settings(settings: _*)
    .settings(Defaults.coreDefaultSettings)
    .settings(name := aProjectName)

  def createSubProject(aProjectName: String) = Project(id = aProjectName, base = file(aProjectName))
    .settings(settings: _*)
    .settings(Defaults.coreDefaultSettings)
    .settings(name := aProjectName)
}

object ProjectFactory {
  def apply(commonSettings: CommonSettings) = new ProjectFactory(commonSettings)
}