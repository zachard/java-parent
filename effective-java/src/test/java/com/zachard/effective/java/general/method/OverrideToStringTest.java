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

package com.zachard.effective.java.general.method;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <code>Effitive Java</code>第三章: 对于所有对象都通用的方法
 * 始终要覆写{@link #toString()}方法 
 *    -- 覆写{@link #toString()}方法测试类
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class OverrideToStringTest {
    
    private static Logger logger = LoggerFactory.getLogger(OverrideToStringTest.class);
    
    /**
     * 覆写{@link #toString()}方法与否对比测试
     */
    @Test
    public void test() {
        OverrideToStringVo overrideVo = new OverrideToStringVo(408, 867, 5309);
        NotOverrideToString notOverrideVo = new NotOverrideToString(408, 867, 5309);
        
        Map<String, OverrideToStringVo> orMap = new HashMap<>(1);
        orMap.put("zachard", overrideVo);
        Map<String, NotOverrideToString> norMap = new HashMap<>(1);
        norMap.put("zachard", notOverrideVo);
        
        System.err.println("覆写toString方法后, 通过println打印的结果为: ");
        System.err.println(overrideVo);
        System.err.println("未覆写toString方法, 通过println打印的结果为: ");
        System.err.println(notOverrideVo);
        System.err.println("覆写toString方法后, 通过字符串联操作符(+)打印结果为: " + overrideVo);
        System.err.println("未覆写toString方法, 通过字符串联操作符(+)打印结果为: " + notOverrideVo);
        
        logger.info("覆写toString方法后, 打印Map集合的结果为: {}", orMap);
        logger.info("未覆写toString方法, 打印Map集合的结果为: {}", norMap);
    }

}
