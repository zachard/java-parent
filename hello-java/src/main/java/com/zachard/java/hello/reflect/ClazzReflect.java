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

package com.zachard.java.hello.reflect;

import java.lang.reflect.Constructor;

/**
 * Java反射机制示例类
 * <pre>
 *     反射机制的主要作用: 
 *     (1)  在运行中分析类的能力
 *     (2)  在运行中查看对象, 例如: 编写{@link #toString()}方法供所有类使用
 *     (3)  实现通用的数组操作代码
 *     (4)  利用Method对象，类似于C++中的指针
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class ClazzReflect {
	
	/**
	 * 无参数构造器,为{@link Class#newInstance()}方法创建对象时提供
	 */
	public ClazzReflect() {
		
	}
	
	/**
	 * 通过类对象获取{@link Object#getClass()}获取{@link Class}对象
	 * <pre>
	 *     使用场景: 非static方法
	 * </pre>
	 * 
	 * @param object    实例对象
	 * @return          {@link Class}对象
	 */
	public Class<?> getClazzObject(Object object) {
		return object.getClass();
	}
	
	/**
	 * 通过T.class方式获取{@link Class}对象
	 * <pre>
	 *     使用场景: static方法
	 * </pre>
	 * 
	 * @param clazz   T.class获取到的{@link Class}对象
	 * @return        {@link Class}对象
	 */
	public Class<?> getClazzObject(Class<?> clazz) {
		return clazz;
	}
	
	/**
	 * 通过{@link Class#forName(String)}的方法获取一个{@link Class}对象
	 * <pre>
	 *     使用场景: 
	 *     (1) 类名保存在字符串中,并且在运行时可变
	 *     (2) 提供的参数必须是类或是接口
	 * </pre>
	 * 
	 * @param clazzName    完整限定类名(包名 + 类名)
	 * @return    {@link Class}对象
	 * @throws ClassNotFoundException   类不存在异常
	 */
	public Class<?> getClazzObject(String clazzName) throws ClassNotFoundException {
		return Class.forName(clazzName);
	}
	
	/**
	 * {@link Class}对象的用途, 通过{@link Class#getName()}获取一个对象的类名
	 * <pre>
	 *     注: 返回的是类的完整限定名
	 * </pre>
	 * 
	 * @param clazz    {@link Class}对象
	 * @return         {@link Class}对象类名
	 */
	public String getClazzName(Class<?> clazz) {
		return clazz.getName();
	}
	
	/**
	 * {@link Class}对象用途, 通过==判断两个类型对象是否相等
	 * <pre>
	 *     注: JVM为每个类型管理一个{@link Class}对象,因为子类类型的{@link Class}对象
	 *        不会等于父类的类型对象
	 * </pre>
	 * 
	 * @param src    源类型
	 * @param des    目标类型
	 * @return       类型是否相同
	 */
	public boolean judgeInstanceOf(Object src, Class<?> des) {
		return src.getClass() == des;
	}
	
	/**
	 * {@link Class}对象用途, 通过{@link Class#newInstance()}创建一个类型的实例对象
	 * <pre>
	 *     注意事项:
	 *     (1) {@link Class#newInstance()}调用的是默认(无参)构造器初始化新的对象,如果没有这个构造器,则会抛出异常
	 *     (2) 如果需要在构造器中传入参数,则需要使用{@link Constructor#newInstance(Object...)}方法
	 * </pre>
	 * 
	 * @param clazz    {@link Class}对象
	 * @return         与clazz类型相同的实例对象
	 * @throws InstantiationException    初始化异常
	 * @throws IllegalAccessException    参数异常
	 */
	public Object createInstance(Class<?> clazz) throws InstantiationException, IllegalAccessException {
		return clazz.newInstance();
	}

}
