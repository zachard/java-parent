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
 * 消除过期的对象引用 -- 过期的对象引用容易引发内存泄漏测试
 * 
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class ObsoleteReferenceOutOfMemoryTest {
    
    @Test
    public void testOutOfMemory() {
        ObsoleteReferenceOutOfMemory stack = new ObsoleteReferenceOutOfMemory();
        
        while(true) {
            // 1m的内存最多push 17407 个Object
            stack.push(new Object());
            System.err.println(stack.size());
        }
    }

}
