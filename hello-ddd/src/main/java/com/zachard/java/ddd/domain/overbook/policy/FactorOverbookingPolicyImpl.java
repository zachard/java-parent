/*
 *  Copyright 2015-2021 zachard, Inc.
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

package com.zachard.java.ddd.domain.overbook.policy;

import com.zachard.java.ddd.domain.Cargo;
import com.zachard.java.ddd.domain.Voyage;

/**
 * 航运运载量超载策略具体实现
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class FactorOverbookingPolicyImpl implements OverbookingPolicy {
	
	/**
	 * 判断是否超过航运运载量
	 * 
	 */
	@Override
	public boolean isAllowed(Cargo cargo, Voyage voyage) {
		return (cargo.getSize() + voyage.bookedCargoSize()) <= 
				(voyage.getCapacity() * voyage.getBookFactor());
	}

}
