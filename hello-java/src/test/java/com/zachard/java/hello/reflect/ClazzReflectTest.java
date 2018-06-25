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
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zachard.java.hello.model.User;
import com.zachard.java.hello.service.AbstractFieldService;
import com.zachard.java.hello.service.impl.FieldServiceImpl;
import com.zachard.java.hello.service.impl.MethodServiceImpl;

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
		Constructor<?> abstractConstructor = clazzReflect.getClazzConstructor(AbstractFieldService.class);
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
		Field clazzField = clazzReflect.getClazzField(FieldServiceImpl.class, fieldName);
		logger.info("当成员变量只在本类中存在时,获取本类中的成员变量: " + clazzField);
		
		// 当成员变量在本类和实现接口中不存在,但在继承类中存在时,获取继承类的成员变量
		fieldName = "existExtends";
		Field extendsField = clazzReflect.getClazzField(FieldServiceImpl.class, fieldName);
		logger.info("当成员变量在本类和实现接口中不存在,但在继承类中存在时,获取抽象类的成员变量: " + extendsField);
		
		// 当成员变量在本类和继承类中不存在,但在实现接口中存在时,获取实现接口中的成员变量
		fieldName = "existImpl";
		Field implField = clazzReflect.getClazzField(FieldServiceImpl.class, fieldName);
		logger.info("当成员变量在本类和继承类中不存在,但在实现接口中存在时,获取实现接口中的成员变量: " + implField);
		
		// 当成员变量在本类中不存在,但在继承类及实现接口中存在时,获取实现接口中的成员变量
		fieldName = "existExtendsAndImpl";
		Field extendsAndImplField = clazzReflect.getClazzField(FieldServiceImpl.class, fieldName);
		logger.info("当成员变量在本类中不存在,但在继承类及实现接口中存在时,获取实现接口中的成员变量 : " + extendsAndImplField);
		
		// 当成员变量在本类,继承类及实现接口中都存在时,则获取本类中的成员变量
		fieldName = "existAll";
		Field allField = clazzReflect.getClazzField(FieldServiceImpl.class, fieldName);
		logger.info("当成员变量在本类,继承类及实现接口中都存在时,则获取本类中的成员变量: " + allField);
		
		// 获取不存在的成员变量(私有也属于不存在)时,抛出NoSuchFieldException
		fieldName = "privateField";
		Field notExistField = clazzReflect.getClazzField(FieldServiceImpl.class, fieldName);
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
		Field clazzField = clazzReflect.getClazzDeclaredField(FieldServiceImpl.class, fieldName);
		logger.info("当成员变量只在本类中存在时,获取本类中的成员变量: " + clazzField);
		
		// 当获取的私有成员变量在本类中存在时,则获取本类中的私有成员变量
		fieldName = "privateField";
		Field privateClazzField = clazzReflect.getClazzDeclaredField(FieldServiceImpl.class, fieldName);
		logger.info("当获取的私有成员变量在本类中存在时,则获取本类中的私有成员变量: " + privateClazzField);
		
		// 当成员变量在本类中不存在,但在继承类及实现接口中存在时,抛出NoSuchFieldException
		fieldName = "existExtendsAndImpl";
		Field extendsAndImplField = clazzReflect.getClazzDeclaredField(FieldServiceImpl.class, fieldName);
		logger.info("当成员变量在本类中不存在,但在继承类及实现接口中存在时,成员变量是否存在: " + (extendsAndImplField != null));
		
		// 获取不存在的成员变量(私有也属于不存在)时,抛出NoSuchFieldException
		fieldName = "notExist";
		Field notExistField = clazzReflect.getClazzField(FieldServiceImpl.class, fieldName);
		logger.info("成员变量是否存在: " + (notExistField != null));
	}
	
	/**
	 * 测试通过{@link Class#getFields()}方法获取指定类型的所有公有成员变量
	 */
	@Test
	public void getClazzFieldsTest() {
		// 获取HomeServiceImpl类型的公有成员变量
		Field[] homeServiceImplFields = clazzReflect.getClazzFields(FieldServiceImpl.class);
		logger.info("HomeServiceImpl类型的公有成员变量个数为: " + homeServiceImplFields.length);
		
		for (Field field : homeServiceImplFields) {
			logger.info(field.toString());
		}
		
		// 获取double基本类型中的公有成员变量
		Field[] doubleFields = clazzReflect.getClazzFields(double.class);
		logger.info("double类型的公有成员变量个数为: " + doubleFields.length);
		
		for (Field field : doubleFields) {
			logger.info(field.toString());
		}
		
		// 获取Double数组类型的公有成员变量
		Field[] doubleArrayFields = clazzReflect.getClazzFields(Double[].class);
		logger.info("Double数组类型的公有成员变量个数为: " + doubleArrayFields.length);
		
		for (Field field : doubleArrayFields) {
			logger.info(field.toString());
		}
	}
	
	/**
	 * 测试通过{@link Class#getDeclaredFields()}方法获取指定类型声明的成员变量
	 */
	@Test
	public void getClazzDeclaredFieldsTest() {
		// 获取HomeServiceImpl类型中声明的成员变量
		Field[] homeServiceImplFields = clazzReflect.getClazzDeclaredFields(FieldServiceImpl.class);
		logger.info("HomeServiceImpl类型声明的成员变量个数为: " + homeServiceImplFields.length);
		
		for (Field field : homeServiceImplFields) {
			logger.info(field.toString());
		}
		
		// 获取double基本类型中声明的成员变量
		Field[] doubleFields = clazzReflect.getClazzDeclaredFields(double.class);
		logger.info("double基本类型声明的成员变量个数为: " + doubleFields.length);
		
		for (Field field : doubleFields) {
			logger.info(field.toString());
		}
		
		// 获取Double数组类型中声明的成员变量
		Field[] doubleArrayFields = clazzReflect.getClazzDeclaredFields(Double[].class);
		logger.info("Double数组类型声明的成员变量个数为: " + doubleArrayFields.length);
		
		for (Field field : doubleArrayFields) {
			logger.info(field.toString());
		}
	}
	
	/**
	 * 测试通过{@link Class#getMethod(String, Class...)}方法获取类型中指定名称的公有方法
	 * 
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	@Test(expected = NoSuchMethodException.class)
	public void getClazzMethodTest() throws NoSuchMethodException, SecurityException {
		// 当本类型、实现接口类型及继承接口类型都存在名称相同的方法时,获取本类型中的方法
		Method allMethod = clazzReflect.getClazzMethod(MethodServiceImpl.class, "existAll", String.class);
		logger.info("当本类型、实现接口类型及继承接口类型都存在名称相同的方法时,获取本类型中的方法: " + allMethod);
		
		// 当继承类型及实现接口类型不存在名称方法,但当前类型存在,则返回当前类型中的方法
		Method clazzMethod = clazzReflect.getClazzMethod(MethodServiceImpl.class, "existClazz", String.class);
		logger.info("当继承类型及实现接口类型不存在名称方法,但当前类型存在,则返回当前类型中的方法: " + clazzMethod);
		
		// 当本类型、实现接口类型不存在,但在继承类型存在时,获取继承类型中的方法
		Method extendsMethod = clazzReflect.getClazzMethod(MethodServiceImpl.class, "existExtends", String.class);
		logger.info("当本类型、实现接口类型不存在,但在继承类型存在时, 获取继承类型中的方法: " + extendsMethod);
		
		// 当获取的方法为私有方法时,抛出NoSuchMethodException异常
//		Method privateMethod = clazzReflect.getClazzMethod(MethodServiceImpl.class, "privateMethod", String.class);
//		logger.info("当前类型中名为privateMethod的方法为: " + privateMethod);
		
		// 当方法名称为"<init>"或是"<clinit>"时, 抛出NoSuchMethodException异常
		Method initMethod = clazzReflect.getClazzMethod(Double.class, "<init>", String.class);
		logger.info("Double类型中<init>名称的方法为: " + initMethod);
	}
	
	/**
	 * 测试通过{@link Class#getDeclaredMethod(String, Class...)}方法获取指定类型中的方法
	 * 
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	@Test(expected = NoSuchMethodException.class)
	public void getClazzDeclaredMethodTest() throws NoSuchMethodException, SecurityException {
		// 当本类型、实现接口类型及继承接口类型都存在名称相同的方法时,获取本类型中的方法
		Method allMethod = clazzReflect.getClazzDeclaredMethod(MethodServiceImpl.class, "existAll", String.class);
		logger.info("当本类型、实现接口类型及继承接口类型都存在名称相同的方法时,获取本类型中的方法: " + allMethod);
		
		// 当继承类型及实现接口类型不存在名称方法,但当前类型存在,则返回当前类型中的方法
		Method clazzMethod = clazzReflect.getClazzDeclaredMethod(MethodServiceImpl.class, "existClazz", String.class);
		logger.info("当继承类型及实现接口类型不存在名称方法,但当前类型存在,则返回当前类型中的方法: " + clazzMethod);
		
		// 当获取的方法为私有方法且只在本类型中存在时,获取本类型中的私有方法
		Method privateMethod = clazzReflect.getClazzDeclaredMethod(MethodServiceImpl.class, "privateMethod", String.class);
		logger.info("当获取的方法为私有方法且只在本类型中存在时,获取本类型中的私有方法: " + privateMethod);
		
		// 当本类型、实现接口类型不存在,但在继承类型存在时,抛出NoSuchMethodException
//		Method extendsMethod = clazzReflect.getClazzDeclaredMethod(MethodServiceImpl.class, "existExtends", String.class);
//		logger.info("当本类型、实现接口类型不存在,但在继承类型存在时, 方法为: " + extendsMethod);
		
		// 当获取方法为私有方法且只在继承类型中存在时, 抛出NoSuchMethodException
		Method extendsPrivateMethod = clazzReflect.getClazzDeclaredMethod(MethodServiceImpl.class, "existExtendsPrivate", String.class);
		logger.info("当获取方法为私有方法且只在继承类型中存在时, 方法为: " + extendsPrivateMethod);
		
		// 当方法名称为"<init>"或是"<clinit>"时, 抛出NoSuchMethodException异常
		Method initMethod = clazzReflect.getClazzDeclaredMethod(Double.class, "<init>", String.class);
		logger.info("Double类型中<init>名称的方法为: " + initMethod);
	}

	/**
	 * 测试通过{@link Class#getMethods()}方法获取类型中的所有公有成员方法
	 */
	@Test
    public void getCLazzMethodsTest() {
		// 获取MethodServiceImpl类型的公有成员方法
		Method[] methodServiceImplMethods = clazzReflect.getCLazzMethods(MethodServiceImpl.class);
		logger.info("MethodServiceImpl类型的公有成员方法个数为: " + methodServiceImplMethods.length);
		Arrays.asList(methodServiceImplMethods).forEach(methodServiceImplMethod 
				-> logger.info(methodServiceImplMethod.toString()));
		
		// 获取Comparable类型的公有成员方法
		Method[] comparableMethods = clazzReflect.getCLazzMethods(Comparable.class);
		logger.info("Comparable类型的公有成员方法个数为: " + comparableMethods.length);
		Arrays.asList(comparableMethods).forEach(comparableMethod
				-> logger.info(comparableMethod.toString()));
		
		// 获取double基本类型的公有成员方法
		Method[] doubleMethods = clazzReflect.getCLazzMethods(double.class);
		logger.info("double基本类型的公有成员方法个数为: " + doubleMethods.length);
		Arrays.asList(doubleMethods).forEach(doubleMethod 
				-> logger.info(doubleMethod.toString()));
		
		// 获取Double数组基本类型的公有成员方法
		Method[] doubleArrMethods = clazzReflect.getCLazzMethods(Double[].class);
		logger.info("Double数组类型的公有成员方法个数为: " + doubleArrMethods.length);
		Arrays.asList(doubleArrMethods).forEach(doubleArrMethod 
				-> logger.info(doubleArrMethod.toString()));
	}
	
	/**
	 * 测试{@link Class#getDeclaredMethods()}方法获取指定类型中定义的方法
	 */
	@Test
	public void getClazzDeclareMethodsTest() {
		// 获取MethodServiceImpl类型中的方法
		Method[] methodServiceImplMethods = clazzReflect.getClazzDeclareMethods(MethodServiceImpl.class);
		logger.info("MethodServiceImpl类型的方法个数为: " + methodServiceImplMethods.length);
		Arrays.asList(methodServiceImplMethods).forEach(methodServiceImplMethod 
				-> logger.info(methodServiceImplMethod.toString()));
		
		// 获取Comparable类型的方法
		Method[] comparableMethods = clazzReflect.getClazzDeclareMethods(Comparable.class);
		logger.info("Comparable类型的方法个数为: " + comparableMethods.length);
		Arrays.asList(comparableMethods).forEach(comparableMethod
				-> logger.info(comparableMethod.toString()));
		
		// 获取double基本类型的方法
		Method[] doubleMethods = clazzReflect.getClazzDeclareMethods(double.class);
		logger.info("double基本类型的方法个数为: " + doubleMethods.length);
		Arrays.asList(doubleMethods).forEach(doubleMethod 
				-> logger.info(doubleMethod.toString()));
		
		// 获取Double数组基本类型的方法
		Method[] doubleArrMethods = clazzReflect.getClazzDeclareMethods(Double[].class);
		logger.info("Double数组类型的方法个数为: " + doubleArrMethods.length);
		Arrays.asList(doubleArrMethods).forEach(doubleArrMethod 
				-> logger.info(doubleArrMethod.toString()));
	}
}
