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

package com.zachard.effective.java.inter.clazz;

import java.math.BigInteger;

import org.junit.Test;

/**
 * <code>Effitive Java</code>第四章: 类和接口
 * 使可变性最小化 
 *    -- 不可变类唯一缺点测试
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class FinalClazzDefectTest {
	
	/**
	 * 不可变类缺点测试
	 */
	@Test
	public void defectTest() {
		BigInteger moby = new BigInteger("1000000");
		BigInteger other = moby.flipBit(0);
		
		byte[] mobyByte = moby.toByteArray();
		byte[] otherByte = other.toByteArray();
		
		System.err.println(moby);
		System.err.println(other);
	} 

}
