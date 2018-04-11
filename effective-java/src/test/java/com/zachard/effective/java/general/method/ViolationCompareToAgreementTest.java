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

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.TreeSet;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <code>Effitive Java</code>第三章: 对于所有对象都通用的方法
 * 考虑实现 {@link Comparable}接口
 *    -- 实现了{@link Comparable}接口但违反{@link #compareTo(ViolationCompareToAgreementVo)}约定测试类
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class ViolationCompareToAgreementTest {
	
	private static Logger logger = LoggerFactory.getLogger(ViolationCompareToAgreementTest.class);
	
	/**
	 * 实现{@link Comparable#compareTo(Object)}方法违反相关约定测试
	 * 
	 */
	@Test
	public void testBasic() {
		ViolationCompareToAgreementVo vo1 = new ViolationCompareToAgreementVo(6);
		ViolationCompareToAgreementVo vo2 = new ViolationCompareToAgreementVo(53);
		ViolationCompareToAgreementVo vo3 = new ViolationCompareToAgreementVo(46);
		ViolationCompareToAgreementVo vo4 = new ViolationCompareToAgreementVo(1);
		
		ViolationCompareToAgreementVo[] voArr1 = {vo1, vo2, vo3, vo4};
		ViolationCompareToAgreementVo[] voArr2 = {vo2, vo3, vo4, vo1};
		ViolationCompareToAgreementVo[] voArr3 = {vo3, vo1, vo4, vo2};
		
		Arrays.sort(voArr1);
		Arrays.sort(voArr2);
		Arrays.sort(voArr3);
		
		logger.info("第一个数组排序后的结果为: {}", Arrays.toString(voArr1));
		logger.info("第二个数组排序后的结果为: {}", Arrays.toString(voArr2));
		logger.info("第三个数组排序后的结果为: {}", Arrays.toString(voArr3));
	} 
	
	/**
	 * 当 (x.compareTo(y) == 0) == (x.equals(y)) 不满足时, 容易出现问题
	 */
	@Test
	public void testCompareToEquality() {
		BigDecimal one1 = new BigDecimal("1.0");
		BigDecimal one2 = new BigDecimal("1.00");
		
		HashSet<BigDecimal> bigDecimalHash = new HashSet<>();
		bigDecimalHash.add(one1);
		bigDecimalHash.add(one2);
		
		TreeSet<BigDecimal> bigDecimalTree = new TreeSet<>();
		bigDecimalTree.add(one1);
		bigDecimalTree.add(one2);
		
		logger.info("将值为1.0与1.00的BigDecimal对象添加到HashSet, HashSet集合中元素个数为: {}", bigDecimalHash.size());
		logger.info("将值为1.0与1.00的BigDecimal对象添加到TreeSet, TreeSet集合中元素个数为: {}", bigDecimalTree.size());
	} 

}
