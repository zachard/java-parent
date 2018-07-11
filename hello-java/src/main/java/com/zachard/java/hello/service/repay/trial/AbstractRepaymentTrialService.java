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
import java.math.MathContext;
import java.math.RoundingMode;

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
	 * 保留两位小数精度并且四舍五入
	 */
	protected static final MathContext PRECISION_2_HAF_UP = new MathContext(2, RoundingMode.HALF_UP);

}
