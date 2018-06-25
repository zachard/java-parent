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

/**
 * 客户端 -- 登录服务提供者接口的实现, 用于提供一个登录服务的实现
 * 
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class LoginProviderImpl implements Provider {

    /**
     * 服务提供者提供服务的方式
     * 
     * @return  登录服务实现
     */
    @Override
    public Service newService() {
        return new LoginServiceImpl();
    }

}
