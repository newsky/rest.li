/*
   Copyright (c) 2012 LinkedIn Corp.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/

/* $Id$ */
package test.r2.perf.server;

import com.linkedin.r2.sample.echo.EchoServiceImpl;
import com.linkedin.r2.sample.echo.rest.RestEchoServer;
import com.linkedin.r2.sample.echo.rpc.RpcEchoServer;
import com.linkedin.r2.transport.common.Server;
import com.linkedin.r2.transport.common.bridge.server.TransportDispatcher;
import com.linkedin.r2.transport.common.bridge.server.TransportDispatcherBuilder;

import java.net.URI;

/**
 * @author Chris Pettitt
 * @version $Revision$
 */
public abstract class AbstractPerfServerFactory
{
  public Server create(int port, URI echoUri)
  {
    final TransportDispatcher dispatcher = new TransportDispatcherBuilder()
          .addRpcHandler(echoUri, new RpcEchoServer(new EchoServiceImpl()))
          .addRestHandler(echoUri, new RestEchoServer(new EchoServiceImpl()))
          .build();

    return createServer(port, dispatcher);
  }

  protected abstract Server createServer(int port, TransportDispatcher dispatcher);
}
