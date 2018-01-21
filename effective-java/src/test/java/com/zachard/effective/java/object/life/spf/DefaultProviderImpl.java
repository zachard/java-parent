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
 * 客户端 -- 默认的服务提供者实现, 用于提供默认的服务
 * 
 * <pre>
 *     (1) 服务提供者接口的实现与服务接口的实现是一对一的关系吗? 
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class DefaultProviderImpl implements Provider {

    /**
     * 服务提供者提供服务的方式
     * 
     * @return  默认的服务接口实现
     */
    @Override
    public Service newService() {
        return new DefaultServiceImpl();
    }

}
