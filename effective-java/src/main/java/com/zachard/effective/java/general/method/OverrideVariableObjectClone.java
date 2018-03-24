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

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * <code>Effitive Java</code>第三章: 对于所有对象都通用的方法
 * 谨慎地覆写{@link #clone()} 
 *    -- 当类中包含可变对象的域时, 应该递归调用clone方法来覆写clone方法
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class OverrideVariableObjectClone implements Cloneable {
    
    /**
     * 可变对象域
     */
    private Object[] elements;
    
    /**
     * 基本类型域
     */
    private int size = 0;
    
    private static final int DEFAULT_INITIAL_CAPACITY = 5;

    /**
     * 类构造方法
     */
    public OverrideVariableObjectClone() {
        this.elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }
    
    /**
     * 入栈
     * @param e  待入栈的元素
     */
    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }
    
    /**
     * 出栈
     * 
     * @return  出栈的元素
     */
    public Object pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        
        Object result = elements[--size];
        // 消除过期引用
        elements[size] = null;
        
        return result;
    }
    
    /**
     * 扩容
     */
    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }
    
    /**
     * 总是覆写{@link #toString()}方法
     * 
     * @return  类型对象的字符串表示
     */
    @Override
    public String toString() {
        return "栈长度为: " + size + "; 元素为: " + Arrays.toString(elements);
    }
    
    /**
     * 覆写{@link #clone()}方法
     * 
     * @return  克隆的实例对象
     */
    @Override
    public OverrideVariableObjectClone clone() {
        try {
            return (OverrideVariableObjectClone) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
