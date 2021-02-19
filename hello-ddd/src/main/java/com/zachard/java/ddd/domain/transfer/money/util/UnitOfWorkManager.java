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
	
	/**
	 * 将需要更新的账户添加到列表中
	 * 
	 * @param account
	 */
	public void addToUnitOfWork(Account account) {
		accountList.add(account);
	}
	
	public void confirm() {
		//
	}

}
