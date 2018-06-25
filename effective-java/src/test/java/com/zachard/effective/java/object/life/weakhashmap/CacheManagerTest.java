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

package com.zachard.effective.java.object.life.weakhashmap;

import org.junit.Test;

/**
 * 缓存管理模拟类
 * 
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class CacheManagerTest {
    
    /**
     * 测试强引用时导致内存溢出
     */
    @Test
    public void testPut() {
        CacheManager cacheManager = new CacheManager();
        
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            System.err.println(i);
            cacheManager.add("key" + i, new Object());
        }
    }

}
