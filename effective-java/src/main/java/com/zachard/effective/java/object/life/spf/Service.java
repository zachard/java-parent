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
 *     通过静态工厂方法这一方式实现服务提供者框架 -- 服务接口(Service Interface)
 *     由服务提供者实现, 用于抽象服务提供者提供的服务
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public interface Service {
    
    /**
     * 定义一个服务提供的服务 -- 不同的服务接口实现可以提供不同的服务
     * 
     * @return   服务响应
     */
    String service();

}
