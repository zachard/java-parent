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

package com.zachard.java.hello.factory;

import java.util.Objects;

import com.zachard.java.hello.enums.RepayMethodEnum;
import com.zachard.java.hello.service.repay.trial.RepaymentTrialService;
import com.zachard.java.hello.service.repay.trial.impl.EqualPrincipalInterestRepayServiceImpl;
import com.zachard.java.hello.service.repay.trial.impl.EqualPrincipalRepayServiceImpl;
import com.zachard.java.hello.service.repay.trial.impl.FixedDayEqualPrincipalInterestRepayServiceImpl;
import com.zachard.java.hello.service.repay.trial.impl.MonthlyPaymentInterestDueRepayServiceImpl;
import com.zachard.java.hello.service.repay.trial.impl.RepaymentInterestMaturityRepayServiceImpl;

/**
 * 还款试算Service工厂类
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class RepaymentTrialServiceFactory {
	
	/**
	 * 还款计划Service获取工厂类(这个方法并没有达到理想中的效果)
	 * 
	 * @param repayMethod   还款方式
	 * @return              还款计划Service
	 */
	public static RepaymentTrialService getRepaymentTrialService(String repayMethod) {
		
		if (Objects.equals(repayMethod, RepayMethodEnum.AVERAGE_CAPITAL_PLUS_INTEREST.getCode())) {
			// 等额本息
			return EqualPrincipalInterestRepayServiceImpl.getInstance();
		}
		
		if (Objects.equals(repayMethod, RepayMethodEnum.EQUAL_PRINCIPAL.getCode())) {
			// 等额本金
			return EqualPrincipalRepayServiceImpl.getInstance();
		}
		
		if (Objects.equals(repayMethod, RepayMethodEnum.MONTHLY_PAYMENT_INTEREST_DUE.getCode())) {
			// 按月付息到期一次性还本
			return MonthlyPaymentInterestDueRepayServiceImpl.getInstance();
		}
		
		if (Objects.equals(repayMethod, RepayMethodEnum.REPAYMENT_INTEREST_MATURITY.getCode())) {
			// 到期一次性还本付息
			return RepaymentInterestMaturityRepayServiceImpl.getInstance();
		}
		
		if (Objects.equals(repayMethod, RepayMethodEnum.FIXED_DAY_EQUAL_PRINCIPAL_INTEREST.getCode())) {
			// 固定还款日等额本息
			return FixedDayEqualPrincipalInterestRepayServiceImpl.getInstance();
		}
		
		return null;
	}

}
