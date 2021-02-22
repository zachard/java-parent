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

import java.util.Map;

/**
 * description...
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class BrokerageAccount {
	
	/**
	 * 经纪账户号
	 */
	private String accountNumber;
	
	/**
	 * 经纪账户关联的客户
	 */
	private Customer customer;
	
	/**
	 * 经纪账户关联的投资
	 */
	private Map<String, Investment> investments;
	
	// 省略构造器, accountNumber的getter方法
	
	public Customer getCustomer() {
		return customer;
	}
	
	public Investment getInvestment(String stockSymbol) {
		return investments.get(stockSymbol);
	}
	
	// setters

}
