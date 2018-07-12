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

package com.zachard.java.hello.enums;

/**
 * 还款计划还款方式枚举
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public enum RepayMethodEnum {
	
	AVERAGE_CAPITAL_PLUS_INTEREST("01", "等额本息"),
    EQUAL_PRINCIPAL("02", "等额本金"),
    MONTHLY_PAYMENT_INTEREST_DUE("03", "按月付息到期一次性还本"),
    REPAYMENT_INTEREST_MATURITY("04", "到期一次性还本付息"),
    FIXED_DAY_EQUAL_PRINCIPAL_INTEREST("05", "固定还款日等额本息"),
	;
	
	private String code;
	private String desc;
	
	RepayMethodEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}
	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
