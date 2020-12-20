/*
 *  Copyright 2015-2020 zachard, Inc.
 *
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

package com.zachard.java.hello.mina;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * description...
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class HelloMinaClientHandler extends IoHandlerAdapter {
    
    private static Logger logger = LoggerFactory.getLogger(HelloMinaClientHandler.class);
    
    private final String message;
    
    public HelloMinaClientHandler( String message) {
        this.message = message;
    }
    
    @Override
    public void sessionOpened(IoSession session) {
        session.write(message);
    }
    
    @Override
    public void messageReceived(IoSession session, Object message) {
        logger.info("客户端收到服务端发送过来的消息是: {}", message.toString());
    }

}
