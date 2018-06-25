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
 *    -- 父类通过构造器覆写{@link #clone()}方法, 而子类通过{@link super#clone()}
 *       方法覆写{@link #clone()}方法测试类
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class OverrideCloneByConstructorTest {
    
    private static Logger logger = LoggerFactory.getLogger(OverrideCloneByConstructorTest.class);
    
    /**
     * 当父类存在通过构造器覆写{@link #clone()}方法, 而子类通过{@link super#clone()}
     * 覆写{@link #clone()}方法测试方法
     */
    @Test
    public void testConstructor() {
        OverrideCloneByConstructorSub sub = new OverrideCloneByConstructorSub();
        OverrideCloneByConstructorSub vo = sub.clone();
        logger.info("父类通过构造器覆写clone方法, 子类通过super.clone方法覆写clone方法, "
                + "子类克隆的对象为: {}", vo);
    }

}
