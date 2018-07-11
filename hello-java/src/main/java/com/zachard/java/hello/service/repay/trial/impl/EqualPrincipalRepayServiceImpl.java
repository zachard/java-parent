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
 * 等额本金还款方式还款计划试算
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class EqualPrincipalRepayServiceImpl extends AbstractRepaymentTrialService {

	/**
	 * 计算等额本金还款方式还款计划
	 * 
	 * @param  req   贷款请求参数
	 * @return       还款计划详情
	 */
	@Override
	public TotalRepayDetail calculateRepayPlan(RepaymentTrialReq req) {
		// 1 计算每期利率
		BigDecimal perInterest = getMonthlyInterestRate(req.getLoanInterest());
		System.err.println("每期利率为: " + perInterest);
		
		// 2. 计算每月还款本金
		BigDecimal monthlyRepayPrincipal = req.getLoanAmount().divide(new BigDecimal(req.getLoanTerm()), LAST_SCALE, BigDecimal.ROUND_HALF_UP);
		System.err.println("每月还款本金为: " + monthlyRepayPrincipal);
		
		// 3. 计算还款计划的还款详情
		TotalRepayDetail totalRepayDetail = new TotalRepayDetail();
		List<SingleRepayDetail> detailList = new ArrayList<>();
		// 3.1 每期剩余应还本金、累计还款利息、累计还款本金、累计还款总额
		BigDecimal remainingPriciple = req.getLoanAmount();
		BigDecimal totalRepayInsterest = BigDecimal.ZERO;
		BigDecimal totalRepayPrinciple = BigDecimal.ZERO;
		BigDecimal totalRepayAmount = BigDecimal.ZERO;
		
		// 每期应还的利息、本金
		BigDecimal insterest;
		// 计算每期还款日期
		Calendar calendar = Calendar.getInstance();
		
		for (int i = 1; i < req.getLoanTerm() + 1; i++) {
			SingleRepayDetail detail = new SingleRepayDetail();
			
			if (i == req.getLoanTerm()) {
				// 最后一期还款本金应该等于剩余的本金, 避免小数点不精确导致还款总额不对称
				// 注: 如果最后一期不这样处理, 会引起用户少还一点钱, 10万、4.35%、12期少还0.04元
				//     但是如果这样处理, 会导致最后一期的还款本金比前面期本金多0.04元
				monthlyRepayPrincipal = remainingPriciple;
			}
			
			// 3.2 本期应还利息及累计还款利息
			insterest = remainingPriciple.multiply(perInterest).setScale(LAST_SCALE, BigDecimal.ROUND_HALF_UP);
			detail.setShouldRepayInterest(insterest);
			totalRepayInsterest = totalRepayInsterest.add(insterest);
			// 3.3 本期应还本金及累计还款本金
			detail.setShouldRepayPrinciple(monthlyRepayPrincipal);
			totalRepayPrinciple = totalRepayPrinciple.add(monthlyRepayPrincipal);
			// 3.4 每期还款金额及累计还款总额
			detail.setShouldRepayAmount(monthlyRepayPrincipal.add(insterest));
			totalRepayAmount = totalRepayAmount.add(monthlyRepayPrincipal).add(insterest);
			// 3.5 每期之后剩余本金
			remainingPriciple = remainingPriciple.subtract(monthlyRepayPrincipal);
			detail.setRemainingPrinciple(remainingPriciple);
			// 3.6 设置相应期数及还款时间
			detail.setTerm(i);
			calendar.setTime(req.getLoanDate());
			calendar.add(Calendar.MONTH, i);
			detail.setShouldRepayDate(calendar.getTime());
			// 将还款详情添加到列表
			detailList.add(detail);
		}
		
		totalRepayDetail.setDetailList(detailList);
		totalRepayDetail.setTotalReapyPrinciple(totalRepayPrinciple);
		totalRepayDetail.setTotalRepayInterest(totalRepayInsterest);
		totalRepayDetail.setTotalRepayAmount(totalRepayAmount);
		totalRepayDetail.setTotalTerm(req.getLoanTerm());  // 还款期数就是贷款期数
		
		return totalRepayDetail;
	}

}
