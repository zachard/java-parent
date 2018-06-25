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
 *    -- 通过{@link super#clone()}得到正确类型的子类
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class OverrideCloneBySuperCloneSub extends OverrideCloneBySuperCloneSuper {
    
    /**
     * 通过{@link super#clone()}方法覆写{@link #clone()}方法
     * 
     * <p>
     *     (1) 方法不会抛出{@link CloneNotSupportedException}异常是因为在超类的
     *         clone方法中已经捕获
     * </p>
     * 
     * @return OverrideCloneBySuperCloneSub对象实例
     */
    @Override
    public OverrideCloneBySuperCloneSub clone() {
        return (OverrideCloneBySuperCloneSub) super.clone();
    }

}
