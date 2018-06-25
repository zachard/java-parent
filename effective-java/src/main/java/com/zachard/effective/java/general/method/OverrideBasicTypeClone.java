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
 *    -- 当类中的每个域的值为基本类型或是指向一个不可变对象引用时, {@link super#clone()}
 *       返回的对象通常正是所需要的对象
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class OverrideBasicTypeClone implements Cloneable {
    
    /**
     * 不可变对象类型域
     */
    private String name;
    
    /**
     * 基本类型域
     */
    private int age;
    
    /**
     * 类构造器
     * 
     * @param name   属性
     * @param age    属性
     */
    public OverrideBasicTypeClone(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    /**
     * 通过直接返回{@link super#clone()}覆写{@link #clone()}方法
     * 
     * @return 克隆的实例
     */
    @Override
    public OverrideBasicTypeClone clone() {
        try {
            return (OverrideBasicTypeClone) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
    
    /**
     * 总是覆写{@link #toString()}方法
     * 
     * @return  对象的字符串表示
     */
    @Override
    public String toString() {
        return "姓名: " + name + "; 年龄: " + age;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }
    
}
