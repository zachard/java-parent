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

package com.zachard.java.noddd.domain;

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
	 * 航程运载量预定因子, 默认为1.1
	 */
	private double bookFactor = 1.1;
	
	/**
	 * 已预定的运载量
	 */
	private double bookedSize;

	/**
	 * @return the voyageId
	 */
	public String getVoyageId() {
		return voyageId;
	}

	/**
	 * @return the capacity
	 */
	public double getCapacity() {
		return capacity;
	}

	/**
	 * @return the bookFactor
	 */
	public double getBookFactor() {
		return bookFactor;
	}

	/**
	 * @return the bookedSize
	 */
	public double getBookedSize() {
		return bookedSize;
	}

	/**
	 * @param voyageId the voyageId to set
	 */
	public void setVoyageId(String voyageId) {
		this.voyageId = voyageId;
	}

	/**
	 * @param capacity the capacity to set
	 */
	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}

	/**
	 * @param bookFactor the bookFactor to set
	 */
	public void setBookFactor(double bookFactor) {
		this.bookFactor = bookFactor;
	}

	/**
	 * @param bookedSize the bookedSize to set
	 */
	public void setBookedSize(double bookedSize) {
		this.bookedSize = bookedSize;
	}
	
}
