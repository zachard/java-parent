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
import java.lang.reflect.Field;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zachard.java.hello.model.User;
import com.zachard.java.hello.service.AbstractService;
import com.zachard.java.hello.service.impl.HomeServiceImpl;

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
	
	/**
	 * 测试通过{@link Class#getModifiers()}获取类型的修饰符
	 */
	@Test
	public void getClazzModifiersTest() {
		logger.info("java.util.Double类型的修饰符为: " + clazzReflect.getClazzModifiers(Double.class));
	}
	
	/**
	 * 测试通过{@link Class#getConstructor(Class...)}获取类型构造器
	 * 
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void getClazzConstructorTest() throws NoSuchMethodException, SecurityException {
		// 只有一个参数,获取无参数构造器
		Constructor<?> stringConstructor = clazzReflect.getClazzConstructor(String.class);
		logger.info("String类型的无参数构造器为: " + stringConstructor.toString());
		
		// 抽象类获取构造器测试
		Constructor<?> abstractConstructor = clazzReflect.getClazzConstructor(AbstractService.class);
		logger.info("AbstractService类型无参数构造器: " + abstractConstructor);
		
		// 参数长度大于1时
		Constructor<?> doubleConstructor = clazzReflect.getClazzConstructor(Double.class, String.class);
		logger.info("Double类型接收一个String类型的构造器为: " + doubleConstructor.toString());
		
		// 抛出 NoSuchMethodException 异常, 但User类中有 private User(String name) 构造器
		//Constructor<?> userConstructor = clazzReflect.getClazzConstructor(User.class, String.class);
		//logger.info("User类型是否存在参数为String类型的公有构造器: " + (userConstructor == null ? false : true));
		
		// 参数小于1,抛出参数异常
		Constructor<?> noParamConstructor = clazzReflect.getClazzConstructor();
		logger.info("获取构造器器异常: " + (noParamConstructor == null));
	}
	
	/**
	 * 测试通过{@link Class#getDeclaredConstructor(Class...)}获取构造器
	 * 
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void getClazzDeclaredConstructorTest() 
			throws NoSuchMethodException, SecurityException {
		// 只有一个参数获取无参数构造器
		Constructor<?> stringConstructor = clazzReflect.getClazzDeclaredConstructor(String.class);
		logger.info("String类型的无参数构造器为: " + stringConstructor);
		
		// 参数长度大于1
		Constructor<?> doubleConstructor = clazzReflect.getClazzDeclaredConstructor(Double.class, String.class);
		logger.info("Double类型接收一个String类型的构造器为: " + doubleConstructor);
		
		// 获取类型的私有构造器
	    Constructor<?> userConstructor = clazzReflect.getClazzDeclaredConstructor(User.class, String.class);
		logger.info("User类型接收一个String类型的构造器为: " + userConstructor);
		
		// 参数小于1,抛出参数异常
		Constructor<?> noParamConstructor = clazzReflect.getClazzDeclaredConstructor();
		logger.info("获取构造器器异常: " + (noParamConstructor == null));
	}
	
	/**
	 * 测试通过{@link Class#getConstructors()}获取指定类型的公有构造器数组
	 */
	@Test
	public void getClazzConstructorsTest() {
		// String类型的所有公有构造器
		Constructor<?>[] stringConstructors = clazzReflect.getClazzConstructors(String.class);
		logger.info("String类型的公有构造器个数为: " + stringConstructors.length);
		
		for (Constructor<?> constructor : stringConstructors) {
			logger.info(constructor.toString());
		}
		
		// Comparable类型的所有公有构造器(接口)
		Constructor<?>[] doubleConstructors = clazzReflect.getClazzConstructors(Comparable.class);
		logger.info("Comparable类型的公有构造器为: " + doubleConstructors.length);
		
		for (Constructor<?> constructor : doubleConstructors) {
			logger.info(constructor.toString());
		}
		
		// User类型公有构造器
		Constructor<?>[] constructors = clazzReflect.getClazzConstructors(User.class);
		logger.info("User类型的公有构造器个数为: " + constructors.length);
		
		for (Constructor<?> constructor : constructors) {
			logger.info(constructor.toString());
		}
	}
	
	/**
	 * 测试通过{@link Class#getDeclaredConstructors()}获取指定类型的所有构造器
	 */
	@Test
	public void getClazzDeclaredConstructorsTest() {
		// String类型的所有构造器
		Constructor<?>[] stringConstructors = clazzReflect.getClazzDeclaredConstructors(String.class);
		logger.info("String类型的构造器个数为: " + stringConstructors.length);
		
		for (Constructor<?> constructor : stringConstructors) {
			logger.info(constructor.toString());
		}
		
		// Comparable类型的所有构造器(接口)
		Constructor<?>[] doubleConstructors = clazzReflect.getClazzDeclaredConstructors(Comparable.class);
		logger.info("Comparable类型的构造器为: " + doubleConstructors.length);
		
		for (Constructor<?> constructor : doubleConstructors) {
			logger.info(constructor.toString());
		}
		
		// User类型构造器
		Constructor<?>[] constructors = clazzReflect.getClazzDeclaredConstructors(User.class);
		logger.info("User类型的构造器个数为: " + constructors.length);
		
		for (Constructor<?> constructor : constructors) {
			logger.info(constructor.toString());
		}
	}
	
	/**
	 * 测试通过{@link Class#getField(String)}方法获取指定类型中的特定名称的公有成员变量
	 * 
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
	@Test(expected = NoSuchFieldException.class)
	public void getClazzFieldTest() throws NoSuchFieldException, SecurityException {
		// 当只有成员变量只在本类中存在时,则获取本类中的成员变量
		String fieldName = "existClazz";
		Field clazzField = clazzReflect.getClazzField(HomeServiceImpl.class, fieldName);
		logger.info("当成员变量只在本类中存在时,获取本类中的成员变量: " + clazzField);
		
		// 当成员变量在本类和实现接口中不存在,但在继承类中存在时,获取继承类的成员变量
		fieldName = "existExtends";
		Field extendsField = clazzReflect.getClazzField(HomeServiceImpl.class, fieldName);
		logger.info("当成员变量在本类和实现接口中不存在,但在继承类中存在时,获取抽象类的成员变量: " + extendsField);
		
		// 当成员变量在本类和继承类中不存在,但在实现接口中存在时,获取实现接口中的成员变量
		fieldName = "existImpl";
		Field implField = clazzReflect.getClazzField(HomeServiceImpl.class, fieldName);
		logger.info("当成员变量在本类和继承类中不存在,但在实现接口中存在时,获取实现接口中的成员变量: " + implField);
		
		// 当成员变量在本类中不存在,但在继承类及实现接口中存在时,获取实现接口中的成员变量
		fieldName = "existExtendsAndImpl";
		Field extendsAndImplField = clazzReflect.getClazzField(HomeServiceImpl.class, fieldName);
		logger.info("当成员变量在本类中不存在,但在继承类及实现接口中存在时,获取实现接口中的成员变量 : " + extendsAndImplField);
		
		// 当成员变量在本类,继承类及实现接口中都存在时,则获取本类中的成员变量
		fieldName = "existAll";
		Field allField = clazzReflect.getClazzField(HomeServiceImpl.class, fieldName);
		logger.info("当成员变量在本类,继承类及实现接口中都存在时,则获取本类中的成员变量: " + allField);
		
		// 获取不存在的成员变量(私有也属于不存在)时,抛出NoSuchFieldException
		fieldName = "privateField";
		Field notExistField = clazzReflect.getClazzField(HomeServiceImpl.class, fieldName);
		logger.info("成员变量是否存在: " + (notExistField != null));
	}
	
	/**
	 * 测试通过{@link Class#getDeclaredField(String)}方法获取指定类型中特定名称的成员变量
	 * 
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
	@Test(expected = NoSuchFieldException.class)
	public void getClazzDeclaredFieldTest() 
			throws NoSuchFieldException, SecurityException {
		// 当公有成员变量在本类中存在时,则获取本类中的公有成员变量
		String fieldName = "existClazz";
		Field clazzField = clazzReflect.getClazzDeclaredField(HomeServiceImpl.class, fieldName);
		logger.info("当成员变量只在本类中存在时,获取本类中的成员变量: " + clazzField);
		
		// 当获取的私有成员变量在本类中存在时,则获取本类中的私有成员变量
		fieldName = "privateField";
		Field privateClazzField = clazzReflect.getClazzDeclaredField(HomeServiceImpl.class, fieldName);
		logger.info("当获取的私有成员变量在本类中存在时,则获取本类中的私有成员变量: " + privateClazzField);
		
		// 当成员变量在本类中不存在,但在继承类及实现接口中存在时,抛出NoSuchFieldException
		fieldName = "existExtendsAndImpl";
		Field extendsAndImplField = clazzReflect.getClazzDeclaredField(HomeServiceImpl.class, fieldName);
		logger.info("当成员变量在本类中不存在,但在继承类及实现接口中存在时,成员变量是否存在: " + (extendsAndImplField != null));
		
		// 获取不存在的成员变量(私有也属于不存在)时,抛出NoSuchFieldException
		fieldName = "notExist";
		Field notExistField = clazzReflect.getClazzField(HomeServiceImpl.class, fieldName);
		logger.info("成员变量是否存在: " + (notExistField != null));
	}
}
