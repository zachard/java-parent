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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <code>Effitive Java</code>第三章: 对于所有对象都通用的方法
 * 覆写{@link #equals(Object)}方法时请遵守通用约定 
 *    -- 覆写{@link #equals(Object)}方法一致性测试类
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class OverrideEqualsConsistencyTest {
    
    private static Logger logger = LoggerFactory.getLogger(OverrideEqualsConsistencyTest.class);
    
    /**
     * 覆写{@link #equals(Object)}方法违反一致性测试
     */
    @Test
    public void violationConsistencyTest() {
        OverrideEqualsConsistencyVo vo1 = new OverrideEqualsConsistencyVo("zachard");
        OverrideEqualsConsistencyVo vo2 = new OverrideEqualsConsistencyVo("zachard");
        boolean firstEqual = vo1.equals(vo2);
        boolean secondEqual = vo1.equals(vo2);
        logger.info("x.equals(y)第一次返回结果为: {}, 第二次返回结果为: {}, 是否满足一致性: {}", 
                firstEqual, secondEqual, (firstEqual == secondEqual));
        
        List<OverrideEqualsConsistencyVo> voList = new ArrayList<>();
        voList.add(vo1);
        logger.info("当equals方法不满足一致性时, 将x对象添加到List集合, 第一次判断y对象是否在集合之中: {}", 
                voList.contains(vo2));
        logger.info("当equals方法不满足一致性时, 将x对象添加到List集合, 第二次判断y对象是否在集合之中: {}", 
                voList.contains(vo2));
        
        Set<OverrideEqualsConsistencyVo> voSet = new HashSet<>();
        voSet.add(vo1);
        logger.info("当equals方法不满足一致性时, 将x对象添加到Set集合, 第一次判断y对象是否在集合之中: {}", 
                voSet.contains(vo2));
        logger.info("当equals方法不满足一致性时, 将x对象添加到Set集合, 第二次判断y对象是否在集合之中: {}", 
                voSet.contains(vo2));
    }

}
