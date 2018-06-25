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

/**
 * <code>Effitive Java</code>第三章: 对于所有对象都通用的方法
 * 覆写{@link #equals(Object)}方法时请遵守通用约定 
 *    -- 覆写{@link #equals(Object)}方法对称性相关测试对象
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class OverrideEqualsSymmetryVo {
    
    /**
     * 对象的布尔字面值
     */
    private final boolean symmet;
    
    /**
     * 包含布尔值的构造器
     * 
     * @param symmet  初始布尔值
     */
    public OverrideEqualsSymmetryVo(boolean symmet) {
        this.symmet = symmet;
    }
    
    /**
     * 覆写{@link #equals(Object)}方法
     * 
     * <p>
     *     此方法并不满足覆写{@link #equals(Object)}方法需要的对称性
     * </p>
     * 
     * @param o   需要比较的对象
     * @return  两个对象是否逻辑相等
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        
        if (o instanceof OverrideEqualsSymmetryVo) {
            return symmet;
        }
        
        return false;
    }

}
