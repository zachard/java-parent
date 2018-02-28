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

/**
 * 终结方法守卫者子类
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class FinalizerGuardianSub extends FinalizerGuardianParent {
    
    /**
     * 子类覆写终结方法, 但不在终结方法中调用父类的终结方法
     */
    @Override
    protected void finalize() throws Throwable {
        System.err.println("子类覆写的终结方法被调用");
    }
    
}
