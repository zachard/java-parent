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

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <code>Effitive Java</code>第三章: 对于所有对象都通用的方法
 * 覆写{@link #equals(Object)}方法时请遵守通用约定 
 *    -- 覆写{@link #equals(Object)}方法一致性相关测试对象
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class OverrideEqualsConsistencyVo {
    
    private static final AtomicInteger counter = new AtomicInteger(0);
    
    /**
     * 属性
     */
    private String name;
    
    /**
     * 构造器
     * @param name
     */
    public OverrideEqualsConsistencyVo(String name) {
        this.name = name;
    }
    
    /**
     * 覆写{@link #equals(Object)}方法
     * 
     * <p>
     *     此方法不满足覆写{@link #equals(Object)}方法所需的一致性
     * </p>
     * 
     * @param  o    需要比较的对象
     * @return  对象逻辑相等返回true, 否则返回false
     */
    @Override
    public boolean equals(Object o) {
        int times = counter.incrementAndGet();
        
        if (this == o) {
            return true;
        }
        
        if (!(o instanceof OverrideEqualsConsistencyVo)) {
            return false;
        }
        
        OverrideEqualsConsistencyVo vo = (OverrideEqualsConsistencyVo) o;
        
        // 属性相等并且偶数次比较为true, 否则为false
        return this.name == vo.name && (times % 2 == 0);
    }

}
