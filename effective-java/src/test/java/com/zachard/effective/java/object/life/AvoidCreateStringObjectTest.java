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

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <code>Effitive Java</code>第二章: 创建和销毁对象
 * 避免创建不必要的对象 -- 避免通过{@code new String()}创建不必要的字符串对象测试
 * 
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class AvoidCreateStringObjectTest {
    
    private static final Logger logger = LoggerFactory.getLogger(AvoidCreateStringObjectTest.class);
    
    /**
     * 测试通过String构造器创建100000000个字符串耗时方法
     */
    @Test
    public void testStringConstructor() {
        long start = System.currentTimeMillis();
        // 通过String构造器创建100000000个字符串对象耗时测试
        for (int i = 0; i < 100000000; i++) {
            String s = new String("zachard");
//            logger.info(s);
        }
        long end = System.currentTimeMillis();
        logger.info("通过String构造器创建100000000个字符串耗时为: {}ms", (end - start));
    }
    
    /**
     * 测试通过字符串常量池创建100000000个字符串耗时
     */
    @Test
    public void testStringConstantPool() {
        long start = System.currentTimeMillis();
        // 通过字符串常量池创建100000000个字符串对象耗时测试
        for (int i = 0; i < 100000000; i++) {
            String s = "zachard";
//            logger.info(s);
        }
        long end = System.currentTimeMillis();
        logger.info("通过字符串常量池创建100000000个字符串对象耗时为: {}ms", (end - start));
    }

}
