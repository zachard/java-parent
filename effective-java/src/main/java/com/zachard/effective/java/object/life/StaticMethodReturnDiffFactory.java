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
 * <code>Effitive Java</code>第二章: 创建和销毁对象
 * 采用静态工程方法创建对象相比于构造器的优势 -- 静态工厂方法可以返回原类型的任何子类型的对象
 * 
 * 用于创建原类型对应子类型的工厂 -- 提供了两个原类型的内部类实现及根据参数值返回不同实现的静态工厂方法
 * 
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class StaticMethodReturnDiffFactory {
    
    /**
     * 私有化默认构造器, 客户端不能通过构造器创建对象实例
     */
    private StaticMethodReturnDiffFactory() {
        
    }
    
    /**
     * 静态工厂方法返回一个类型的子类型实现示例, 并且根据不同参数, 可以返回不同的子类型实现
     * 
     * @param complex   是否为复杂的实现
     * @return  {@code complex}为<code>true</code>返回一个类型的复杂实现, 否则返回一个类型的简单实现
     */
    public static StaticMethodReturnDiff diffInstanceImpl(boolean complex) {
        
        if (complex) {
            return new ComplexStaticMethodReturnDiff();
        }
        
        return new SimpleStaticMethodReturnDiff();
    }
    
    /**
     * {@link StaticMethodReturnDiff}的一个简单实现
     * 
     * <pre>
     *    作为工厂的非公有内部实现类, 对于客户端隐藏了{@link StaticMethodReturnDiff}的实现
     *    因为这个的测试类与当前类包名一致, 为了测试不可访问性, 作用域设置为<code>private</code>
     *    实际的作用域可以设置为默认(仅包内可见)
     * </pre>
     * 
     * @author richard
     *
     */
    private static class SimpleStaticMethodReturnDiff implements StaticMethodReturnDiff {

        @Override
        public String printSubTypeInfo() {
            return "我是StaticMethodReturnDiff类型的一个简单实现";
        }
        
    }
    
    /**
     * {@link StaticMethodReturnDiff}的一个简单实现
     * 
     * <pre>
     *    作为工厂的非公有内部实现类, 对于客户端隐藏了{@link StaticMethodReturnDiff}的实现
     *    因为这个的测试类与当前类包名一致, 为了测试不可访问性, 作用域设置为<code>private</code>
     *    实际的作用域可以设置为默认(仅包内可见)
     * </pre>
     * 
     * @author richard
     *
     */
    private static class ComplexStaticMethodReturnDiff implements StaticMethodReturnDiff {

        @Override
        public String printSubTypeInfo() {
            return "我是StaticMethodReturnDiff类型的一个复杂实现";
        }
        
    }

}
