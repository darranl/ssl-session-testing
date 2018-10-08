/*
 * Copyright 2018 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wildfly.security;

import static org.wildfly.security.Common.createServerSSLContext;
import static org.wildfly.security.Common.outputSessionInfo;

import java.net.Socket;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
/**
 *
 * @author <a href="mailto:darran.lofthouse@jboss.com">Darran Lofthouse</a>
 */
public class Server {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        System.out.println("\n * * Server * *\n");
        SSLContext sslContext = createServerSSLContext();

        SSLServerSocketFactory serverSocketFactory = sslContext.getServerSocketFactory();
        SSLServerSocket serverSocket = (SSLServerSocket) serverSocketFactory.createServerSocket(12345);
        serverSocket.setWantClientAuth(true);

        System.out.println("\n * * About to start accepting. * *\n");

        while (true) {
            outputSessionInfo(sslContext, true);

            SSLSocket socket = (SSLSocket) serverSocket.accept();
            socket.startHandshake();


        }



    }

}
