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

import javax.servlet.ServletContext

import nl.knaw.dans.lib.logging.DebugEnhancedLogging
import org.eclipse.jetty.ajp.Ajp13SocketConnector
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.ServletContextHandler
import org.scalatra._
import org.scalatra.servlet.ScalatraListener

import scala.util.Try

class EasyExampleModuleService(app: EasyExampleModuleApp) extends DebugEnhancedLogging {
  import logger._

  private val server = new Server(app.httpPort)
  private val context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS)
  context.addEventListener(new ScalatraListener() {
    override def probeForCycleClass(classLoader: ClassLoader): (String, LifeCycle) = {
      ("anonymous", new LifeCycle {
        override def init(context: ServletContext): Unit = {
          debug("Mounting servlet...")
          context.mount(new EasyExampleModuleServlet(app), "/")
          debug("Servlet mounted.")
        }
      })
    }
  })
  server.setHandler(context)
  info(s"HTTP port is ${app.httpPort}")

  def start(): Try[Unit] = Try {
    info("Starting HTTP service...")
    server.start()
  }

  def stop(): Try[Unit] = Try {
    info("Stopping HTTP service...")
    server.stop()
  }

  def destroy(): Try[Unit] = Try {
    server.destroy()
  }
}
