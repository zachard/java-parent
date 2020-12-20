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

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zachard.java.hello.constant.Constant;

/**
 * 基于Apache Mina构建一个时间服务器
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class HelloMinaServer {
    
    private static Logger logger = LoggerFactory.getLogger(HelloMinaServer.class);
    
    private static final int READ_BUFFER_SIZE = 2048;
    private static final int BOTH_IDLE_TIME = 10;
    
    public static void main(String[] args) {
        IoAcceptor acceptor = new NioSocketAcceptor();  // 创建监听连接对象
        
        acceptor.getFilterChain().addLast(Constant.LOGGER_FILTER_NAME, new LoggingFilter());
        acceptor.getFilterChain().addLast(Constant.CODEC_FILTER_NAME, 
                new ProtocolCodecFilter(
                        new TextLineCodecFactory(Charset.forName(Constant.DEFAULT_CHARSET))));
        acceptor.setHandler(new HelloMinaServerHandler());
        acceptor.getSessionConfig().setReadBufferSize(READ_BUFFER_SIZE);
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, BOTH_IDLE_TIME);
        
        try {
            acceptor.bind(new InetSocketAddress(Constant.MINA_PORT));
        } catch (IOException e) {
            logger.error("启动时间服务异常, 异常信息为: {}", e.getMessage());
        }
    }

}
