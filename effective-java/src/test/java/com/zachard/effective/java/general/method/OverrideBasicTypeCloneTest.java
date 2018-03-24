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

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <code>Effitive Java</code>第三章: 对于所有对象都通用的方法
 * 谨慎地覆写{@link #clone()} 
 *    -- 当类中的每个域的值为基本类型或是指向一个不可变对象引用时, {@link super#clone()}
 *       返回的对象通常正是所需要的对象测试类
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class OverrideBasicTypeCloneTest {
    
    private static Logger logger = LoggerFactory.getLogger(OverrideBasicTypeCloneTest.class);
    
    /**
     * 通过{@link super#clone()}覆写只包含
     * 基本类型域或不可变对象域的{@link #clone()}方法测试方法
     */
    @Test
    public void testBasicTypeClone() {
        OverrideBasicTypeClone vo = new OverrideBasicTypeClone("zachard", 18);
        logger.info("初始对象为: {}", vo);
        OverrideBasicTypeClone cloneVo = vo.clone();
        logger.info("克隆出来的对象为: {}", cloneVo);
        
        // 修改克隆对象的属性
        cloneVo.setAge(20);
        cloneVo.setName("richard");
        logger.info("修改克隆对象的属性, 克隆出来的对象为: {}", cloneVo);
        logger.info("修改克隆对象的属性, 初始对象为: {}", vo);
    }

}
