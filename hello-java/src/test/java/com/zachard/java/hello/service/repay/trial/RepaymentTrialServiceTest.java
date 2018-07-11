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
import java.util.Date;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.zachard.java.hello.bean.repay.trial.req.RepaymentTrialReq;
import com.zachard.java.hello.bean.repay.trial.res.TotalRepayDetail;
import com.zachard.java.hello.service.repay.trial.impl.EqualPrincipalInterestRepayServiceImpl;
import com.zachard.java.hello.service.repay.trial.impl.EqualPrincipalRepayServiceImpl;

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
		req.setLoanAmount(new BigDecimal(100000));
		req.setLoanDate(new Date());
		req.setLoanInterest(new BigDecimal(4.35));
		req.setLoanTerm(12);
		req.setRepayMethod("02");
		
		RepaymentTrialService service = new EqualPrincipalRepayServiceImpl();
		TotalRepayDetail totalDetail = service.calculateRepayPlan(req);
		System.err.println(JSON.toJSONString(totalDetail));
	} 

}
