/*
 *  Copyright 2015-2018 zachard, Inc.
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

package com.zachard.java.hello.bean.repay.trial.res;

import java.math.BigDecimal;
import java.util.List;

/**
 * 总的还款计划明细
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class TotalRepayDetail {

	/**
	 * 累计还款期数
	 */
	private Integer totalTerm;
	
	/**
	 * 累计还款总额
	 */
	private BigDecimal totalRepayAmount;
	
	/**
	 * 累计还款本金总额
	 */
	private BigDecimal totalReapyPrinciple;
	
	/**
	 * 累计还款利息总额
	 */
	private BigDecimal totalRepayInterest;
	
	/**
	 * 每期还款详情
	 */
	private List<SingleRepayDetail> detailList;

	/**
	 * @return the totalTerm
	 */
	public Integer getTotalTerm() {
		return totalTerm;
	}

	/**
	 * @param totalTerm the totalTerm to set
	 */
	public void setTotalTerm(Integer totalTerm) {
		this.totalTerm = totalTerm;
	}

	/**
	 * @return the totalRepayAmount
	 */
	public BigDecimal getTotalRepayAmount() {
		return totalRepayAmount;
	}

	/**
	 * @param totalRepayAmount the totalRepayAmount to set
	 */
	public void setTotalRepayAmount(BigDecimal totalRepayAmount) {
		this.totalRepayAmount = totalRepayAmount;
	}

	/**
	 * @return the totalReapyPrinciple
	 */
	public BigDecimal getTotalReapyPrinciple() {
		return totalReapyPrinciple;
	}

	/**
	 * @param totalReapyPrinciple the totalReapyPrinciple to set
	 */
	public void setTotalReapyPrinciple(BigDecimal totalReapyPrinciple) {
		this.totalReapyPrinciple = totalReapyPrinciple;
	}

	/**
	 * @return the totalRepayInterest
	 */
	public BigDecimal getTotalRepayInterest() {
		return totalRepayInterest;
	}

	/**
	 * @param totalRepayInterest the totalRepayInterest to set
	 */
	public void setTotalRepayInterest(BigDecimal totalRepayInterest) {
		this.totalRepayInterest = totalRepayInterest;
	}

	/**
	 * @return the detailList
	 */
	public List<SingleRepayDetail> getDetailList() {
		return detailList;
	}

	/**
	 * @param detailList the detailList to set
	 */
	public void setDetailList(List<SingleRepayDetail> detailList) {
		this.detailList = detailList;
	}
}
