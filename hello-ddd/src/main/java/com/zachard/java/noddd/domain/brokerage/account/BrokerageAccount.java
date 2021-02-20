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
	 * 经纪账户中对应的投资
	 */
	private Set<Investment> investments;
	
	/**
	 * @return the accountNumber
	 */
	public String getAccountNumber() {
		return accountNumber;
	}
	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}
	/**
	 * @return the investments
	 */
	public Set<Investment> getInvestments() {
		return investments;
	}
	/**
	 * @param accountNumber the accountNumber to set
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	/**
	 * @param investments the investments to set
	 */
	public void setInvestments(Set<Investment> investments) {
		this.investments = investments;
	}

}
