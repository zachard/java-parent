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

import com.zachard.java.hello.bean.repay.trial.req.RepaymentTrialReq;
import com.zachard.java.hello.bean.repay.trial.res.TotalRepayDetail;

/**
 * 还款计划试算接口
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public interface RepaymentTrialService {
	
	/**
	 * 计算还款计划
	 * 
	 * @param req    贷款相关信息
	 * @return       还款计划
	 */
	TotalRepayDetail calculateRepayPlan(RepaymentTrialReq req);

}
