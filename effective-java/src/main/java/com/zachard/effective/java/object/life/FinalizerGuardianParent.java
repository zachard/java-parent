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
 * 终结方法守卫者父类
 * <pre>
 *     (1) 显示声明一个终止方法
 *     (2) 提供一个匿名类调用终止方法终结外围实例
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class FinalizerGuardianParent {
    
    /**
     * 父类中的终结守卫者对象
     */
    @SuppressWarnings("unused")
    private final Object finalizerGuardian = new Object() {
      
        /**
         * 终结守卫者中的终结方法
         */
        @Override
        protected void finalize() throws Throwable {
            try {
                System.err.println("终结方法守卫者的终结方法被调用");
            } finally {
                // 在终结守卫者的终结方法中调用显示声明的终止方法来终结外围对象
                terminate();
            }
        }
    };
    
    /**
     * 覆写的终结方法
     */
    @Override
    protected void finalize() throws Throwable {
        System.err.println("父类覆写的终结方法被调用");
        terminate();
    }
    
    /**
     * 父类显示声明的终止方法
     */
    private void terminate() {
        // 释放资源
        System.err.println("父类显示声明的终止方法被调用");
    }

}
