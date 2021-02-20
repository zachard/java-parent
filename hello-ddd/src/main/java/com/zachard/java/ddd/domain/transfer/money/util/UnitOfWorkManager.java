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

package com.zachard.java.ddd.domain.transfer.money.util;

import java.util.ArrayList;
import java.util.List;

import com.zachard.java.ddd.domain.transfer.money.mapper.AccountMapper;
import com.zachard.java.ddd.domain.transfer.money.model.Account;

/**
 * 基础设施层
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class UnitOfWorkManager {
	
	/**
	 * 需要持久化的列表
	 */
	private List<Account> accountList = new ArrayList<Account>();
	
	private AccountMapper accountMapper = new AccountMapper();
	
	private UnitOfWorkManager() {
		// 做成单例, 构造器私有化
	}
	
	private static class UnitOfWorkManagerHolder {
		// 用于构造单例的内部类
		private static final UnitOfWorkManager INSTANCE = new UnitOfWorkManager();
	}
	
	/**
	 * 获取对象单例
	 * 
	 * @return
	 */
	public static final UnitOfWorkManager getInstance() {
		return UnitOfWorkManagerHolder.INSTANCE;
	}
	
	/**
	 * 将需要更新的账户添加到列表中
	 * 
	 * @param account
	 */
	public void addToUnitOfWork(Account account) {
		accountList.add(account);
	}
	
	/**
	 * 持久化账户信息
	 */
	public void confirm() {
		accountMapper.update(accountList);
	}
	
	/**
	 * 根据账户编号查询账户
	 * @param accountId
	 * @return
	 */
	public Account selectByAccountId(String accountId) {
		return accountMapper.selectByAccountId(accountId);
	}

}
