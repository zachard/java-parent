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
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.zachard.java.hello.bean.repay.trial.req.RepaymentTrialReq;
import com.zachard.java.hello.bean.repay.trial.res.TotalRepayDetail;
import com.zachard.java.hello.service.repay.trial.impl.EqualPrincipalInterestRepayServiceImpl;
import com.zachard.java.hello.service.repay.trial.impl.EqualPrincipalRepayServiceImpl;
import com.zachard.java.hello.service.repay.trial.impl.FixedDayEqualPrincipalInterestRepayServiceImpl;
import com.zachard.java.hello.service.repay.trial.impl.MonthlyPaymentInterestDueRepayServiceImpl;
import com.zachard.java.hello.service.repay.trial.impl.RepaymentInterestMaturityRepayServiceImpl;

/**
 * 等额本息还款计划试算测试
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class RepaymentTrialServiceTest {
	
	/**
	 * 等额本息还款计算试算测试
	 */
	@Test
	public void equalPrincipalInterestTest() {
		RepaymentTrialReq req = new RepaymentTrialReq();
		req.setLoanAmount(new BigDecimal(100000));
		req.setLoanDate(new Date());
		req.setLoanInterest(new BigDecimal(4.35));
		req.setLoanTerm(12);
		req.setRepayMethod("01");
		
		RepaymentTrialService service = new EqualPrincipalInterestRepayServiceImpl();
		TotalRepayDetail totalDetail = service.calculateRepayPlan(req);
		System.err.println(JSON.toJSONString(totalDetail));
	} 
	
	/**
	 * 等额本金还款方式还款计划试算
	 */
	@Test
	public void equalPrincipalTest() {
		RepaymentTrialReq req = new RepaymentTrialReq();
		req.setLoanAmount(new BigDecimal(0));
		req.setLoanDate(new Date());
		req.setLoanInterest(new BigDecimal(4.35));
		req.setLoanTerm(12);
		req.setRepayMethod("02");
		
		RepaymentTrialService service = new EqualPrincipalRepayServiceImpl();
		TotalRepayDetail totalDetail = service.calculateRepayPlan(req);
		System.err.println(JSON.toJSONString(totalDetail));
	} 
	
	/**
	 * 按期付息到期一次性还清本金
	 */
	@Test
	public void monthlyPaymentInterestDueTest() {
		RepaymentTrialReq req = new RepaymentTrialReq();
		req.setLoanAmount(new BigDecimal(100000));
		req.setLoanDate(new Date());
		req.setLoanInterest(new BigDecimal(4.35));
		req.setLoanTerm(12);
		req.setRepayMethod("03");
		
		RepaymentTrialService service = new MonthlyPaymentInterestDueRepayServiceImpl();
		TotalRepayDetail totalDetail = service.calculateRepayPlan(req);
		System.err.println(JSON.toJSONString(totalDetail));
	} 
	
	/**
	 * 到期一次性还本付息还款计划测试
	 */
	@Test
	public void repaymentInterestMaturityTest() {
		RepaymentTrialReq req = new RepaymentTrialReq();
		req.setLoanAmount(new BigDecimal(100000));
		req.setLoanDate(new Date());
		req.setLoanInterest(new BigDecimal(4.35));
		req.setLoanTerm(6);
		req.setRepayMethod("04");
		
		RepaymentTrialService service = new RepaymentInterestMaturityRepayServiceImpl();
		TotalRepayDetail totalRepayDetail = service.calculateRepayPlan(req);
		System.err.println(JSON.toJSONString(totalRepayDetail));
	} 
	
	/**
	 * 固定还款日等额本息还款计划测试
	 */
	@Test
	public void fixedDayEqualPrincipalInterestTest() {
		RepaymentTrialReq req = new RepaymentTrialReq();
		req.setLoanAmount(new BigDecimal(5000));
		Calendar calendar = Calendar.getInstance();
		calendar.set(2017, Calendar.DECEMBER, 28);
		req.setLoanDate(calendar.getTime());
		System.err.println("贷款时间为: " + calendar.getTime());
		req.setLoanInterest(new BigDecimal(144));
		req.setLoanTerm(24);
		req.setRepayMethod("05");
		
		RepaymentTrialService service = new FixedDayEqualPrincipalInterestRepayServiceImpl();
		TotalRepayDetail totalRepayDetail = service.calculateRepayPlan(req);
		System.err.println(JSON.toJSONString(totalRepayDetail));
	} 

}
