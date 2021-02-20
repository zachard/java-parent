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

package com.zachard.java.ddd.domain.transfer.money.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zachard.java.ddd.domain.transfer.money.model.Account;
import com.zachard.java.ddd.domain.transfer.money.util.UnitOfWorkManager;

/**
 * description...
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class FundsTransferService {
	
	private static Logger logger = LoggerFactory.getLogger(FundsTransferService.class);
	private UnitOfWorkManager manager = UnitOfWorkManager.getInstance();  // 必须是单例
	
	/**
	 * 执行转账业务
	 * @param fromId
	 * @param toId
	 * @param amount
	 */
	//@Transcation事务注解
	public void transfer(String fromId, String toId, double amount) {
		Account fromAccount = manager.selectByAccountId(fromId);
		Account toAccount = manager.selectByAccountId(toId);
		
		if (fromAccount == null || toAccount == null) {
			// 业务规则-转账业务双方账号必须是真实有效账号
			logger.error("从账户-{}向账户-{}转账时, 账户不存在", fromId, toId);
			throw new RuntimeException("账户不存在");
		}
		
		logger.info("执行转账操作, 转账之前, 转出账户编号为: {}, 转出账户余额为: {}", fromAccount.getAccountId(), fromAccount.getBalance());
		logger.info("执行转账操作, 转账金额为: {}", amount);
		logger.info("执行转账操作, 转账之前, 转入账户编号为: {}, 转入账户余额为: {}", toAccount.getAccountId(), toAccount.getBalance());
		fromAccount.transferTo(toAccount, amount);  // 执行转账操作
		logger.info("执行转账操作, 转账之后, 转出账户编号为: {}, 转出账户余额为: {}", fromAccount.getAccountId(), fromAccount.getBalance());
		logger.info("执行转账操作, 转账之后, 转入账户编号为: {}, 转入账户余额为: {}", toAccount.getAccountId(), toAccount.getBalance());
		
		manager.confirm();  // 确认转账, 将数据持久化到数据库
	}

}
