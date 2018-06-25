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

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zachard.effective.java.object.life.BuilderPattern.Builder;

/**
 * <code>Effitive Java</code>第二章: 创建和销毁对象
 * 遇到多个构造器参数时要考虑用构造器 -- Builder模式解决办法测试类
 * 
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class BuilderPatternTest {
    
    private static final Logger logger = LoggerFactory.getLogger(BuilderPatternTest.class);
    
    /**
     * 测试Builder模式构造对象
     */
    @Test
    public void testBasic() {
        Builder builder = new Builder("章三", "18888888888");
        BuilderPattern pattern = builder.age(23)
                .sex(false).address("皇后街666号").build();
        logger.info("姓名为: " + pattern.getName() + ", 住址为: " + pattern.getAddress());
    }

}
