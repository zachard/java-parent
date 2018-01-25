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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <code>Effitive Java</code>第二章: 创建和销毁对象
 * 用私有构造器或者枚举类型强化Singleton属性 -- 饿汉模式测试类
 * 
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class SingletonHungryTest {
    
    private static final Logger logger = LoggerFactory.getLogger(SingletonHungryTest.class);
    
    /**
     * 饿汉模式单例测试方法
     * 
     * @throws ClassNotFoundException 
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     * @throws SecurityException 
     * @throws NoSuchMethodException 
     */
    @Test
    public void testBasic() throws ClassNotFoundException, InstantiationException, 
        IllegalAccessException, NoSuchMethodException, SecurityException {
        
        // 饿汉方式单例模式测试方法
        // logger.info(SingletonHungry.INSTANCE.description());
        logger.info(SingletonHungry.getInstance().description());
        
        // 通过反射机制创建不同于单例的实例
        Constructor<?>  constructor = SingletonHungry.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        try {
            logger.info("通过反射创建的实例为: " + constructor.newInstance());
        } catch (IllegalArgumentException | InvocationTargetException e) {
            logger.error("通过构造器创建对象异常: {}", e);
        }
        logger.info("类型本身的实例为: " + SingletonHungry.getInstance());
    }

}
