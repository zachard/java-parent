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

package com.zachard.java.noddd.domain.overbook;

/**
 * 航船预定处理服务类
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class VoyageBookService {
	
	/**
	 * 航船预定
	 * 
	 * @param voyage
	 * @param cargo
	 * @return
	 */
	public int makeBooking(Voyage voyage, Cargo cargo) {
		if ((voyage.getBookedSize() + cargo.getSize()) > (voyage.getCapacity() * voyage.getBookFactor())) {
			return -1;  // 判断是否超过运载量
		}
		
		cargo.setVoyageId(voyage.getVoyageId());  // 将运输货物与航运关联
		voyage.setBookedSize(voyage.getBookedSize() + cargo.getSize());  // 更新航船已经被预定的运载量
		
		return 1;
	}

}
