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
public class OverrideEqualsTransitivitySubVo extends OverrideEqualsTransitivitySuperVo {
    
    private final OverrideEqualsTransitivityColor color;
    
    /**
     * 覆写{@link #equals(Object)}方法子类构造器
     * 
     * @param x    属性
     * @param y    属性
     * @param color    属性
     */
    public OverrideEqualsTransitivitySubVo(int x, int y, OverrideEqualsTransitivityColor color) {
        super(x, y);
        this.color = color;
    }
    
    /**
     * 覆写{@link #equals(Object)}方法
     * 
     * @param o  需要比较的对象
     * @return   对象逻辑相等-true, 否则-false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        
        if (!(o instanceof OverrideEqualsTransitivitySuperVo)) {
            // 兼容对父类的比较, 子类也是属于父类型
            return false;
        }
        
        if (!(o instanceof OverrideEqualsTransitivitySubVo)) {
            // 直接返回false会导致对称性问题
//            return false;
            
            /*
             *  判断不为本类型, 那么就是属于父类型
             *  调用父类的equals方法比较从父类继承的属性, 不进行子类新增属性的比较(因为子类没有)
             *  不能写成 this.equals(o), 这样是调用子类的equals方法形成递归调用
             */
            return o.equals(this);
        }
        
        /*
         *  对象属于子类类型, 调用super.equals(o)方法比较从父类继承的属性是否相等
         *  再比较自己的属性是否相等
         */
        return super.equals(o) && ((OverrideEqualsTransitivitySubVo) o).color == color;
    }

}
