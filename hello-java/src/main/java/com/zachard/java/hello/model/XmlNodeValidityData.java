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
import java.util.Map;

/**
 * description...
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class XmlNodeValidityData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2527193704705793443L;
	
	private Map<String, Map<String, XmlNodeWarnCodeEnum>> nodeValidityData = 
			new HashMap<>();

	/**
	 * @return the nodeValidityData
	 */
	public Map<String, Map<String, XmlNodeWarnCodeEnum>> getNodeValidityData() {
		return nodeValidityData;
	}

	/**
	 * @param nodeValidityData the nodeValidityData to set
	 */
	public void setNodeValidityData(Map<String, Map<String, XmlNodeWarnCodeEnum>> nodeValidityData) {
		this.nodeValidityData = nodeValidityData;
	}

}
