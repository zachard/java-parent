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

package com.zachard.java.test.ddd.transfer.money;

import org.junit.Before;
import org.junit.Test;

import com.zachard.java.ddd.domain.transfer.money.controller.TransferController;
import com.zachard.java.ddd.domain.transfer.money.dto.TransferReq;

/**
 * description...
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class TransferControllerTest {
	
	private TransferController controller;
	
	@Before
	public void before() {
		controller = new TransferController();
	}
	
	@Test
	public void transferTest() {
		TransferReq req = new TransferReq();
		req.setFromId("a123");
		req.setToId("a234");
		req.setAmount(1000.00);
		controller.transfer(req);
	}

}
