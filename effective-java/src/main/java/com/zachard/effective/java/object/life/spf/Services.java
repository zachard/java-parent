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

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <code>Effitive Java</code>第二章: 创建和销毁对象
 * 采用静态工程方法创建对象相比于构造器的优势 -- 静态工厂方法可以返回原类型的任何子类型的对象
 * 
 * <pre>
 *     通过静态工厂方法这一方式实现服务提供者框架 
 *       -- 提供者注册API (Provider Registration API)和服务访问API (Service Access API)
 *     提供者注册API: 客户端用于注册服务提供者接口的实现
 *     服务访问API: 客户端用于获取服务接口的实现
 *     注意: 注册的是服务提供者实现, 获取到的服务接口实现 (为什么不直接注册服务接口实现?) 
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class Services {
    
    private static final Logger logger = LoggerFactory.getLogger(Services.class);
    
    /**
     * 私有化构造器,防止实例化对象
     */
    private Services() {
        
    }
    
    /**
     * 服务提供者实现与服务名称之间的映射
     */
    private static final Map<String, Provider> providers = 
            new ConcurrentHashMap<>();
    
    /**
     * 默认的服务提供者实现名称(默认的服务接口实现名称)
     */
    private static final String DEFAULT_PROVIDER_NAME = "<def>";
    
    /**
     * 提供者注册API -- 将一个服务提供者实现注册为默认的服务提供者实现
     * 
     * @param provider   需要注册的服务提供者实现
     */
    public static void registerDefaultprovider(Provider provider) {
        registerProvider(DEFAULT_PROVIDER_NAME, provider);
    }
    
    /**
     * 提供者注册API -- 将一个服务提供者实现与指定的服务名称相关联
     * 
     * @param name      服务名称
     * @param provider  服务提供者实现
     */
    public static void registerProvider(String name, Provider provider) {
        providers.put(name, provider);
    }
    
    /**
     * 服务访问API -- 获取一个默认的服务接口实现
     * 
     * @return   默认的服务接口实现
     */
    public static Service newInstance() {
        return newInstance(DEFAULT_PROVIDER_NAME);
    }
    
    /**
     * 服务访问API -- 根据指定的服务名称获取对应的服务接口实现
     * 
     * @param name    服务名称
     * @return        服务实现
     */
    public static Service newInstance(String name) {
        Provider provider = providers.get(name);
        
        if (provider == null) {
            logger.info("服务名称为{}的服务不存在", name);
            throw new IllegalArgumentException("服务名称为" + name + "的服务不存在");
        }
        
        return provider.newService();
    }

}
