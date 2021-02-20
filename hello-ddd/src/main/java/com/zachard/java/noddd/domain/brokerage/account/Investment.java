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

/**
 * description...
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class Investment {
	
	/**
	 * 投资代码
	 */
	private String stockSymbol;
	
	/**
	 * 投资份额
	 */
	private double numberOfShares;

	/**
	 * @return the stockSymbol
	 */
	public String getStockSymbol() {
		return stockSymbol;
	}

	/**
	 * @return the numberOfShares
	 */
	public double getNumberOfShares() {
		return numberOfShares;
	}

	/**
	 * @param stockSymbol the stockSymbol to set
	 */
	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	/**
	 * @param numberOfShares the numberOfShares to set
	 */
	public void setNumberOfShares(double numberOfShares) {
		this.numberOfShares = numberOfShares;
	}

}
