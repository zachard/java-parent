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

package com.zachard.java.ddd.domain.brokerage.account;

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
	 * 经纪账户账户号
	 */
	private String accountNumber;
	
	/**
	 * 经纪账户统一社会安全码
	 */
	private String customerSocialSecurityNumber;
	
	// 省略构造器, accountNumber的getter方法
	
	/**
	 * 返回经纪账户关联的客户
	 * 
	 * @return
	 */
	public Customer getCustomer() {
		String sqlQuery = 
				"select * from CUSTOMER where SS_NUMBER = '" + customerSocialSecurityNumber + "'";
//		return customerMapper.selectBySSNumber(sqlQuery);
		return null;
	}
	
	/**
	 * 返回经纪账户投资中对应的股票投资
	 * 
	 * @param stockSymbol
	 * @return
	 */
	public Investment getInvestment(String stockSymbol) {
		String sqlQuery = 
				"select * from INVESTMENT where BROKERAGE_ACCOUNT = '" + accountNumber + "'" 
				+ " AND STOCK_SYMBOL = '" + stockSymbol + "'";
//		return investmentMapper.selectByAccountNumberAndStockSymbol;
		return null;
	}

}
