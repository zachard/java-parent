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

package com.zachard.java.hello.service.repay.trial.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.zachard.java.hello.bean.repay.trial.req.RepaymentTrialReq;
import com.zachard.java.hello.bean.repay.trial.res.SingleRepayDetail;
import com.zachard.java.hello.bean.repay.trial.res.TotalRepayDetail;
import com.zachard.java.hello.service.repay.trial.AbstractRepaymentTrialService;

/**
 * 等额本息还款方式还款方式计算
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class EqualPrincipalInterestRepayService extends AbstractRepaymentTrialService {

	/**
	 * 等额本息方式计算还款计划
	 * 
	 * @param req  贷款请求参数
	 * @return     还款计划详情
	 */
	@Override
	public TotalRepayDetail calculateRepayPlan(RepaymentTrialReq req) {
		// 1 计算每期利率
		BigDecimal perInterest = req.getLoanInterest().divide(PERCENTAGE).divide(TWELVE_MONTHS, SCALE, BigDecimal.ROUND_HALF_UP);
		System.err.println("每期利率为: " + perInterest);
					
		// 2. 计算每月固定还款额
		BigDecimal monthlyRepayAmount;
		
		if (BigDecimal.ZERO.compareTo(req.getLoanInterest()) == 0) {
			// 利率为0的情况
			monthlyRepayAmount = req.getLoanAmount().divide(new BigDecimal(req.getLoanTerm()), LAST_SCALE, BigDecimal.ROUND_HALF_UP);
		} else {
			// 2.1 计算分母 (1+r)^N-1
			BigDecimal denominator = BigDecimal.ONE.add(perInterest).pow(req.getLoanTerm()).subtract(BigDecimal.ONE);
			System.err.println("分母为: " + denominator);
			// 2.2 计算分子 P*r*(1+r)^N
			BigDecimal numerator = BigDecimal.ONE.add(perInterest).pow(req.getLoanTerm()).multiply(perInterest).multiply(req.getLoanAmount());
			System.err.println("分子为: " + numerator);
			// 2.3 计算每月还款额 = 分子 / 分母
			monthlyRepayAmount = numerator.divide(denominator, LAST_SCALE, BigDecimal.ROUND_HALF_UP);
		}
		System.err.println("每月还款额为: " + monthlyRepayAmount);
		
		// 3. 计算各期还款详情
		TotalRepayDetail totalRepayDetail = new TotalRepayDetail();
		List<SingleRepayDetail> detailList = new ArrayList<>();
		// 3.1 每期剩余应还本金、累计还款利息、累计还款本金、累计还款总额
		BigDecimal remainingPriciple = req.getLoanAmount();
		BigDecimal totalRepayInsterest = BigDecimal.ZERO;
		BigDecimal totalRepayPrinciple = BigDecimal.ZERO;
		BigDecimal totalRepayAmount = BigDecimal.ZERO;
		
		// 每期应还的利息、本金
		BigDecimal insterest;
		BigDecimal principle;
		// 计算每期还款日期
		Calendar calendar = Calendar.getInstance();
		
		for (int i = 1; i < req.getLoanTerm() + 1; i++) {
			SingleRepayDetail detail = new SingleRepayDetail();
			// 3.2 本期应还利息并累计还款利息
			insterest = remainingPriciple.multiply(perInterest).setScale(LAST_SCALE, BigDecimal.ROUND_HALF_UP);
			detail.setShouldRepayInterest(insterest);
			totalRepayInsterest = totalRepayInsterest.add(insterest);
			// 3.3 本期应还本金并累计还款本金
			
			if (i == req.getLoanTerm()) {
				// 最后一期应还本金就等于上一期剩余的本金, 防止小数点不精确导致的计算问题
				principle = remainingPriciple;
			} else {
				principle = monthlyRepayAmount.subtract(insterest);
			}
			 
			detail.setShouldRepayPrinciple(principle);
			totalRepayPrinciple = totalRepayPrinciple.add(principle);
			// 3.4 累计还款金额
			totalRepayAmount = totalRepayAmount.add(insterest).add(principle);
			// 3.5 计算每月剩余还款本金
			remainingPriciple = remainingPriciple.subtract(principle);
			detail.setRemainingPrinciple(remainingPriciple);
			// 3.6 设置期数及本期应还日期
			detail.setTerm(i);
			calendar.setTime(req.getLoanDate());
			calendar.add(Calendar.MONTH, i);
			detail.setShouldRepayDate(calendar.getTime());
			// 3.7 将每期还款详情添加到还款详情列表
			detailList.add(detail);
		}
		
		totalRepayDetail.setDetailList(detailList);
		totalRepayDetail.setTotalReapyPrinciple(totalRepayPrinciple);
		totalRepayDetail.setTotalRepayInterest(totalRepayInsterest);
		totalRepayDetail.setTotalRepayAmount(totalRepayAmount);
		totalRepayDetail.setTotalTerm(req.getLoanTerm());  // 总的还款期数就是申请的期数
		
		return totalRepayDetail;
	}

}
