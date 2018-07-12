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

package com.zachard.java.hello.service.repay.trial;

import java.math.BigDecimal;

/**
 * 还款计划抽象类
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public abstract class AbstractRepaymentTrialService implements RepaymentTrialService {
	
	/**
	 * 一年十二个月
	 */
	protected static final BigDecimal TWELVE_MONTHS = new BigDecimal(12);
	
	/**
	 * 百分比
	 */
	protected static final BigDecimal PERCENTAGE = new BigDecimal(100);
	
	/**
	 * 小数点精度
	 */
	protected static final int SCALE = 20;
	
	/**
	 * 最终保留的小数点精度
	 */
	protected static final int LAST_SCALE = 2;
	
	/**
	 * 每月固定还款日
	 */
	protected static final int FIXED_DAY = 21;
	
	/**
	 * 每月固定天数大整数表示
	 */
	protected static final BigDecimal DAYS_PER_MONTH = new BigDecimal(30);
	
	/**
	 * 一天拥有的毫秒数
	 */
	protected static final long MILLSEC_FOR_ONE_DAY = 1000 * 3600 * 24;
	
	/**
	 * 将贷款年利率转换为月利率
	 * 
	 * @param yearRate   贷款年利率
	 * @return           贷款月利率
	 */
	protected BigDecimal getMonthlyInterestRate(BigDecimal yearRate) {
		return yearRate.divide(PERCENTAGE)
				.divide(TWELVE_MONTHS, SCALE, BigDecimal.ROUND_HALF_UP);
	} 
	
	/**
	 * 等额本息算法计算每月还款额 
	 * 
	 * @param principal     本金
	 * @param term          贷款期限
	 * @param perInterest   贷款月利率
	 * @return              等额本息每月还款额
	 */
	protected BigDecimal getEqualPrincipalInterestTermAmount(BigDecimal principal, int term, BigDecimal perInterest) {
		BigDecimal monthlyRepayAmount;
		
		if (BigDecimal.ZERO.compareTo(perInterest) == 0) {
			// 利率为0的情况
			monthlyRepayAmount = principal.divide(new BigDecimal(term), LAST_SCALE, BigDecimal.ROUND_HALF_UP);
		} else {
			// 2.1 计算分母 (1+r)^N-1
			BigDecimal denominator = BigDecimal.ONE.add(perInterest).pow(term).subtract(BigDecimal.ONE);
			System.err.println("分母为: " + denominator);
			// 2.2 计算分子 P*r*(1+r)^N
			BigDecimal numerator = BigDecimal.ONE.add(perInterest).pow(term).multiply(perInterest).multiply(principal);
			System.err.println("分子为: " + numerator);
			// 2.3 计算每月还款额 = 分子 / 分母
			monthlyRepayAmount = numerator.divide(denominator, LAST_SCALE, BigDecimal.ROUND_HALF_UP);
		}
		
		return monthlyRepayAmount;
	} 

}
