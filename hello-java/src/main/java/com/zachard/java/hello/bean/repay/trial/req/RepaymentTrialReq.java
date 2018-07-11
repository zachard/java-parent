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

package com.zachard.java.hello.bean.repay.trial.req;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 还款试算请求实体
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class RepaymentTrialReq {
	
	/**
	 * 还款方式
	 */
	private String repayMethod;
	
	/**
	 * 贷款金额
	 */
	private BigDecimal loanAmount;
	
	/**
	 * 贷款年华利率
	 */
	private BigDecimal loanInterest;
	
	/**
	 * 贷款期限(月)
	 */
	private Integer loanTerm;
	
	/**
	 * 贷款日期
	 */
	private Date loanDate;

	/**
	 * @return the repayMethod
	 */
	public String getRepayMethod() {
		return repayMethod;
	}

	/**
	 * @param repayMethod the repayMethod to set
	 */
	public void setRepayMethod(String repayMethod) {
		this.repayMethod = repayMethod;
	}

	/**
	 * @return the loanAmount
	 */
	public BigDecimal getLoanAmount() {
		return loanAmount;
	}

	/**
	 * @param loanAmount the loanAmount to set
	 */
	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}

	/**
	 * @return the loanInterest
	 */
	public BigDecimal getLoanInterest() {
		return loanInterest;
	}

	/**
	 * @param loanInterest the loanInterest to set
	 */
	public void setLoanInterest(BigDecimal loanInterest) {
		this.loanInterest = loanInterest;
	}

	/**
	 * @return the loanTerm
	 */
	public Integer getLoanTerm() {
		return loanTerm;
	}

	/**
	 * @param loanTerm the loanTerm to set
	 */
	public void setLoanTerm(Integer loanTerm) {
		this.loanTerm = loanTerm;
	}

	/**
	 * @return the loanDate
	 */
	public Date getLoanDate() {
		return loanDate;
	}

	/**
	 * @param loanDate the loanDate to set
	 */
	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}

}
