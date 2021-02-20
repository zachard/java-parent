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

package com.zachard.java.noddd.domain.transfer.money.controller;

import com.zachard.java.noddd.domain.transfer.money.dto.TransferReq;
import com.zachard.java.noddd.domain.transfer.money.dto.TransferResp;
import com.zachard.java.noddd.domain.transfer.money.service.FundsTransferService;

/**
 * description...
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
//@RestController
public class TransferController {
	
	// @Autowire
	private FundsTransferService fundsTransferService = new FundsTransferService();
	
	/**
	 * 转账操作接口
	 * @param req
	 * @return
	 */
	// @RequestMapping("/transfer")
	// @RequestBody
	public TransferResp transfer(TransferReq req) {
		fundsTransferService.transfer(req.getFromId(), req.getToId(), req.getAmount());
		return new TransferResp();
	}

}
