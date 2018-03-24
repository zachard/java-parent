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
 *    -- 子类通过{@link super#clone()}方式覆写{@link #clone()}方法
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class OverrideCloneByConstructorSub extends OverrideCloneByConstructorSuper {
    
    /**
     * 通过{@link super#clone()}方式覆写{@link #clone()}方法
     * 
     * @return  OverrideCloneByConstructorSub实例
     */
    @Override
    public OverrideCloneByConstructorSub clone() {
        return (OverrideCloneByConstructorSub) super.clone();
    }

}
