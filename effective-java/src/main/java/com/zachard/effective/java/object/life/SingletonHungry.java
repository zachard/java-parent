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

package com.zachard.effective.java.object.life;

/**
 * <code>Effitive Java</code>第二章: 创建和销毁对象
 * 用私有构造器或者枚举类型强化Singleton属性 -- 饿汉模式
 * 
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class SingletonHungry {
    
    /**
     * 饿汉模式创建单例模式
     */
    public static final SingletonHungry INSTANCE = new SingletonHungry();
    
    /**
     * 私有化构造器, 无法通过构造器来创建对象
     */
    private SingletonHungry() {
        
    }
    
    /**
     * 业务方法
     * 
     * @return   响应
     */
    public String description() {
        return "我是饿汉方式的单例模式";
    }

}
