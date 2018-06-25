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

import java.util.Date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <code>Effitive Java</code>第二章: 创建和销毁对象
 * 避免创建不必要的对象 -- 重用已知不会修改的对象测试类
 * 
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class ReuseVariableObjectTest {
    
    private static final Logger logger = LoggerFactory.getLogger(ReuseVariableObjectTest.class);
    
    /**
     * 测试10000次调用方法都要创建不必要对象的耗时
     */
    @Test
    public void testBad() {
        long start = System.currentTimeMillis();
        // 每次调用创建局部变量情况下, 调用10000次测试
        for (int i = 0; i < 10000; i++) {
            ReuseVariableObjectBad.isCentury20(new Date());
        }
        long end = System.currentTimeMillis();
        logger.info("每次调用方法创建局部变量方法调用10000次耗时: " + (end - start) + "ms");
    }
    
    /**
     * 测试10000次调用方法重用已知不会被修改的可变对象的耗时
     */
    @Test
    public void testSuit() {
        long start = System.currentTimeMillis();
        // 每次调用重用已知不会被修改的可变对象情况下, 调用10000次测试
        for (int i = 0; i < 10000; i++) {
            ResuseVariableObjectSuit.isCentury20(new Date());
        }
        long end = System.currentTimeMillis();
        logger.info("每次调用方法重用已知不会被修改的可变对象方法调用10000次耗时: " + (end - start) + "ms");
    }
    
    /**
     * 测试10000次调用方法第一次调用时初始化已知不会被修改的可变对象的耗时
     */
    @Test
    public void testLazy() {
        long start = System.currentTimeMillis();
        // 第一次调用方法时初始化已知不会被修改的可变对象, 调用10000次测试
        for (int i = 0; i < 10000; i++) {
            ResuseVariableObjectLazy.isCentury20(new Date());
        }
        long end = System.currentTimeMillis();
        logger.info("每次调用方法第一次调用方法时初始化已知不会被修改的可变对象方法调用10000次耗时: " + (end - start) + "ms");
    }

}
