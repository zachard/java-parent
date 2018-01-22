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

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 服务提供者框架客户端
 * 
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class ServiceProviderTest {
    
    private static final Logger logger = LoggerFactory.getLogger(ServiceProviderTest.class);
    
    /**
     * 服务提供者框架测试
     */
    @Test
    public void testBasic() {
        // 提供者注册API, 注册相关的服务提供者
        Services.registerDefaultprovider(new DefaultProviderImpl());
        Services.registerProvider("login", new LoginProviderImpl());
        
        // 服务访问API, 获取对应的服务
        Service defaultService = Services.newInstance();
        Service loginService = Services.newInstance("login");
        
        // 调用服务执行相关操作
        logger.info("默认的服务实现执行结果为: {}", defaultService.service());
        logger.info("登录服务的执行结果为: {}", loginService.service());
    }
    
}
