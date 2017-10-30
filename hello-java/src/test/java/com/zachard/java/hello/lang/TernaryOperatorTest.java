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

import org.junit.Before;
import org.junit.Test;

/**
 * description...
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class TernaryOperatorTest {
	
	private TernaryOperator operator;
	
	/**
	 * 测试初始化数据
	 */
	@Before
	public void before() {
		operator = new TernaryOperator();
	}
	
	/**
	 * 三目运算符类型转换测试
	 */
	@Test
	public void typeConversionTest() {
		operator.typeConversion();
	}

}
