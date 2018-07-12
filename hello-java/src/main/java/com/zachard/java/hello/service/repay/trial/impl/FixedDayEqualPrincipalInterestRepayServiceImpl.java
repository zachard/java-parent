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
import java.util.Date;
import java.util.List;

import com.zachard.java.hello.bean.repay.trial.req.RepaymentTrialReq;
import com.zachard.java.hello.bean.repay.trial.res.SingleRepayDetail;
import com.zachard.java.hello.bean.repay.trial.res.TotalRepayDetail;
import com.zachard.java.hello.service.repay.trial.AbstractRepaymentTrialService;

/**
 * 固定还款日等额本息还款方式还款计划
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class FixedDayEqualPrincipalInterestRepayServiceImpl extends AbstractRepaymentTrialService {

	/**
	 * 固定还款日等额本息还款计划试算
	 * 
	 * @param  req   贷款请求参数
	 * @return       还款计划详情
	 */
	@Override
	public TotalRepayDetail calculateRepayPlan(RepaymentTrialReq req) {
		// 1 计算每月利率
		BigDecimal perInterest = getMonthlyInterestRate(req.getLoanInterest());
		System.err.println("每期利率为: " + perInterest);
		
		// 2 计算实际应还期限
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(req.getLoanDate());
		int applyDay = calendar.get(Calendar.DAY_OF_MONTH);
		System.err.println("贷款申请的日期为当月的第 " + applyDay + "天");
		int realTerm;
		// 最后一期天数
		int lastTermDays;
		
		if (applyDay > FIXED_DAY + 1) {
			// 如果在固定还款日之后申请的借款, 还款计划需要多一期
			realTerm = req.getLoanTerm() + 1;
			lastTermDays = applyDay - FIXED_DAY;
		} else {
			realTerm = req.getLoanTerm();
			calendar.add(Calendar.MONTH, req.getLoanTerm());
			Date lastTermRepayDate = calendar.getTime();
			calendar.set(Calendar.DAY_OF_MONTH, FIXED_DAY);
			calendar.add(Calendar.MONTH, -1);
			long diff = lastTermRepayDate.getTime() - calendar.getTimeInMillis();
			lastTermDays = Integer.parseInt(String.valueOf(diff / MILLSEC_FOR_ONE_DAY));
		}
		
		// 3 计算每月还款额
		BigDecimal monthlyRepayAmount = getEqualPrincipalInterestTermAmount(req.getLoanAmount(), realTerm, perInterest);
		System.err.println("每月还款额为: " + monthlyRepayAmount);
		
		// 3. 计算各期还款详情
		TotalRepayDetail totalRepayDetail = new TotalRepayDetail();
		List<SingleRepayDetail> detailList = new ArrayList<>();
		// 3.1 每期剩余应还本金、累计还款利息、累计还款本金、累计还款总额
		BigDecimal remainingPriciple = req.getLoanAmount();
		BigDecimal totalRepayInsterest = BigDecimal.ZERO;
		BigDecimal totalRepayPrinciple = BigDecimal.ZERO;
		BigDecimal totalRepayAmount = BigDecimal.ZERO;
		
		// 3.1 计算第一期还款详情
		SingleRepayDetail firstDetail = getFirstTermRepayDetail(remainingPriciple, perInterest, req, monthlyRepayAmount);
		detailList.add(firstDetail);
		totalRepayAmount = totalRepayAmount.add(firstDetail.getShouldRepayAmount());
		totalRepayInsterest = totalRepayInsterest.add(firstDetail.getShouldRepayInterest());
		totalRepayPrinciple = totalRepayPrinciple.add(firstDetail.getShouldRepayPrinciple());
		remainingPriciple = firstDetail.getRemainingPrinciple();
		
		// 每期的利息和本金
		BigDecimal interest;
		BigDecimal principal;
		
		// 3.2 计算中间期数
		for (int i = 2; i < realTerm; i++) {
			SingleRepayDetail detail = new SingleRepayDetail();
			// 3.2.1 计算每期还款利息及累计总的还款利息
			interest = remainingPriciple.multiply(perInterest).setScale(LAST_SCALE, BigDecimal.ROUND_HALF_UP);
			detail.setShouldRepayInterest(interest);
			totalRepayInsterest = totalRepayInsterest.add(interest);
			// 3.2.2 计算每期还款本金及累计总的还款本金
			principal = monthlyRepayAmount.subtract(interest);
			detail.setShouldRepayPrinciple(principal);
			totalRepayPrinciple = totalRepayPrinciple.add(principal);
			// 3.2.3 计算每月还款总额及累计还款总额
			detail.setShouldRepayAmount(principal.add(interest));
			totalRepayAmount = totalRepayAmount.add(principal).add(interest);
			// 3.2.4 计算剩余还款本金
			remainingPriciple = remainingPriciple.subtract(principal);
			detail.setRemainingPrinciple(remainingPriciple);
			// 3.2.5 设置每期的还款日期及期数
			detail.setTerm(i);
			calendar.setTime(req.getLoanDate());
			calendar.add(Calendar.MONTH, i);
			calendar.set(Calendar.DAY_OF_MONTH, FIXED_DAY);
			detail.setShouldRepayDate(calendar.getTime());
			// 3.2.6 将还款明细添加到还款计划列表
			detailList.add(detail);
		}
		
		// 4 计算最后一期还款详情
		SingleRepayDetail lastDetail = getLastTermRepayDetail(remainingPriciple, perInterest, req, 
				realTerm, lastTermDays);
		detailList.add(lastDetail);
		totalRepayAmount = totalRepayAmount.add(lastDetail.getShouldRepayAmount());
		totalRepayPrinciple = totalRepayPrinciple.add(lastDetail.getShouldRepayPrinciple());
		totalRepayInsterest = totalRepayInsterest.add(lastDetail.getShouldRepayInterest());
		
		// 5 设置总的还款计划详情
		totalRepayDetail.setDetailList(detailList);
		totalRepayDetail.setTotalReapyPrinciple(totalRepayPrinciple);
		totalRepayDetail.setTotalRepayInterest(totalRepayInsterest);
		totalRepayDetail.setTotalRepayAmount(totalRepayAmount);
		totalRepayDetail.setTotalTerm(realTerm);
		
		return totalRepayDetail;
	}
	
	/**
	 * 计算第一期的还款详情
	 * 
	 * @param remainingPriciple   剩余还款本金
	 * @param perInterest         每期利息
	 * @param req                 贷款请求参数
	 * @param monthlyRepayAmount  每期还款总额
	 * @return                    第一期还款详情
	 */
	private SingleRepayDetail getFirstTermRepayDetail(BigDecimal remainingPriciple, 
			BigDecimal perInterest, RepaymentTrialReq req, BigDecimal monthlyRepayAmount) {
		// 计算第一期天数
		int days;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(req.getLoanDate());
		int loanDay = calendar.get(Calendar.DAY_OF_MONTH);
		
		if (loanDay == FIXED_DAY) {
			days = 30;
		} else {
			calendar.add(Calendar.MONTH, 1);
			calendar.set(Calendar.DAY_OF_MONTH, FIXED_DAY);
			long diff = calendar.getTimeInMillis() - req.getLoanDate().getTime();
			days = Integer.parseInt(String.valueOf(diff / MILLSEC_FOR_ONE_DAY));
		}
		
		SingleRepayDetail detail = new SingleRepayDetail();
		// 3.1.1 计算第一期还款利息
		BigDecimal interest = remainingPriciple.multiply(new BigDecimal(days))
				.multiply(perInterest).divide(DAYS_PER_MONTH, LAST_SCALE, BigDecimal.ROUND_HALF_UP);
		detail.setShouldRepayInterest(interest);
		// 3.1.2 计算第一期应还本金
		BigDecimal principal = monthlyRepayAmount.subtract(interest);
		detail.setShouldRepayPrinciple(principal);
		// 3.1.3 计算第一期还款总额
		detail.setShouldRepayAmount(principal.add(interest));
		// 3.1.4 计算期末剩余本金
		detail.setRemainingPrinciple(remainingPriciple.subtract(principal));
		// 3.1.5 设置还款期数及还款日期
		detail.setTerm(1);
		detail.setShouldRepayDate(calendar.getTime());
		
		return detail;
	} 
	
	/**
	 * 计算最后一期还款明细
	 * 
	 * @param remainingPriciple   最后一期剩余本金
	 * @param perInterest         每期利率
	 * @param req                 贷款详情
	 * @param realTerm            最后一期期数
	 * @param lastTermDays        最后一期借款天数
	 * @return                    最后一期还款详情
	 */
	private SingleRepayDetail getLastTermRepayDetail(BigDecimal remainingPriciple, BigDecimal perInterest, 
			RepaymentTrialReq req, int realTerm, int lastTermDays) {
		SingleRepayDetail detail = new SingleRepayDetail();
		// 4.1 设置最后一期期数及还款日期
		detail.setTerm(realTerm);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(req.getLoanDate());
		calendar.add(Calendar.MONTH, req.getLoanTerm());
		Date lastRepayDate = calendar.getTime();
		detail.setShouldRepayDate(lastRepayDate);
		// 4.2 计算最后一期利息
		BigDecimal interest = remainingPriciple.multiply(new BigDecimal(lastTermDays))
				.multiply(perInterest).divide(DAYS_PER_MONTH, LAST_SCALE, BigDecimal.ROUND_HALF_UP);
		detail.setShouldRepayInterest(interest);
		// 4.3 计算最后一期本金(还完所有剩余本金)
//		BigDecimal principal = monthlyRepayAmount.subtract(interest);
		BigDecimal principal = remainingPriciple;
		detail.setShouldRepayPrinciple(principal);
		// 4.4 计算最后一期还款总额
		detail.setShouldRepayAmount(principal.add(interest));
		// 4.5 计算最后一期剩余本金
		detail.setRemainingPrinciple(remainingPriciple.subtract(principal));
		
		return detail;
	}

}
