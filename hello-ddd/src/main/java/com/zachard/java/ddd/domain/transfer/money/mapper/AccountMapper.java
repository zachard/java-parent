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

package com.zachard.java.ddd.domain.transfer.money.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.zachard.java.ddd.domain.transfer.money.model.Account;

/**
 * description...
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class AccountMapper {
	
	private List<Account> existAccountList = new ArrayList<Account>();
	
	/**
	 * 将账户更新持久化到数据库
	 * @param accountList
	 */
	public void update(List<Account> accountList) {
		//update set balance = ${balance} where accountId = ${accountId}
	}
	
	/**
	 * 根据账户编号查询账户
	 * 
	 * @param accountId
	 * @return
	 */
	public Account selectByAccountId(String accountId) {
		initAccountData();
		return existAccountList.stream()
				.filter(account -> Objects.equals(accountId, account.getAccountId()))
				.findFirst()
				.orElse(null);
	}
	
	/**
	 * 初始化账户在数据库中的数据
	 */
	private void initAccountData() {
		// 模拟存储在数据库中的账户数据
		Account a123 = new Account();
		a123.setAccountId("a123");
		a123.setBalance(10000.00);
		existAccountList.add(a123);
		Account a234 = new Account();
		a234.setAccountId("a234");
		a234.setBalance(5000.00);
		existAccountList.add(a234);
	}

}
