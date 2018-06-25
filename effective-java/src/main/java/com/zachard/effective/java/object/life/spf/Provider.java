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
 * <code>Effitive Java</code>第二章: 创建和销毁对象
 * 采用静态工程方法创建对象相比于构造器的优势 -- 静态工厂方法可以返回原类型的任何子类型的对象
 * 
 * <pre>
 *     通过静态工厂方法这一方式实现服务提供者框架 -- 服务提供者接口(Service Provider Interface)
 *     由服务提供者实现, 用来抽象服务提供者(这个组件在服务提供者框架中不是必须的, 不存在时可以通过服务实现类名注册)
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public interface Provider {
    
    /**
     * 定义服务提供者如何实现提供服务实现
     * 
     * @return   服务方对象
     */
    Service newService();

}
