/*
 *  Copyright 2015-2021 zachard, Inc.
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

package com.zachard.java.test.ddd;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.zachard.java.ddd.domain.Cargo;
import com.zachard.java.ddd.domain.Voyage;
import com.zachard.java.ddd.domain.overbook.policy.FactorOverbookingPolicyImpl;
import com.zachard.java.ddd.domain.overbook.policy.OverbookingPolicy;

/**
 * description...
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class VoyageTest {
	
	private Voyage voyage;
	
	@Before
	public void before() {
		// 初始化航船的相关信息
		voyage = new Voyage();
		voyage.setCapacity(1000);
		OverbookingPolicy overbookingPolicy = new FactorOverbookingPolicyImpl();
		voyage.setOverbookingPolicy(overbookingPolicy);
		voyage.setBookFactor(1.1);
	}
	
	/**
	 * 航船预定测试
	 */
	@Test
	public void makeBookingTest() {
		Cargo cargo500 = new Cargo();
		cargo500.setSize(500);
		int bookedSize = voyage.makeBooking(cargo500, voyage);
		assertTrue(bookedSize > 0);  // 先向航船添加一个货物
		
		Cargo cargo600 = new Cargo();
		cargo600.setSize(600);
		bookedSize = voyage.makeBooking(cargo600, voyage);
		assertTrue(bookedSize > 0); // 再向航船添加一个货物
		
		Cargo cargo100 = new Cargo();
		cargo100.setSize(100);
		bookedSize = voyage.makeBooking(cargo100, voyage);
		assertFalse(bookedSize > 0);  // 此时已经超载了
	}

}
