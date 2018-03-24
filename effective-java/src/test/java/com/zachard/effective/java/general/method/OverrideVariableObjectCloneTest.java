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
 *    -- 当类中包含可变对象的域时, 应该递归调用clone方法来覆写clone方法测试类
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class OverrideVariableObjectCloneTest {
    
    private static Logger logger = LoggerFactory.getLogger(OverrideVariableObjectCloneTest.class);
    
    /**
     * 包含可变域的类覆写{@link #clone()}方法测试
     */
    @Test
    public void testVariableObjectClone() {
        OverrideVariableObjectClone vo = new OverrideVariableObjectClone();
        vo.push("Amy");
        vo.push("Bob");
        vo.push("Cary");
        OverrideVariableObjectClone cloneVo = vo.clone();
        logger.info("初始对象为: {}", vo);
        logger.info("克隆得到的对象为: {}", cloneVo);
        
        // 将克隆对象中的元素出栈
        cloneVo.pop();
        cloneVo.pop();
        logger.info("克隆对象中元素出栈后, 克隆对象为: {}", cloneVo);
        logger.info("克隆对象中元素出栈后, 初始对象为: {}", vo);
    }

}
