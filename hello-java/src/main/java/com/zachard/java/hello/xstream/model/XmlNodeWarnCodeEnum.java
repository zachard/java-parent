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

/**
 * description...
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public enum XmlNodeWarnCodeEnum {
	
	WRAN_CODE_001("warn_code_001"),
	WARN_CODE_002("warn_code_002"),
	;
	
	private String code;
	private String lab;
	
	private XmlNodeWarnCodeEnum(String lab) {
		this.code = name();
		this.lab = lab;
	}
	
	/**
	 * @return the lab
	 */
	public String getLab() {
		return lab;
	}

}
