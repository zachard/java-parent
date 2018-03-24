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
 *    -- 通过{@link super#clone()}得到正确类型测试类
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class OverrideCloneBySuperCloneTest {
    
    private static Logger logger = LoggerFactory.getLogger(OverrideCloneBySuperCloneTest.class);
    
    /**
     * 测试通过{@link super#clone()}覆写{@link #clone()}克隆对象
     * 
     */
    @Test
    public void testSuperClone() {
        OverrideCloneBySuperCloneSub sub = new OverrideCloneBySuperCloneSub();
        OverrideCloneBySuperCloneSub vo = sub.clone();
        logger.info("通过super.clone方式覆写clone方法得到克隆的对象为: {}", vo);
    }

}
