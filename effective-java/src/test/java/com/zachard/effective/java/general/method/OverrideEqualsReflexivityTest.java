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
 *    -- 覆写{@link #equals(Object)}方法自反性测试类
 * 
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class OverrideEqualsReflexivityTest {
    
    private static final Logger logger = LoggerFactory.getLogger(OverrideEqualsReflexivityTest.class);
    
    /**
     * 覆写{@link #equals(Object)}方法时违反自反性测试
     */
    @Test
    public void violationReflexivityTest() {
        OverrideEqualsReflexivityVo vo = new OverrideEqualsReflexivityVo();
        logger.info("当前对象的HashCode值为: {}", vo.hashCode());
        logger.info("覆写equals方法后, 自反性是否满足: {}", vo.equals(vo));
        
        List<OverrideEqualsReflexivityVo> voList = new ArrayList<>();
        voList.add(vo);
        voList.add(vo);
        logger.info("将equals方法不满足自反性的对象两次添加到List集合中, 集合元素的个数为: {}", 
                voList.size());
        logger.info("将equals方法不满足自反性的对象两次添加到List集合中, 集合是否存在该元素: {}", 
                voList.contains(vo));
        
        Set<OverrideEqualsReflexivityVo> voSet = new HashSet<>();
        voSet.add(vo);
        voSet.add(vo);
        logger.info("将equals方法不满足自反性的对象两次添加到Set集合中, 集合元素个数为: {}", 
                voSet.size());
        logger.info("将equals方法不满足自反性的对象两次添加到Set集合中, 集合是否存在该元素: {}",
                voSet.contains(vo));
        
        String str = new String("zahcard");
        
        List<String> strList = new ArrayList<>();
        strList.add(str);
        strList.add(str);
        logger.info("将equals方法满足自反性的对象两次添加到List集合中, 集合元素个数为: {}", 
                strList.size());
        logger.info("将equals方法满足自反性的对象两次添加到List集合中, 集合是否存在该元素: {}", 
                strList.contains(str));
        
        Set<String> strSet = new HashSet<>();
        strSet.add(str);
        strSet.add(str);
        logger.info("将equals方法满足自反性的对象两次添加到Set集合中, 集合元素个数为: {}", 
                strSet.size());
        logger.info("将equals方法满足自反性的对象两次添加到Set集合中, 集合是否存在该元素: {}", 
                strSet.contains(str));
    }

}
