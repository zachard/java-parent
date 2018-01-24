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

import java.math.BigDecimal;

/**
 * <code>Effitive Java</code>第二章: 创建和销毁对象
 * 遇到多个构造器参数时要考虑用构造器 -- Builder模式解决办法
 * 
 * <pre>
 *     代码示例中对用户进行了抽象
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class BuilderPattern {
    
    /**
     * 用户姓名, 必填
     */
    private String name;
    
    /**
     * 用户手机号, 必填
     */
    private String mobile;
    
    /**
     * 用户年龄, 非必填
     */
    private Integer age;
    
    /**
     * 用户性别, 非必填
     */
    private Boolean sex;
    
    /**
     * 用户住址, 非必填
     */
    private String address;
    
    /**
     * 用户收入, 非必填
     */
    private BigDecimal salary;
    
    /**
     * 对象构造器
     * 
     * @author richard
     *
     */
    public static class Builder {
        // 被构造对象必填的属性(是否应该设置为final应当示情况而定)
        private final String name;
        private final String mobile;
        
        // 被构造对象非必填参数
        private Integer age;
        private Boolean sex;
        private String address;
        private BigDecimal salary;
        
        /**
         * 构造器 -- 通常在构造器中初始化被构建对象的必填值
         * 
         * @param name    姓名
         * @param mobile  手机
         */
        public Builder(String name, String mobile) {
            this.name = name;
            this.mobile = mobile;
        }
        
        // 非必须属性的设置方法, 因为必填属性为final, 所以没有提供设置其属性的方式
        public Builder age(Integer age) {
            this.age = age;
            return this;
        }
        
        public Builder sex(Boolean sex) {
            this.sex = sex;
            return this;
        }
        
        public Builder address(String address) {
            this.address = address;
            return this;
        }
        
        public Builder salary(BigDecimal salary) {
            this.salary = salary;
            return this;
        }
        
        /**
         * 通过{@link Builder}对象创建{@link BuilderPattern}对象的方式
         * 
         * @return
         */
        public BuilderPattern builder() {
            return new BuilderPattern(this);
        }
    }
    
    /**
     * 私有化构造器, 并且设置只能通过构造对象来创建对象
     * 
     * @param builder   Builder模式中Builder对象
     */
    private BuilderPattern(Builder builder) {
        this.name = builder.name;
        this.mobile = builder.mobile;
        this.age = builder.age;
        this.sex = builder.sex;
        this.address = builder.address;
        this.salary = builder.salary;
    }

    // 用于创建完对象之后获取对象属性或修改对象属性的方式
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

}
