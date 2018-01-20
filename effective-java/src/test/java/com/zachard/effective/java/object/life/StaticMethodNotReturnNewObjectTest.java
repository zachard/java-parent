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

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <code>Effitive Java</code>第二章: 创建和销毁对象
 * 采用静态工程方法创建对象相比于构造器的优势 -- 静态方法不必在每次调用它们的时候创建新对象
 * 测试类
 * 
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class StaticMethodNotReturnNewObjectTest {
	
	private static final Logger logger = LoggerFactory.getLogger(StaticMethodNotReturnNewObjectTest.class);
	
	/**
	 * 测试通过构造器创建的对象为不同实例对象, 但是静态工厂方法可以返回相同的实例对象
	 */
	@Test
	public void testBasic() {
		/*
		 *  通过构造器创建两个对象, 比较它们是否相等
		 */
		StaticMethodNotReturnNewObject constructorObject1 = new StaticMethodNotReturnNewObject();
		StaticMethodNotReturnNewObject constructorObject2 = new StaticMethodNotReturnNewObject();
		logger.info("通过构造器创建的第一个对象为: {}", constructorObject1);
		logger.info("通过构造器创建的第二个对象为: {}", constructorObject2);
		logger.info("通过构造器创建的两个对象是否相等: {}", constructorObject1 == constructorObject2 );
		
		/*
		 * 通过静态工厂方法创建两个对象, 比较它们是否相等
		 */
		StaticMethodNotReturnNewObject staticMethodObject1 = StaticMethodNotReturnNewObject.instance();
		StaticMethodNotReturnNewObject staticMethodObject2 = StaticMethodNotReturnNewObject.instance();
		logger.info("通过静态工厂方法创建的第一个对象为: {}", staticMethodObject1);
		logger.info("通过静态工厂方法创建的第二个对象为: {}", staticMethodObject2);
		logger.info("通过静态工厂方法创建的两个对象是否相等: {}", staticMethodObject1 == staticMethodObject2);
	}

}
