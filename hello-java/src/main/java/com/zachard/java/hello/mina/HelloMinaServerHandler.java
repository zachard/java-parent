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

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zachard.java.hello.constant.Constant;

/**
 * 处理mina Time服务的Handler
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class HelloMinaServerHandler extends IoHandlerAdapter {
    
    private static Logger logger = LoggerFactory.getLogger(HelloMinaServerHandler.class);
    
    private static final String QUIT_FLAG = "quit";  // 服务退出标志
    
    @Override
    public void exceptionCaught(IoSession session, Throwable cause) {
        logger.error("服务处理出现异常, 异常信息为: {}", cause.getMessage());
    }
    
    @Override
    public void messageReceived(IoSession session, Object message) {
        String mess = message.toString();
        
        if (mess.trim().equalsIgnoreCase(QUIT_FLAG)) {
            session.closeNow();
            return;
        }
        
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat(Constant.YYYY_MM_DD_HH_MM_SS);
        session.write(df.format(calendar.getTime()));
        logger.info("{}, Mina Time服务执行了写操作, 收到的消息为: {}", df.format(calendar.getTime()), message);
    }
    
    @Override
    public void sessionIdle(IoSession session, IdleStatus status) {
        logger.info("当前为: {}状态的session个数为: {}", 
                status.toString(), session.getIdleCount(status));
    }

}
