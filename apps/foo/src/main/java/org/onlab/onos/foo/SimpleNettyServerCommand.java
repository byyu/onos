/*
 * Copyright 2014 Open Networking Laboratory
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.onlab.onos.foo;

import static org.onlab.onos.foo.SimpleNettyServer.startStandalone;

import org.apache.karaf.shell.commands.Argument;
import org.apache.karaf.shell.commands.Command;
import org.onlab.onos.cli.AbstractShellCommand;

/**
 * Starts the Simple Netty server.
 */
@Command(scope = "onos", name = "simple-netty-server",
         description = "Starts simple Netty server")
public class SimpleNettyServerCommand extends AbstractShellCommand {

    //FIXME: Replace these with parameters for
    @Argument(index = 0, name = "port", description = "listen port",
              required = false, multiValued = false)
    String port = "8081";

    @Override
    protected void execute() {
        try {
            startStandalone(new String[]{port});
        } catch (Exception e) {
            error("Unable to start server %s", e);
        }
    }

}
