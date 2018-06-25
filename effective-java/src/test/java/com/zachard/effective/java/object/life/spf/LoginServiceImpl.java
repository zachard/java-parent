/*
 *  Copyright 2015-2018 zachard, Inc.
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

package com.zachard.effective.java.object.life.spf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 客户端 -- 登录服务实现
 * 
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class LoginServiceImpl implements Service {
    
    private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    /**
     * 服务接口实现 -- 一个登录服务
     * 
     * @return  登录结果
     */
    @Override
    public String service() {
        logger.info("正在执行登录...");
        return "登录成功";
    }

}
