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

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Java反射机制测试类
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class ClazzReflectTest {
	
	private static final Logger logger = LoggerFactory.getLogger(ClazzReflectTest.class);
	
	ClazzReflect clazzReflect = null;
	
	@Before
	public void setUp() {
		clazzReflect = new ClazzReflect();
	}

	/**
	 * 获取{@link Class}对象方法测试
	 * 
	 * @throws ClassNotFoundException   类不存在异常
	 */
	@Test
	public void getClazzObjectTest() throws ClassNotFoundException {
		// 1. object.getClass()方式获取测试
		logger.info("获取到的Class对象为: " + clazzReflect.getClazzObject(clazzReflect));
		
		// 2. T.class方式获取测试
		logger.info("获取到的Class对象为: " + clazzReflect.getClazzObject(int[].class));
		
		// 3. Class.forName(clazzName)方式获取测试
		String clazzName = "com.zachard.java.hello.reflect.HelloReflect";
		logger.info("获取到的Class对象为: " + clazzReflect.getClazzObject(clazzName));
	}
	
	/**
	 * 验证{@link #getClass()}方法获取的是运行时类
	 */
	@Test
	public void getRuntimeClazzTest() {
		Number number = new Long("10");
		Class<? extends Number> clazz = number.getClass();
		logger.info("getClass()获取的对象类型为: " + clazz.getName());
		Assert.assertEquals("java.lang.Long", clazz.getName());
	}
	
	/**
	 * 通过{@link Class}对象获取类名测试
	 */
	@Test
	public void getClazzNameTest() {
		logger.info("获取到的类名为: " + clazzReflect.getClazzName(ClazzReflect.class));
		logger.info("获取到的日期类名为: " + clazzReflect.getClazzName(Date.class));
	}

	/**
	 * 测试利用{@link Class}及==判断某个对象是否属于某个类型
	 */
	@Test
	public void judgeInstanceOfTest() {
		logger.info("ClazzReflect类对象是否与Object类对象相等? " + clazzReflect.judgeInstanceOf(clazzReflect, ClazzReflect.class));
		logger.info("ClazzReflect类对象是否与Date类对象相等? " + clazzReflect.judgeInstanceOf(clazzReflect, Date.class));
		logger.info("Date类对象是否与Object类对象相等? " + clazzReflect.judgeInstanceOf(new Date(), Object.class));
	}
	
	/**
	 * 测试利用{@link Class#newInstance()}方法创建实例对象
	 * 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@Test
	public void createInstanceTest() throws InstantiationException, IllegalAccessException {
		//类中没有声明无参构造器,则默认提供一个无参构造器
		ClazzReflect reflectInstance = (ClazzReflect) clazzReflect.createInstance(ClazzReflect.class);
		logger.info("新创建实例的对象类型名为: " + clazzReflect.getClazzName(reflectInstance.getClass()));
	}
	
	/**
	 * 测试利用{@link Class#getSuperclass()}方法获取父类类型对象
	 */
	@Test
	public void getSuperClazzTest() {
		logger.info("java.util.Date的父类类型为: " + clazzReflect.getSuperClazz(Date.class));
		logger.info("java.lang.Double的父类类型为: " + clazzReflect.getSuperClazz(Double.class));
	}
	
	/**
	 * {@link #getClass()}方法错误使用示例测试
	 */
	@Test(expected = Throwable.class)
	public void cannotGetSuperClazzObjectTest() {
		Class<?> clazz = clazzReflect.cannotGetSuperClazzObject();
		Assert.assertEquals("java.lang.Object", clazz.getName());
	}
}
