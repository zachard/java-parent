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

package com.zachard.java.test.noddd;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.zachard.java.noddd.domain.Cargo;
import com.zachard.java.noddd.domain.Voyage;
import com.zachard.java.noddd.domain.VoyageBookService;

/**
 * description...
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class VoyageBookServiceTest {
	
	Voyage voyage;
	private VoyageBookService voyageBookService;
	
	/**
	 * 初始化航船信息及处理Service
	 */
	@Before
	public void before() {
		voyage = new Voyage();
		voyage.setVoyageId("V0000");
		voyage.setCapacity(1000);
		voyage.setBookFactor(1.1);
		
		voyageBookService = new VoyageBookService();
	}
	
	@Test
	public void makeBookingTest() {
		Cargo cargo500 = new Cargo();
		cargo500.setCargoId("C0000");
		cargo500.setSize(500);
		int flag = voyageBookService.makeBooking(voyage, cargo500);
		assertTrue(flag > 0);
		
		Cargo cargo600 = new Cargo();
		cargo600.setCargoId("C1111");
		cargo600.setSize(600);
		flag = voyageBookService.makeBooking(voyage, cargo600);
		assertTrue(flag > 0);
		
		Cargo cargo100 = new Cargo();
		cargo100.setCargoId("C2222");
		cargo100.setSize(100);
		flag = voyageBookService.makeBooking(voyage, cargo100);
		assertFalse(flag > 0);
	}

}
