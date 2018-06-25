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

package com.zachard.effective.java.general.method;

/**
 * <code>Effitive Java</code>第三章: 对于所有对象都通用的方法
 * 考虑实现 {@link Comparable}接口
 *    -- 实现了{@link Comparable}接口但违反{@link #compareTo(ViolationCompareToAgreementVo)}约定
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class ViolationCompareToAgreementVo implements Comparable<ViolationCompareToAgreementVo> {
	
	/**
	 * 类对象包含的值
	 */
	private int value;
	
	/**
	 * 默认构造器
	 */
	public ViolationCompareToAgreementVo() {}
	
	/**
	 * 带初始值的构造器
	 * 
	 * @param value   类对象初始值
	 */
	public ViolationCompareToAgreementVo(int value) {
		this.value = value;
	}

	/**
	 * 覆写{@link Comparable#compareTo(Object)}方法, 此方法违反了对应约定
	 * 
	 * @param 需要比较的对象
	 * @return   当前对象大于{@code o}时, 返回{@code 1}, 相等时, 返回{@code 0}, 否则返回{@code -1}
	 */
	@Override
	public int compareTo(ViolationCompareToAgreementVo o) {
		
		boolean flag = (value & 1) > 0;
		boolean oFlag = (o.value & 1) > 0;
		
		// 同为奇数时, 返回1, 一奇一偶, 返回0, 同为偶数, 返回-1 
		if (flag && oFlag) {
			return 1;
		} else if (oFlag || flag) {
			return 0;
		} else {
			return -1;
		}
		
	}

	/**
	 * getter 
	 * 
	 * @return
	 */
	public int getValue() {
		return value;
	}

	/**
	 * setter
	 * 
	 * @param value
	 */
	public void setValue(int value) {
		this.value = value;
	}
	
	/**
	 * 总是覆写{@link #toString()}方法
	 * 
	 * @return  对象的字符串表示
	 */
	@Override
	public String toString() {
		return value + "";
	}

}
