/*
 *  Copyright 2015-2017 zachard, Inc.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.zachard.java.hello.model;

import java.io.Serializable;

/**
 * 用户实体类
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class User implements Serializable {

	/**
	 * 序列化ID，显示声明时有如下好处:
	 * <pre>
	 *   1. 提高程序运行效率
	 *   2. 避免不同操作系统间计算方式不一致而导致生成值不一致
	 *   3. 避免类文件修改后导致同一个类出现不同ID值情况
	 * </pre>
	 */
	private static final long serialVersionUID = -1985584994983123624L;
	
	/**
	 * 用户姓名
	 */
	private String name;
	
	/**
	 * 用户年龄
	 */
	private Integer age;
	
	/**
	 * 私有无参数构造器
	 */
	@SuppressWarnings("unused")
	private User() {
		
	}
	
	/**
	 * 私有带参数构造器
	 * 
	 * @param name    用户姓名
	 */
	@SuppressWarnings("unused")
	private User(String name) {
		this.name = name;
	}
	
	/**
	 * 公有参数构造器
	 * 
	 * @param name    用户姓名
	 * @param age     用户年龄
	 */
	public User(String name, Integer age) {
		this.name = name;
		this.age = age;
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
	public Integer getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

}
