/*
 *  Copyright 2015-2020 zachard, Inc.
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

package com.zachard.java.hello.xstream.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * description...
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class XmlNodeReviewData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -660877924220162659L;
	
	private Map<String, Map<String, String>> reviewsMap = 
			new HashMap<String, Map<String,String>>();
	private String memo;
	private String remark;
	
	/**
	 * @return the reviewsMap
	 */
	public Map<String, Map<String, String>> getReviewsMap() {
		return reviewsMap;
	}
	/**
	 * @return the memo
	 */
	public String getMemo() {
		return memo;
	}
	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param reviewsMap the reviewsMap to set
	 */
	public void setReviewsMap(Map<String, Map<String, String>> reviewsMap) {
		this.reviewsMap = reviewsMap;
	}
	/**
	 * @param memo the memo to set
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
