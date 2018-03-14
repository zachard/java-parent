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
 *    -- 覆写{@link #equals(Object)}方法传递性测试类
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class OverrideEqualsTransitivityTest {
    
    private static Logger logger = LoggerFactory.getLogger(OverrideEqualsTransitivityTest.class);

    /**
     * 覆写{@link #equals(Object)}方法违反传递性测试
     */
    @Test
    public void violationTransitivityTest() {
        OverrideEqualsTransitivitySubVo subVo1 = 
                new OverrideEqualsTransitivitySubVo(1, 2, 
                        OverrideEqualsTransitivityColor.RED);
        OverrideEqualsTransitivitySuperVo superVo = 
                new OverrideEqualsTransitivitySuperVo(1, 2);
        OverrideEqualsTransitivitySubVo subVo2 = 
                new OverrideEqualsTransitivitySubVo(1, 2, 
                        OverrideEqualsTransitivityColor.GREEN);
        logger.info("x.equals(y)返回结果为: {}, y.equals(x)返回结果为: {}, 是否满足对称性: {}", 
                subVo1.equals(superVo), superVo.equals(subVo1), 
                (subVo1.equals(superVo) == superVo.equals(subVo1)));
        logger.info("y.equals(z)返回结果为: {}, z.equals(x)返回结果为: {}, 是否满足对称性: {}", 
                superVo.equals(subVo2), subVo2.equals(superVo), 
                (superVo.equals(subVo2) == subVo2.equals(superVo)));
        logger.info("x.equals(y)返回结果为: {}, y.equals(z)返回结果为: {}, x.equals(z)"
                + "返回的结果为: {}, 是否满足对称性: {}", subVo1.equals(superVo), superVo.equals(subVo2), 
                subVo1.equals(subVo2), 
                (subVo1.equals(superVo) && superVo.equals(subVo2)) == (subVo1.equals(subVo2)));
        
        
        List<OverrideEqualsTransitivitySuperVo> voList = new ArrayList<>();
        voList.add(subVo1);
        voList.add(superVo);
        logger.info("equals方法违反传递性时, 当向List集合中加入A、B对象, 测试C对象是否在集合中: {}", 
                voList.contains(subVo2));
        
        Set<OverrideEqualsTransitivitySuperVo> voSet = new HashSet<>();
        voSet.add(subVo1);
        voSet.add(superVo);
        logger.info("equals方法违反传递性时, 当向Set集合加入A、B对象, 测试C对象是否在集合中: {}", 
                voSet.contains(subVo2));
    }
}
