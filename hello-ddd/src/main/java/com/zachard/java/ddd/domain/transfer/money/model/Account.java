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

package com.zachard.java.ddd.domain.transfer.money.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zachard.java.ddd.domain.transfer.money.util.UnitOfWorkManager;

/**
 * 转账账户类
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class Account {
	
	private static Logger logger = LoggerFactory.getLogger(Account.class);
	
	private UnitOfWorkManager manager = UnitOfWorkManager.getInstance();  // 这里必须为单例
	
	/**
	 * 账户id
	 */
	private String accountId;
	
	/**
	 * 账户金额
	 */
	private double balance;

	/**
	 * @return the accountId
	 */
	public String getAccountId() {
		return accountId;
	}

	/**
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	/**
	 * 贷记
	 * @param amount
	 */
	public void credit(double amount) {
		this.balance = this.balance + amount;
		manager.addToUnitOfWork(this);
	}
	
	/**
	 * 借记
	 * @param amount
	 */
	public void debit(double amount) {
		this.balance = this.balance - amount;
		manager.addToUnitOfWork(this);
	}
	
	/**
	 * 执行转账操作
	 * 
	 * @param toAccount
	 * @param amount
	 */
	public void transferTo(Account toAccount, double amount) {
		
		if (amount <= 0) {
			// 业务规则-转账金额必须大于0
			logger.error("从账户-{}向账户-{}转账时, 转账金额不正确, 转账金额为: {}", 
					this.accountId, toAccount.accountId, amount);
			throw new RuntimeException("转账金额不正确");
		}
		
		if (this.balance < amount) {
			// 业务规则-转账之间必须校验当前账户余额是否充足
			logger.error("从账户-{}向账户-{}转账时, 账户余额不足, 账户余额为: {}, 转账金额为: {}", 
					this.accountId, toAccount.getAccountId(), this.balance, amount);  // 这里的日志是否合理
			throw new RuntimeException("账户余额不足");
		}
		
		toAccount.credit(amount);  // 将转账金额划入目标账户, 当前账户相应转出金额
		debit(amount);
	}

}
