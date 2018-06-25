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

import org.junit.Test;

/**
 * <code>Effitive Java</code>第二章: 创建和销毁对象
 * 消除过期的对象引用 -- 过期的对象引用容易引发内存泄漏测试
 * 
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class ObsoleteReferenceOutOfMemoryTest {
    
    /**
     * 测试元素出栈后的对象仍然被引用
     */
    @Test
    public void testExistObsoleteRefer() {
        ObsoleteReferenceOutOfMemory stack = new ObsoleteReferenceOutOfMemory();
        
        // 先将第一个栈A的元素填充到极限
        for (int i = 0; i < 17407; i++) {
            stack.push(new Object());
            System.err.println(stack.size() + "  " + stack.length());
        }
        
        // 将栈A中最后一次扩容的元素出栈
        for (int i = 17406; i > 8702; i--) {
            stack.pop();
        }
        
        // 线程休眠, 如何栈A出栈的元素会被垃圾回收, 在这将被回收
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // 将一部分元素出栈之后, 尝试获取第一次入栈时的最后一个元素
        System.err.println(stack.get(17406));
        
        ObsoleteReferenceOutOfMemory stack2 = new ObsoleteReferenceOutOfMemory();
        
        // 往第二个栈B中填充元素, 填充个数是测试上限个数+1
        for (int i = 0; i < 2177; i++) {
            stack2.push(new Object());
            System.err.println(stack2.size() + "  " + stack2.length());
        }
    }
    
    /**
     * 测试往一个栈中不断加入元素的极限, 一个栈可容纳的最大元素个数
     */
    @Test
    public void testOneStackMaxLength() {
        ObsoleteReferenceOutOfMemory stack = new ObsoleteReferenceOutOfMemory();
        
        while (true) {
            // 不断往栈中加入元素, 直至内存溢出
            stack.push(new Object());
            System.err.println(stack.size() + " " + stack.length());
        }
    }
    
    /**
     * 测试第一个栈元素填充完成之后, 第二个栈可填充的最大元素个数
     */
    @Test
    public void testTwoStackMaxLength() {
        ObsoleteReferenceOutOfMemory stack = new ObsoleteReferenceOutOfMemory();
        
        // 先将第一个栈元素加到极限
        for (int i = 0; i < 17407; i++) {
            stack.push(new Object());
        }
        
        ObsoleteReferenceOutOfMemory anotherStack = new ObsoleteReferenceOutOfMemory();
        
        while (true) {
            // 往第二个栈中不断加入元素, 直至内存溢出
            anotherStack.push(new Object());
            System.err.println(anotherStack.size() + " " + anotherStack.length());
        }
    }

}
