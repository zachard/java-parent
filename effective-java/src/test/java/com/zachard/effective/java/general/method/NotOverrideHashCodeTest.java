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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <code>Effitive Java</code>第三章: 对于所有对象都通用的方法
 * 覆写{@link #equals(Object)}时总要覆写{@link #hashCode()} 
 *    -- 没有覆写{@link #hashCode()}时类无法与所有基于散列的集合一起正常工作测试类
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class NotOverrideHashCodeTest {
    
    private static Logger logger = LoggerFactory.getLogger(NotOverrideHashCodeTest.class);
    
    /**
     * 覆写{@link #equals(Object)}方法但未覆写{@link #hashCode()}的类
     * 无法与所有基于散列的集合一起正常工作测试
     */
    @Test
    public void test() {
        NotOverrideHashCodeVo vo1 = new NotOverrideHashCodeVo(010, 8888, 8888);
        NotOverrideHashCodeVo vo2 = new NotOverrideHashCodeVo(010, 8888, 8888);
        logger.info("覆写了equals方法但未覆写hashCode方法的两个对象通过equals方法比较是否相等: {}", 
                vo1.equals(vo2));
        logger.info("覆写了equals方法但未覆写hashCode方法的两个对象hashCode返回值是否相等: {}", 
                vo1.hashCode() == vo2.hashCode());
        
        List<NotOverrideHashCodeVo> voList = new ArrayList<>();
        voList.add(vo1);
        logger.info("覆写equals方法但未覆写hashCode方法, 将两个属性值完全相同的对象中一个加入到List集合, "
                + "判断另一个对象在List集合中是否存在: {}", voList.contains(vo2));
        
        Set<NotOverrideHashCodeVo> voSet = new HashSet<>();
        voSet.add(vo1);
        logger.info("覆写equals方法但未覆写hashCode方法, 将两个属性值完全相同的对象中一个加入到Set集合, "
                + "判断另一个对象在Set集合中是否存在: {}", voSet.contains(vo2));
        
        Map<NotOverrideHashCodeVo, String> voMap = new HashMap<>();
        voMap.put(vo1, "zachard");
        logger.info("覆写equals方法但未覆写hashCode方法, 将两个属性值完全相同的对象中一个作为Map的键, " 
                + "通过另外一个对象从Map中获取的值为: {}", voMap.get(vo2));
    }

}
