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
 *    -- 覆写{@link #equals(Object)}方法传递性相关测试对象
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class OverrideEqualsTransitivitySuperVo {
    
    private final int x;
    private final int y;
    
    /**
     * 对象的构造方法
     * 
     * @param x  属性
     * @param y  属性
     */
    public OverrideEqualsTransitivitySuperVo(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * 覆写父类的{@link #equals(Object)}方法
     * 
     * @param o   需要比较的对象
     * @return    对象逻辑相等-true, 否则-false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        
//        if (!(o instanceof OverrideEqualsTransitivitySuperVo)) {
//            return false;
//        }
        
        /*
         *  用getClass代替instanceOf判断, 实现父类与子类进行逻辑相等性测试返回false
         */
        if (o == null || o.getClass() != getClass()) {
            return false;
        }
        
        OverrideEqualsTransitivitySuperVo other = 
                (OverrideEqualsTransitivitySuperVo) o;
        
        return x == other.x && y == other.y;
    }

}
