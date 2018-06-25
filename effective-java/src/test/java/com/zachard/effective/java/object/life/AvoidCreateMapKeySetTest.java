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

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <code>Effitive Java</code>第二章: 创建和销毁对象
 * 避免创建不必要的对象 -- 测试{@link HashMap#keySet()}是否每次都返回相同对象
 * 
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class AvoidCreateMapKeySetTest {
    
    private static final Logger logger = LoggerFactory.getLogger(AvoidCreateMapKeySetTest.class);
    
    /**
     * 测试{@link HashMap#keySet()}方法在key值改变的情况下返回的对象是否相同
     */
    @Test
    public void testHashMapKeySet() {
        Map<String, Object> person = new HashMap<>(16);
        person.put("name", "zachard");
        person.put("age", 18);
        Set<String> keySet1 = person.keySet();
        logger.info("第一次获取到HashMap的keySet对象为: {}", person.keySet());
        person.put("address", "RenMin Road");
        Set<String> keySet2 = person.keySet();
        logger.info("为Map添加属性获取到HashMap的keySet对象为: {}", person.keySet());
        logger.info("修改HashMap属性前后的keySet对象是否相同: {}", (keySet1 == keySet2));
    }

}
