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

package com.zachard.java.noddd.domain.transfer.money.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zachard.java.noddd.domain.transfer.money.mapper.AccountMapper;
import com.zachard.java.noddd.domain.transfer.money.model.Account;

/**
 * description...
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
// @Service
public class FundsTransferService {
	
	private static Logger logger = LoggerFactory.getLogger(FundsTransferService.class);
	
	//@Resource 注解注入
	private AccountMapper accountMapper = new AccountMapper();
	
	// @Transcation  事务控制
	public void transfer(String fromId, String toId, double amount) {
		Account fromAccount = accountMapper.selectByAccountId(fromId);
		Account toAccount = accountMapper.selectByAccountId(toId);
		
		if (fromAccount == null || toAccount == null) {
			// 业务规则-转账业务双方账号必须是真实有效账号
			logger.error("从账户-{}向账户-{}转账时, 账户不存在", fromId, toId);
			throw new RuntimeException("账户不存在");
		}
		
		if (amount <= 0) {
			// 业务规则-转账金额必须大于0
			logger.error("从账户-{}向账户-{}转账时, 转账金额不正确, 转账金额为: {}", 
					fromAccount.getAccountId(), toAccount.getAccountId(), amount);
			throw new RuntimeException("转账金额不正确");
		}
		
		if (fromAccount.getBalance() < amount) {
			// 业务规则-转账之间必须校验当前账户余额是否充足
			logger.error("从账户-{}向账户-{}转账时, 账户余额不足, 账户余额为: {}, 转账金额为: {}", 
					fromAccount.getAccountId(), toAccount.getAccountId(), fromAccount.getBalance(), amount);
			throw new RuntimeException("账户余额不足");
		}
		
		logger.info("执行转账操作, 转账之前, 转出账户编号为: {}, 转出账户余额为: {}", fromAccount.getAccountId(), fromAccount.getBalance());
		logger.info("执行转账操作, 转账金额为: {}", amount);
		logger.info("执行转账操作, 转账之前, 转入账户编号为: {}, 转入账户余额为: {}", toAccount.getAccountId(), toAccount.getBalance());
		toAccount.setBalance(toAccount.getBalance() + amount);  // 转入账户增加相应的金额
		fromAccount.setBalance(fromAccount.getBalance() - amount);   // 转出账户减少相应的金额
		logger.info("执行转账操作, 转账之后, 转出账户编号为: {}, 转出账户余额为: {}", fromAccount.getAccountId(), fromAccount.getBalance());
		logger.info("执行转账操作, 转账之后, 转入账户编号为: {}, 转入账户余额为: {}", toAccount.getAccountId(), toAccount.getBalance());
		
		accountMapper.update(fromAccount);  // 将变更后的对象持久化到数据库
		accountMapper.update(toAccount);
	}

}
