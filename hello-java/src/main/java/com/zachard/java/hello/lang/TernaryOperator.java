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

package com.zachard.java.hello.lang;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 三目运算符相关测试类
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class TernaryOperator {
	
	private static final Logger logger = LoggerFactory.getLogger(TernaryOperator.class);
	
	/**
	 * 三目运算符类型转换测试方法
	 * 
	 * <pre>
	 *     注: 1.转换规则如下:
	 *           (1) 若两个操作数不可转换，则不做转换，返回值为Object类型
	 *           (2) 若两个操作数是明确的类型，按照基础数据类型的转换规则来处理(boolean除外)
	 *               转换依据: 从存储范围小的类型到存储范围大的类型
	 *               示例: byte -> short(char) -> int -> long -> float -> double
	 *           (3) 若两个操作数，一个是数字S，一个是表达式，类型标记为T，若S在T的范围之内，则S转换为T类型，若S在T的范围之外，则T转换为S类型
	 *           (4) 若两个操作数都是直接量数字(字面量), 则返回值为范围较大的
	 *         2.转换是在编译器完成,类型不能转换为目标接收类型时,会出现编译期错误
	 * </pre>
	 */
	public void typeConversion() {
		// 判断条件值
		int condition = 9;
		
		// 对于两个类型不能转换的情况,不进行转换,返回值为Object类型
		Object cannotConversion = condition < 10 ? new Date() : Integer.SIZE;
		logger.info("三目运算符结果类型为: " + cannotConversion.getClass());
		condition = 11;
		cannotConversion = condition < 10 ? new Date() : Integer.SIZE;
		logger.info("三目运算符结果类型为: " + cannotConversion.getClass());
		
		// 对于两个明确类型的操作数,按照基础数据类型的转换规则来处理(不包括基础类型)
		String intConversion = String.valueOf(condition < 10 ? 90 : 100);
		logger.info("操作数类型相同时的处理: " + intConversion);
		String doubleConversion = String.valueOf(condition < 10 ? 90.0 : 100);
		logger.info("操作数类型不相同时的处理: " + doubleConversion);
	}

}
