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
 * 采用静态工程方法创建对象相比于构造器的优势 -- 静态工厂方法不必在每次调用它们的时候创建新对象
 * 
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class StaticMethodNotReturnNewObject {
	
	/**
	 * 将对象声明为volatile
	 */
	private volatile static StaticMethodNotReturnNewObject instance;
	
	/**
	 * 对象默认构造器
	 */
	public StaticMethodNotReturnNewObject() {
		
	}
	
	/**
	 * 静态工厂方法 -- 通过双检锁机制, 每次调用的时候返回相同的对象
	 * 
	 * <pre>
	 *     某些时刻会返回不为同一对象的实例, 
	 *     要想保证一直返回同一个对象, 参考单例模式的实现
	 * </pre>
	 * 
	 * @return   类实例对象
	 */
	public static StaticMethodNotReturnNewObject instance() {
		if (instance == null) {
			synchronized (StaticMethodNotReturnNewObject.class) {
				if (instance == null) {
					instance = new StaticMethodNotReturnNewObject();
				}
			}
		}
		
		return instance;
	}

}
