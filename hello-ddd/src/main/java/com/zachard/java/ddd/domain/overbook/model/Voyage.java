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

package com.zachard.java.ddd.domain.overbook.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.zachard.java.ddd.domain.overbook.policy.OverbookingPolicy;

/**
 * 航运行程类
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class Voyage {
	
	/**
	 * 航船编号
	 */
	private String voyageId;
	
	/**
	 * 航程运载量
	 */
	private double capacity;
	
	/**
	 * 当前航程已经预定的货物
	 */
	private List<Cargo> bookedCargoList = new ArrayList<Cargo>();
	
	/**
	 * 航程运载量预定因子, 默认为1.1
	 */
	private double bookFactor = 1.1;
	
	/**
	 * 航运运载量超载策略
	 */
	private OverbookingPolicy overbookingPolicy;

	/**
	 * @return the capacity
	 */
	public double getCapacity() {
		return capacity;
	}

	/**
	 * @param capacity the capacity to set
	 */
	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}

	/**
	 * @return the bookedCargoList
	 */
	public List<Cargo> getBookedCargoList() {
		return bookedCargoList;
	}

	/**
	 * @param bookedCargoList the bookedCargoList to set
	 */
	public void setBookedCargoList(List<Cargo> bookedCargoList) {
		this.bookedCargoList = bookedCargoList;
	}
	
	/**
	 * @return the bookFactor
	 */
	public double getBookFactor() {
		return bookFactor;
	}

	/**
	 * @param bookFactor the bookFactor to set
	 */
	public void setBookFactor(double bookFactor) {
		this.bookFactor = bookFactor;
	}

	/**
	 * @return the overbookingPolicy
	 */
	public OverbookingPolicy getOverbookingPolicy() {
		return overbookingPolicy;
	}

	/**
	 * @param overbookingPolicy the overbookingPolicy to set
	 */
	public void setOverbookingPolicy(OverbookingPolicy overbookingPolicy) {
		this.overbookingPolicy = overbookingPolicy;
	}

	/**
	 * 将预定的货物添加到航程中
	 * 
	 * @param cargo
	 */
	public void addCargo(Cargo cargo) {
		bookedCargoList.add(cargo);
	}
	
	/**
	 * 计算当前航程已预定货物容量
	 * 
	 * @return
	 */
	public double bookedCargoSize() {
		
		if (CollectionUtils.isEmpty(bookedCargoList)) {
			return 0;
		}
		
		return bookedCargoList.stream()
				.mapToDouble(Cargo::getSize)
				.sum();
	}
	
	/**
	 * 航运预定货物
	 * 
	 * @param cargo
	 * @param voyage
	 * @return
	 */
	public int makeBooking(Cargo cargo, Voyage voyage) {
		
		if (!overbookingPolicy.isAllowed(cargo, voyage)) {
			return -1;  // 判断是否超过运载量
		}
		
		voyage.addCargo(cargo);   // 将获取添加到航运预定的列表中
		
		return voyage.getBookedCargoList().size();
	}

}
