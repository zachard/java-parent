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

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <code>Effitive Java</code>第三章: 对于所有对象都通用的方法
 * 覆写{@link #equals(Object)}方法时请遵守通用约定 
 *    -- 覆写{@link #equals(Object)}方法对称性测试类
 * 
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class OverrideEqualsSymmetryTest {
    
    private static Logger logger = LoggerFactory.getLogger(OverrideEqualsSymmetryTest.class);
    
    /**
     * 覆写{@link #equals(Object)}方法违反对称性测试
     */
    @Test
    public void violationSymmetryTest() {
        OverrideEqualsSymmetryVo equalsVo = new OverrideEqualsSymmetryVo(true);
        OverrideEqualsSymmetryVo notEqualsVo = new OverrideEqualsSymmetryVo(false);
        logger.info("x.equals(y)返回结果为: {}, y.equals(x)返回结果为: {}, 是否满足对称性: {}", 
                equalsVo.equals(notEqualsVo), notEqualsVo.equals(equalsVo), 
                (equalsVo.equals(notEqualsVo) == notEqualsVo.equals(equalsVo)));
        
        List<OverrideEqualsSymmetryVo> equalsList = new ArrayList<>();
        equalsList.add(equalsVo);
        logger.info("equals方法违反对称性时, 当向List集合中加入A对象, 测试B对象是否在集合中: {}", 
                equalsList.contains(notEqualsVo));
        
        Set<OverrideEqualsSymmetryVo> equalsSet = new HashSet<>();
        equalsSet.add(equalsVo);
        logger.info("equals方法违反对称性时, 当向Set集合中加入A对象, 测试B对象是否在集合中: {}", 
                equalsSet.contains(notEqualsVo));
        
        List<OverrideEqualsSymmetryVo> notEqualsList = new ArrayList<>();
        notEqualsList.add(notEqualsVo);
        logger.info("equals方法违反对称性时, 当向List集合中加入B对象, 测试A对象是否在集合中: {}", 
                notEqualsList.contains(equalsVo));
        
        Set<OverrideEqualsSymmetryVo> notEqualsSet = new HashSet<>();
        notEqualsSet.add(notEqualsVo);
        logger.info("equals方法违反对称性时, 当向Set集合中加入B对象, 测试A对象是否在集合中: {}", 
                notEqualsSet.contains(equalsVo));
    }
    
    /**
     * {@link Timestamp}与{@link Date}类型覆写{@link #equals(Object)}
     * 方法违反对称性测试
     */
    @Test
    public void timestampViolationSymmetryTest() {
        Timestamp timestamp = new Timestamp(1521172126);
        Date date = new Date(1521172126);
        logger.info("Timestamp与Date违反equals方法对称性测试, timestamp.equals(date)返回: {}, "
                + "date.equals(timestampl)返回: {}, 是否遵守对称性: {}", 
                timestamp.equals(date), date.equals(timestamp), 
                (timestamp.equals(date) == date.equals(timestamp)));
    }
}
