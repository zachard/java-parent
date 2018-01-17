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
 * 采用静态工程方法创建对象相比于构造器的优势 -- 静态方法有名称
 * 
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
@SuppressWarnings("all")
public class StaticMethodHasName {
	
	/**
	 * int类型属性
	 */
	private int count;
	
	/**
	 * int类型属性
	 */
	private int num;
	
	/**
	 * String类型属性
	 */
	private String name;
	
	/**
	 * 默认构造器
	 */
	public StaticMethodHasName() {
		
	}
	
	/**
	 * 一个类可能存在相同类型的属性, 由于一个类只能有一个带 指定签名的构造器, 
	 * 当需要创建相同类型属性但属性不一致时, 构造器可以通过调整不同类型的参数
	 * 顺序来实现, 但是这样无法表达创建出对象的含义, 并且构造器极易混淆
	 * 
	 * @param name    String类型参数
	 * @param count   int类型参数
	 */
	public StaticMethodHasName(String name, int count) {
		this.name = name;
		this.count = count;
	}
	
	/**
	 * 一个类可能存在相同类型的属性, 由于一个类只能有一个带 指定签名的构造器, 
	 * 当需要创建相同类型属性但属性不一致时, 构造器可以通过调整不同类型的参数
	 * 顺序来实现, 但是这样无法表达创建出对象的含义, 并且构造器极易混淆
	 * 
	 * @param num    int类型参数
	 * @param name   String类型参数
	 */
	public StaticMethodHasName(int num, String name) {
		this.num = num;
		this.name = name;
	}
	
	/**
	 * 从静态工厂方法的名称可以看出, 创建一个初始化了{@link #name}属性和{@link StaticMethodHasName#count}
	 * 属性的对象, 并且与{@link #instanceWithNameAndNum(String, int)}方法参数类型顺序一样
	 * 
	 * @param name    int类型属性
	 * @param count   String类型属性
	 * @return        类对象
	 */
	public static StaticMethodHasName instanceWithNameAndCount(String name, int count) {
		StaticMethodHasName instance = new StaticMethodHasName();
		// 没有为属性提供setter/getter方法主要是因为类可能是Service层类
		instance.name = name;
		instance.count = count;
		
		return instance;
	}
	
	/**
	 * 从静态工厂方法的名称可以看出, 创建一个初始化了{@link #name}属性和{@link #num}
	 * 属性的对象, 并且与{@link #instanceWithNameAndCount(String, int)}方法参数类型顺序一样
	 * 
	 * @param name   String类型属性
	 * @param num    int类型属性
	 * @return
	 */
	public static StaticMethodHasName instanceWithNameAndNum(String name, int num) {
		StaticMethodHasName instance = new StaticMethodHasName();
		// 没有为属性提供setter/getter方法主要是因为类可能是Service层类
		instance.name = name;
		instance.num = num;
		
		return instance;
	}

}
