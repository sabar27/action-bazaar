/**
 * SupportChat.java
 *  EJB 3 in Action
 *  Book: http://manning.com/panda2/
 *  Code: http://code.google.com/p/action-bazaar/
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.actionbazaar.chat;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.websocket.EndpointConfig;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * Chat Support - user must be in the CSR role
 * @author Ryan Cuprak
 */
@ServerEndpoint(value="/support",encoders = {MessageEncoder.class,CommandEncoder.class})
public class SupportChat implements Serializable {
   
    /**
     * Logger
     */
    private static final Logger logger = Logger.getLogger("ChatSupport");
    
    /**
     * Serial UID
     */
    private static final long serialVersionUID = 4588122135773589676L;
    
    /**
     * Chat server
     */
    @Inject
    private ChatServer chatServer;
    
    /**
     * Opens 
     * @param session
     * @param conf 
     */
    @OnOpen
    public void open(Session session, EndpointConfig conf) { 
        logger.info("Connection opened.");
        chatServer.customServiceRepConnected(session);
    }
    
    /**
     * Sends a message 
     * @return Text to echo
     */
    @OnMessage
    public String sendMessage(Session session, String message) {
        logger.log(Level.INFO, "Got message: {0}", message);
        chatServer.handleMessage(session, message);
        return message;
    }
    
    @OnError
    public void error(Session session, Throwable error) { 
        logger.info("Handling errors: " + error.getMessage());
    }    
}
