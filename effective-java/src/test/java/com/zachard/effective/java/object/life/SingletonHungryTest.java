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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
     * 序列化文件路径
     */
    private static final String SERIALIZABLE_FILE_PATH = "serializable.txt";
    
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
        //logger.info(SingletonHungry.INSTANCE.INSTANCE.INSTANCE.description());
        //logger.info(SingletonHungry.INSTANCE.description());
        logger.info(SingletonHungry.getInstance().description());
        
        // 通过反射机制创建不同于单例的实例
        Constructor<?>  constructor = SingletonHungry.class.getDeclaredConstructor();
        // 设置构造器的可访问性
        constructor.setAccessible(true);
        try {
            logger.info("通过反射创建的实例为: " + constructor.newInstance());
        } catch (IllegalArgumentException | InvocationTargetException e) {
            logger.error("通过构造器创建对象异常: {}", e);
        }
        logger.info("类型公有静态final成员为: " + SingletonHungry.getInstance());
    }
    
    /**
     * 单例模式对象序列化测试方法
     */
    @Test
    public void testSerializable() {
        // 先将单例模式的唯一对象序列化到文件
        ObjectOutputStream output = null;
        try {
            output = new ObjectOutputStream(new FileOutputStream(SERIALIZABLE_FILE_PATH));
            output.writeObject(SingletonHungry.getInstance());
        } catch (IOException e) {
            logger.error("将对象序列化到文件异常: {}", e);
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    logger.error("关闭输出流失败: {}", e);
                }
            }
        }
    }
    
    /**
     * 单例模式对象反序列化测试方法
     */
    @Test
    public void testDeserialize() {
        File file = new File(SERIALIZABLE_FILE_PATH);
        ObjectInputStream input = null;
        // 从文件中读取单例模式序列化信息并对对象进行反序列化
        try {
            input = new ObjectInputStream(new FileInputStream(file));
            SingletonHungry singleton = (SingletonHungry) input.readObject();
            logger.info("单例模式中的实例为: {}", SingletonHungry.getInstance());
            logger.info("反序列化得到的实例为: {}", singleton);
        } catch (Exception e) {
            logger.error("对对象进行反序列化失败: {}", e);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    logger.error("关闭输入流失败: {}", e);
                }
            }
        }
    }

}
