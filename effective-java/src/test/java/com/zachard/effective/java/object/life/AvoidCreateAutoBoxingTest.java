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
 * 避免创建不必要的对象 -- 自动装箱创建不必要的对象
 * 
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class AvoidCreateAutoBoxingTest {
    
    private static final Logger logger = LoggerFactory.getLogger(AvoidCreateAutoBoxingTest.class);
    
    /**
     * 测试存在自动装箱情况下, 将整型数据从0~Integer.MAX_VALUE累加耗时
     */
    @Test
    public void testAutoBoxing() {
        long start = System.currentTimeMillis();
        Long sum = 0L;
        // 将整型数据从0~Integer.MAX_VALUE累加
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        logger.info("存在自动装箱情况下, 将整型数据从0~Integer.MAX_VALUE累加耗时: {}ms", end - start);
    }
    
    /**
     * 测试不存在自动装箱情况下, 将整型数据从0~Integer.MAX_VALUE累加耗时
     */
    @Test
    public void testNoAutoBoxingBase() {
        long start = System.currentTimeMillis();
        long sum = 0L;
        // 将整型数据从0~Integer.MAX_VALUE累加
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        logger.info("不存在自动装箱情况下, 将整型数据从0~Integer.MAX_VALUE累加耗时: {}ms", end - start);
    }
    
    @Test
    public void testNoAutoBoxingBox() {
        long start = System.currentTimeMillis();
        Long sum = 0L;
        // 将整型数据从0~Integer.MAX_VALUE累加
        for (Integer i = 0; i < Integer.MAX_VALUE; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        logger.info("不存在自动装箱情况下, 将整型数据从0~Integer.MAX_VALUE累加耗时: {}ms", end - start);
    }
    
    @Test
    public void testIntegerOver128() {
        // integer1到integer4都发生了自动装箱操作, 但是JVM处于节省内存考虑, 会缓存-128到127的Integer对象
        Integer integer1 = 28;
        Integer integer2 = 28;
        System.err.println("integer1 == integer2: " + (integer1 == integer2));
        
        Integer integer3 = 129;
        Integer integer4 = 129;
        System.err.println("integer3 == integer4: " + (integer3 == integer4));
    }

}
