/**
 * Copyright (C) 2017 DANS - Data Archiving and Networked Services (info@dans.knaw.nl)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package nl.knaw.dans.easy.example

import java.nio.file.{ Files, Paths }

import nl.knaw.dans.lib.logging.DebugEnhancedLogging
import org.apache.commons.configuration.PropertiesConfiguration

import scala.io.Source

class EasyExampleModuleApp extends DebugEnhancedLogging {
  import logger._
  private val home = Paths.get(System.getProperty("app.home"))
  debug(s"app.home = $home")
  val version: String = resource.managed(scala.io.Source.fromFile(home.resolve("bin/version").toFile)).acquireAndGet {
    _.mkString
  }
  debug(s"version = $version")
  private val cfg = Seq(
    Paths.get(s"/etc/opt/dans.knaw.nl/easy-example-module/"),
    home.resolve("cfg")).find(Files.exists(_)).getOrElse { throw new IllegalStateException("No configuration directory found")}
  debug(s"Found configuration directory at $cfg")

  private val properties = new PropertiesConfiguration(cfg.resolve("application.properties").toFile)
  info(s"Reading configuration from ${properties.getFile}")

  val httpPort: Int = properties.getInt("daemon.http.port")

  // Wiring and initialization here.
}