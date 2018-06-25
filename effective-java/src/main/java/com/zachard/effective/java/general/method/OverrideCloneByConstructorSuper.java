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
 * 谨慎地覆写{@link #clone()} 
 *    -- 超类通过构造器返回克隆对象
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class OverrideCloneByConstructorSuper implements Cloneable {
    
    /**
     * 通过构造器的方式覆写{@link #clone()}方法
     * 
     * @return  OverrideCloneByConstructorSuper类型实例
     */
    @Override
    public OverrideCloneByConstructorSuper clone() {
        return new OverrideCloneByConstructorSuper();
    }

}
