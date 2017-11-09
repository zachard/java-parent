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

package com.zachard.java.hello.service;

/**
 * 反射方法Service抽象类
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public abstract class AbstractMethodService {
	
	/**
	 * 测试{@link Class#getMethod(String, Class...)}获取类公有型方法,当本类型、实现接口类型及继承类型
	 * 都存在名称相同的方法时,获取当前类型中的方法
	 * 
	 * @param name    方法参数
	 * @return        方法返回值
	 */
	public abstract String existAll(String name);
	
	/**
	 * 测试{@link Class#getMethod(String, Class...)}获取公有类型方法,当本类型、实现接口类型不存在,但在
	 * 继承类型存在时, 获取继承类型中的方法
	 * 测试{@link Class#getDeclaredMethod(String, Class...)}获取方法时,当本类型、实现接口类型不存在,但在
	 * 继承类型存在时, 抛出{@link NoSuchMethodException}异常
	 * 
	 * @param name    方法参数
	 * @return        方法返回值
	 */
	public String existExtends(String name) {
		return name;
	}
	
	/**
	 * 测试{@link Class#getDeclaredMethod(String, Class...)}获取私有类型方法,当本类型、实现接口中不存在,但在
	 * 继承类型中存在时, 抛出{@link NoSuchMethodException}异常
	 * 
	 * @param name    方法参数
	 * @return        方法返回值
	 */
	public String existExtendsPrivate(String name) {
		return name;
	}

}
