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

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoConnector;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zachard.java.hello.constant.Constant;

/**
 * description...
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class HelloMinaClient {
    
    private static Logger logger = LoggerFactory.getLogger(HelloMinaClient.class);
    private static int CONNECT_TIMEOUT_MILLS = 30000;
    
    public static void main(String[] args) {
        IoConnector connector = new NioSocketConnector();
        connector.setConnectTimeoutMillis(CONNECT_TIMEOUT_MILLS);
        connector.getFilterChain().addLast(Constant.CODEC_FILTER_NAME, 
                new ProtocolCodecFilter(
                        new TextLineCodecFactory(Charset.forName(Constant.DEFAULT_CHARSET))));
        connector.setHandler(new HelloMinaClientHandler("你好! \r\n 大家好!"));
        connector.connect(new InetSocketAddress("localhost", Constant.MINA_PORT));
    }

}
