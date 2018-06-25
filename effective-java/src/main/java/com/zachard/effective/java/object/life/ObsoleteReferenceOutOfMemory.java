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

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * <code>Effitive Java</code>第二章: 创建和销毁对象
 * 消除过期的对象引用 -- 过期的对象引用容易引发内存泄漏
 * 
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class ObsoleteReferenceOutOfMemory {
    
    /**
     * 存储栈元素的数组
     */
    private Object[] elements;
    
    /**
     * 指向栈顶元素的游标
     */
    private int size = 0;
    
    /**
     * 栈的默认初始化长度大小
     */
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    
    /**
     * 栈构造器, 初始化一个栈
     */
    public ObsoleteReferenceOutOfMemory() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }
    
    /**
     * 执行元素入栈操作, 注: size真正表示了栈中元素的个数
     * 
     * <pre>
     *     (1) 确定是否需要扩容
     *     (2) 元素放入数组
     *     (3) 栈顶指针移动
     * </pre>
     * 
     * @param e    需要入栈的元素
     */
    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }
    
    /**
     * 栈执行出栈操作
     * 
     * <pre>
     *     (1) 将栈长度减1, 移动到栈顶位置
     *     (2) 返回栈顶元素
     * </pre>
     * 
     * @return
     */
    public Object pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        Object result = elements[--size];
        elements[size] = null;
        
        return result;
    }
    
    /**
     * 返回栈中元素的个数, 执行push操作, 个数加1, 执行pop操作, 个数减1
     * 
     * @return   栈中元素的个数
     */
    public int size() {
        return size;
    }
    
    /**
     * 返回栈的数组长度, 并非栈中元素个数
     * 
     * @return    栈的数组长度
     */
    public int length() {
        return elements.length;
    }
    
    /**
     * 获取栈中的元素
     * 
     * @param i    元素下标 -- 第一个入栈的元素下标为0
     * @return     栈中的元素
     */
    public Object get(int i) {
        if (i > (elements.length - 1)) {
            throw new IllegalArgumentException();
        }
        
        return elements[i];
    }
    
    /**
     * 对栈进行扩容, 扩容后长度是原来的两倍加一
     */
    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }

}
