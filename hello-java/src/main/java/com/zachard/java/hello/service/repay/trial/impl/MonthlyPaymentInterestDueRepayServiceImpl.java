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
 * 按月付息到期一次性归还本金还款方式还款计划试算
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class MonthlyPaymentInterestDueRepayServiceImpl extends AbstractRepaymentTrialService {
	
	/**
	 * 构造器私有化, 保持为单例模式
	 */
	private MonthlyPaymentInterestDueRepayServiceImpl() {}

	/**
	 * 按月付息到期一次性归还本金还款方式
	 * 
	 * @param req   贷款请求参数
	 * @return      贷款还款计划
	 */
	@Override
	public TotalRepayDetail calculateRepayPlan(RepaymentTrialReq req) {
		// 1 计算每期利率
		BigDecimal perInterest = getMonthlyInterestRate(req.getLoanInterest());
		System.err.println("每期利率为: " + perInterest);
		
		// 2 计算每月还款利率(因为最后一期到期归还本金, 所以每期利率一致)
		BigDecimal monthlyRepayInterest = req.getLoanAmount().multiply(perInterest).setScale(LAST_SCALE, BigDecimal.ROUND_HALF_UP);
		System.err.println("每期还款利息为: " + monthlyRepayInterest);
		
		// 3. 计算还款计划的还款详情
		TotalRepayDetail totalRepayDetail = new TotalRepayDetail();
		List<SingleRepayDetail> detailList = new ArrayList<>();
		// 3.1 累计还款利息、累计还款本金、累计还款总额
		BigDecimal totalRepayInsterest = BigDecimal.ZERO;
		BigDecimal totalRepayPrinciple = BigDecimal.ZERO;
		BigDecimal totalRepayAmount = BigDecimal.ZERO;
		
		// 计算每期还款日期
		Calendar calendar = Calendar.getInstance();
		
		for (int i = 1; i < req.getLoanTerm() + 1; i++) {
			SingleRepayDetail detail = new SingleRepayDetail();
			// 3.2 每月还款利息及总的还款利息
			detail.setShouldRepayInterest(monthlyRepayInterest);
			totalRepayInsterest = totalRepayInsterest.add(monthlyRepayInterest);
			
			// 3.3  每月还款本金及总的还款本金
			// 3.4 每月还款总额及总的还款额
			// 3.5 每月剩余应还本金
			if (i == req.getLoanTerm()) {
				// 对最后一期做处理, 因为这一些需要归还本金, 剩余本金为0, 应还本金为贷款本金, 应还总额为贷款本金加上本期利息
				detail.setRemainingPrinciple(BigDecimal.ZERO);
				detail.setShouldRepayPrinciple(req.getLoanAmount());
				detail.setShouldRepayAmount(req.getLoanAmount().add(monthlyRepayInterest));
			} else {
				// 除最后一期, 每期剩余本金为贷款本金、应还本金为0, 应还总额为利息
				detail.setRemainingPrinciple(req.getLoanAmount());
				detail.setShouldRepayPrinciple(BigDecimal.ZERO);
				detail.setShouldRepayAmount(monthlyRepayInterest);
			}
			
			totalRepayPrinciple = totalRepayPrinciple.add(detail.getShouldRepayPrinciple());
			totalRepayAmount = totalRepayAmount.add(detail.getShouldRepayAmount());
			
			// 3.6 每月还款日期及期数
			// 注: 招商银行的计算器是固定为21号还款, 并按日计息, 是否有必要这么处理
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
	
	/**
	 * 单例对象获取方法
	 * 
	 * @return   单例对象
	 */
	public static MonthlyPaymentInterestDueRepayServiceImpl getInstance() {
		return SingletonHolder.INSTANCE;
	} 
	
	/**
	 * 保持单例模式所需内部类
	 * 
	 * @author richard
	 *
	 */
	private static class SingletonHolder {
		private static final MonthlyPaymentInterestDueRepayServiceImpl INSTANCE = 
				new MonthlyPaymentInterestDueRepayServiceImpl();
	}

}
