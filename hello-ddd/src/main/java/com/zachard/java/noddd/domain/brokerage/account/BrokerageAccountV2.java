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

package com.zachard.java.noddd.domain.brokerage.account;

import java.util.Set;

/**
 * description...
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class BrokerageAccountV2 {
	
	/**
	 * 经纪账户号
	 */
	private String accountNumber;
	
	/**
	 * 经纪账户关联的客户的社会安全码
	 */
	private String customerSocialSecurityNumber;

	/**
	 * @return the accountNumber
	 */
	public String getAccountNumber() {
		return accountNumber;
	}
	
	/**
	 * 返回经纪账户关联的客户对象
	 * @return
	 */
	public Customer getCustomer() {
		// 根据社会安全码来返回相应的客户对象
//		String sqlQuery = 
//				"select * from CUSTOMER where SS_NUMBER = '" + customerSocialSecurityNumber + "'";
		// return customerMapper.selectBySSNumber(sqlQuery);
		return null;   // 应该注释的是这样
	}
	
	/**
	 * 返回经纪账户关联的投资列表
	 * @return
	 */
	public Set<Investment> getInvestments() {
		//根据经纪账户号查询投资列表
//		String sqlQuery = 
//				"select * from INVESTMENT where BROKERAGE_ACCOUNT = '" + accountNumber + "'";
//		return investmentMapper.selectByBrokerageAccount(sqlQuery);
		return null;
	}

}
