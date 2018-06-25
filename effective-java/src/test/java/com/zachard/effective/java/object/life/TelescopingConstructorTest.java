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

/**
 * <code>Effitive Java</code>第二章: 创建和销毁对象
 * 遇到多个构造器参数时要考虑用构造器 -- 重叠构造器模式解决方法及JavaBean模式测试类
 * 
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class TelescopingConstructorTest {
    
    /**
     * JavaBean模式创建对象测试方法
     */
    @Test
    public void testJavaBeanMode() {
        TelescopingConstructor instance = new TelescopingConstructor();
        instance.setName("章三");
        instance.setMobile("18888888888");
        instance.setAge(30);
    }

}
