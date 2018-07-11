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
 * 到期一次性还本付息还款方式还款计划
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class RepaymentInterestMaturityRepayServiceImpl extends AbstractRepaymentTrialService {

	/**
	 * 到期一次性还本付息还款计划计算
	 * 
	 * @param  req   贷款请求参数
	 * @return       还款计划
	 */
	@Override
	public TotalRepayDetail calculateRepayPlan(RepaymentTrialReq req) {
		// 1 计算每期利率
		BigDecimal perInterest = getMonthlyInterestRate(req.getLoanInterest());
		System.err.println("每期利率为: " + perInterest);
		
		// 2 计算还款利息
		BigDecimal repayInterest = req.getLoanAmount().multiply(perInterest)
				.multiply(new BigDecimal(req.getLoanTerm())).setScale(LAST_SCALE, BigDecimal.ROUND_HALF_UP);
		System.err.println("总的还款利息额为: " + repayInterest);
		
		// 3 只需要还款一期
		TotalRepayDetail totalRepayDetail = new TotalRepayDetail();
		List<SingleRepayDetail> detailList = new ArrayList<>();
		SingleRepayDetail detail = new SingleRepayDetail();
		
		// 3.1 唯一一期的还款明细
		// 注: 招行的计算器是按日计息, 是否有必要修改
		detail.setRemainingPrinciple(BigDecimal.ZERO);
		detail.setShouldRepayAmount(req.getLoanAmount().add(repayInterest));
		detail.setShouldRepayInterest(repayInterest);
		detail.setShouldRepayPrinciple(req.getLoanAmount());
		detail.setTerm(1);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(req.getLoanDate());
		calendar.add(Calendar.MONTH, req.getLoanTerm());
		detail.setShouldRepayDate(calendar.getTime());
		detailList.add(detail);
		
		// 3.4 设置总的还款详情
		totalRepayDetail.setDetailList(detailList);
		totalRepayDetail.setTotalReapyPrinciple(req.getLoanAmount());
		totalRepayDetail.setTotalRepayAmount(detail.getShouldRepayAmount());
		totalRepayDetail.setTotalRepayInterest(repayInterest);
		totalRepayDetail.setTotalTerm(1);
		
		return totalRepayDetail;
	}

}
