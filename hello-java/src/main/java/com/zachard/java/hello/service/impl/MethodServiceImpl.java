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

package com.zachard.java.hello.service.impl;

import com.zachard.java.hello.service.AbstractMethodService;
import com.zachard.java.hello.service.MethodService;

/**
 * 反射方法Service实现类
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class MethodServiceImpl extends AbstractMethodService implements MethodService {

	/**
	 * 测试{@link Class#getMethod(String, Class...)}获取类型方法,当本类型、实现接口类型及继承类型
	 * 都存在名称相同的方法时,获取当前类型中的方法
	 * 
	 * <pre>
	 *     注: 当类型的继承类型中存在与实现接口签名相同的方法时,此方法的返回值必须与实现接口类型中方法一致
	 * </pre>
	 * 
	 * @param name    方法参数
	 * @return        方法返回值
	 */
	@Override
	public String existAll(String name) {
		return name;
	}
	
	/**
	 * 测试{@link Class#getMethod(String, Class...)}获取类型方法,当继承类型及实现接口类型不存在名称方法,
	 * 但当前类型存在,则返回本类型中的方法
	 * 
	 * @param name    方法参数
	 * @return        方法返回值
	 */
	public String existClazz(String name) {
		return name;
	}
	
	/**
	 * 测试{@link Class#getMethod(String, Class...)}获取公有方法,当为私有方法时抛出{@link NoSuchMethodException}异常
	 * 
	 * @param name    方法参数
	 * @return        方法返回值
	 */
	@SuppressWarnings("unused")
	private String privateMethod(String name) {
		return name;
	}
}
