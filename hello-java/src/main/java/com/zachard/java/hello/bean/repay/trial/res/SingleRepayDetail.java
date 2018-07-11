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
import java.util.Date;

/**
 * 每一期还款明细响应实体
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class SingleRepayDetail {
	
	/**
	 * 还款期数
	 */
	private Integer term;
	
	/**
	 * 本期应还日期
	 */
	private Date shouldRepayDate;
	
	/**
	 * 本期应还总额
	 */
	private BigDecimal shouldRepayAmount;
	
	/**
	 * 本期应还本金
	 */
	private BigDecimal shouldRepayPrinciple;
	
	/**
	 * 本期应还利息
	 */
	private BigDecimal shouldRepayInterest;
	
	/**
	 * 剩余本金金额
	 */
	private BigDecimal remainingPrinciple;

	/**
	 * @return the term
	 */
	public Integer getTerm() {
		return term;
	}

	/**
	 * @param term the term to set
	 */
	public void setTerm(Integer term) {
		this.term = term;
	}

	/**
	 * @return the shouldRepayDate
	 */
	public Date getShouldRepayDate() {
		return shouldRepayDate;
	}

	/**
	 * @param shouldRepayDate the shouldRepayDate to set
	 */
	public void setShouldRepayDate(Date shouldRepayDate) {
		this.shouldRepayDate = shouldRepayDate;
	}

	/**
	 * @return the shouldRepayAmount
	 */
	public BigDecimal getShouldRepayAmount() {
		return shouldRepayAmount;
	}

	/**
	 * @param shouldRepayAmount the shouldRepayAmount to set
	 */
	public void setShouldRepayAmount(BigDecimal shouldRepayAmount) {
		this.shouldRepayAmount = shouldRepayAmount;
	}

	/**
	 * @return the shouldRepayPrinciple
	 */
	public BigDecimal getShouldRepayPrinciple() {
		return shouldRepayPrinciple;
	}

	/**
	 * @param shouldRepayPrinciple the shouldRepayPrinciple to set
	 */
	public void setShouldRepayPrinciple(BigDecimal shouldRepayPrinciple) {
		this.shouldRepayPrinciple = shouldRepayPrinciple;
	}

	/**
	 * @return the shouldRepayInterest
	 */
	public BigDecimal getShouldRepayInterest() {
		return shouldRepayInterest;
	}

	/**
	 * @param shouldRepayInterest the shouldRepayInterest to set
	 */
	public void setShouldRepayInterest(BigDecimal shouldRepayInterest) {
		this.shouldRepayInterest = shouldRepayInterest;
	}

	/**
	 * @return the remainingPrinciple
	 */
	public BigDecimal getRemainingPrinciple() {
		return remainingPrinciple;
	}

	/**
	 * @param remainingPrinciple the remainingPrinciple to set
	 */
	public void setRemainingPrinciple(BigDecimal remainingPrinciple) {
		this.remainingPrinciple = remainingPrinciple;
	}

}
