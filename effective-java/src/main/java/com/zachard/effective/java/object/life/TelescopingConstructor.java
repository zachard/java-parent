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
 * 遇到多个构造器参数时要考虑用构造器 -- 重叠构造器模式解决方法
 * 
 * <pre>
 *     代码示例中对用户进行了抽象
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class TelescopingConstructor {
    
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
     * 默认构造器
     */
    public TelescopingConstructor() {
        
    }
    
    /**
     * 带有两个必填参数的构造器
     * 
     * @param name    用户名称
     * @param mobile  用户手机号
     */
    public TelescopingConstructor(String name, String mobile) {
        this(name, mobile, null);
    }
    
    /**
     * 带有两个必填参数和一个可选参数的构造器
     * 
     * @param name    用户姓名
     * @param mobile  用户手机
     * @param age     用户年龄
     */
    public TelescopingConstructor(String name, String mobile, Integer age) {
        this(name, mobile, age, null);
    }
    
    /**
     * 带有两个必填参数和两个可选参数的构造器
     * 
     * @param name    用户姓名
     * @param mobile  用户手机
     * @param age     用户年龄
     * @param sex     用户性别
     */
    public TelescopingConstructor(String name, String mobile, Integer age, 
            Boolean sex) {
        this(name, mobile, age, sex, null);
    }
    
    /**
     * 带有所有必选参数和三个非必选参数的构造器
     * 
     * @param name    用户姓名
     * @param mobile  用户手机号
     * @param age     用户年龄
     * @param sex     用户性别
     * @param address 用户住址
     */
    public TelescopingConstructor(String name, String mobile, Integer age, 
            Boolean sex, String address) {
        this(name, mobile, age, sex, address, null);
    }
    
    /**
     * 包含所有必选和可选参数的构造器
     * 
     * @param name    用户名称
     * @param mobile  用户手机号
     * @param age     用户年龄
     * @param sex     用户性别
     * @param address 用户住址
     * @param salary  用户收入
     */
    public TelescopingConstructor(String name, String mobile, Integer age, 
            Boolean sex, String address, BigDecimal salary) {
        this.name = name;
        this.mobile = mobile;
        this.age = age;
        this.sex = sex;
        this.address = address;
        this.salary = salary;
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
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return the age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * @return the sex
     */
    public Boolean getSex() {
        return sex;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the salary
     */
    public BigDecimal getSalary() {
        return salary;
    }

    /**
     * @param salary the salary to set
     */
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

}
