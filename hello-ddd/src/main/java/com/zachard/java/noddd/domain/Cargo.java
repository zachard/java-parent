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
 * 货物类
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class Cargo {
	
	/**
	 * 货物编号
	 */
	private String cargoId;
	
	/**
	 * 货物大小
	 */
	private double size;
	
	/**
	 * 运输货物的航船编号
	 */
	private String voyageId;

	/**
	 * @return the cargoId
	 */
	public String getCargoId() {
		return cargoId;
	}

	/**
	 * @return the size
	 */
	public double getSize() {
		return size;
	}

	/**
	 * @param cargoId the cargoId to set
	 */
	public void setCargoId(String cargoId) {
		this.cargoId = cargoId;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(double size) {
		this.size = size;
	}

	/**
	 * @return the voyageId
	 */
	public String getVoyageId() {
		return voyageId;
	}

	/**
	 * @param voyageId the voyageId to set
	 */
	public void setVoyageId(String voyageId) {
		this.voyageId = voyageId;
	}

}
