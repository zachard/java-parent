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

package com.zachard.java.hello.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
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
	private static final long serialVersionUID = -3277173827343311769L;
	
	private Map<String, Map<String, List<String>>> reviewsMap = 
			new HashMap<String, Map<String,List<String>>>();
	private Map<String, Map<String, Map<String, String>>> reviewsValueMap = 
			new HashMap<String, Map<String,Map<String,String>>>();
	private Map<String, Map<String, Map<String, String>>> reviewsDiffValueMap = 
			new HashMap<String, Map<String,Map<String,String>>>();
	private String memo;
	private String remark;
	
	/**
	 * @return the reviewsMap
	 */
	public Map<String, Map<String, List<String>>> getReviewsMap() {
		return reviewsMap;
	}
	/**
	 * @return the reviewsValueMap
	 */
	public Map<String, Map<String, Map<String, String>>> getReviewsValueMap() {
		return reviewsValueMap;
	}
	/**
	 * @return the reviewsDiffValueMap
	 */
	public Map<String, Map<String, Map<String, String>>> getReviewsDiffValueMap() {
		return reviewsDiffValueMap;
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
	public void setReviewsMap(Map<String, Map<String, List<String>>> reviewsMap) {
		this.reviewsMap = reviewsMap;
	}
	/**
	 * @param reviewsValueMap the reviewsValueMap to set
	 */
	public void setReviewsValueMap(Map<String, Map<String, Map<String, String>>> reviewsValueMap) {
		this.reviewsValueMap = reviewsValueMap;
	}
	/**
	 * @param reviewsDiffValueMap the reviewsDiffValueMap to set
	 */
	public void setReviewsDiffValueMap(Map<String, Map<String, Map<String, String>>> reviewsDiffValueMap) {
		this.reviewsDiffValueMap = reviewsDiffValueMap;
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
