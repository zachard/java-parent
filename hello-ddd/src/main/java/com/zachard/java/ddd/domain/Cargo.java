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

package com.zachard.java.ddd.domain;

/**
 * 采用ddd方式的货物类
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class Cargo {
	
	/**
	 * 货物大小
	 */
	private double size;

	/**
	 * @return 货物大小
	 */
	public double getSize() {
		return size;
	}

	/**
	 * @param 设置货物的大小
	 */
	public void setSize(double size) {
		this.size = size;
	}

}
