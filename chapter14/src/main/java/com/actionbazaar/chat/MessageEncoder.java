/**
 *  SelectedItem.java
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

import java.io.StringWriter;
import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * Encodes the 
 * @author Ryan Cuprak
 */
public class MessageEncoder implements Encoder.Text<ChatMessage>  {

    /**
     * Initialize method
     * @param config - endpoint configuration
     */
    @Override
    public void init(EndpointConfig config) {
        // No implementation
    }

    /**
     * Releases any resources
     */
    @Override
    public void destroy() {
        // no-op
    }
    
    /**
     * Encodes the message
     * @param chatMessage - chat message
     * @return JSON encoded message
     * @throws EncodeException - thrown if there is a problem encoding the message
     */
    @Override
    public String encode(ChatMessage chatMessage) throws EncodeException {
        StringWriter writer = new StringWriter();
        try (JsonGenerator jsonWriter = Json.createGenerator(writer)) {
            jsonWriter.writeStartObject();
            jsonWriter.write("type","ChatMessage");
            jsonWriter.write("user",chatMessage.getUser());
            jsonWriter.write("message",chatMessage.getMessage());
            jsonWriter.writeEnd();
        }
        return writer.toString();
    }  
}
