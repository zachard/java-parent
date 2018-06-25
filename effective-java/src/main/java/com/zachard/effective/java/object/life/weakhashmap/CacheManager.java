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

import java.util.Map;
import java.util.WeakHashMap;

/**
 * 缓存管理模拟类
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class CacheManager {
    
//    private Map<String, Object> cache = new HashMap<>();
    private Map<String, Object> cache = new WeakHashMap<>();
    
    /**
     * 将对象加入缓存
     * 
     * @param key
     * @param object
     */
    public void add(String key, Object object) {
        cache.put(key, object);
    }
    
    /**
     * 根据key获取缓存中的对象
     * 
     * @param key
     * @return
     */
    public Object get(String key) {
        return cache.get(key);
    }
    
    /**
     * 根据key删除缓存中的对象
     * 
     * @param key
     */
    public void remove(String key) {
        cache.remove(key);
    }

}
