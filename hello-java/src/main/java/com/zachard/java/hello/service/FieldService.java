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
 * 成员变量Service接口
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public interface FieldService {
	
	/**
	 * 测试{@link Class#getField(String)}获取公有域,当本类,继承类,实现接口中存在名称
	 * 相同的域时,获取的本类中的域
	 */
	String existAll = "existAll";
	
	/**
	 * 测试{@link Class#getField(String)}获取公有域,当本类不存在,但继承类,实现接口中同时
	 * 存在名称相同域时,获取实现接口中的域
	 */
	String existExtendsAndImpl = "existExtendsAndImpl";
	
	/**
	 * 测试{@link Class#getField(String)}获取公有域,当本类及继承类不存在时,但实现接口中
	 * 存在名称相同域时,获取实现接口中的域
	 */
	String existImpl = "existImpl";

}
