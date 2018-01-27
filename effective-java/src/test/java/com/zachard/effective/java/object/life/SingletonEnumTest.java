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
 * 用私有构造器或者枚举类型强化Singleton属性 -- 枚举强化Singleton属性测试类
 * 
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class SingletonEnumTest {
    
    private static final Logger logger = LoggerFactory.getLogger(SingletonEnumTest.class);
    
    /**
     * 序列化文件路径
     */
    private static final String SERIALIZABLE_FILE_PATH = "serializable.txt";
    
    /**
     * 测试通过反射创建枚举类型的单例模式对象
     * 
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    @Test
    public void testBasic() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        logger.info(SingletonEnum.INSTANCE.description());
        logger.info("枚举类型单例模式的对象为: {}", SingletonEnum.INSTANCE);
        
        // 获取枚举类型中的构造器
        Constructor<?>[] constructors = SingletonEnum.class.getDeclaredConstructors();
        
        for (Constructor<?> constructor : constructors) {
            constructor.setAccessible(true);
            // constructor.newInstance()调用抛出IllegalArgumentException异常
            logger.info("通过反射创建的对象为: {}", constructor.newInstance());
        }
    }
    
    /**
     * 枚举累强化Singleton属性序列化测试方法
     */
    @Test
    public void testSerializable() {
     // 先将单例模式的唯一对象序列化到文件
        ObjectOutputStream output = null;
        try {
            output = new ObjectOutputStream(new FileOutputStream(SERIALIZABLE_FILE_PATH));
            output.writeObject(SingletonEnum.INSTANCE);
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
     * 枚举类型强化Singleton属性反序列化测试方法
     */
    @Test
    public void testDeserialize() {
        File file = new File(SERIALIZABLE_FILE_PATH);
        ObjectInputStream input = null;
        // 从文件中读取单例模式序列化信息并对对象进行反序列化
        try {
            input = new ObjectInputStream(new FileInputStream(file));
            SingletonEnum singleton = (SingletonEnum) input.readObject();
            logger.info("单例模式中的实例为: {}", SingletonEnum.INSTANCE);
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
