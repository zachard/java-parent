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

package com.zachard.java.hello.xsteam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.xstream.XStream;
import com.zachard.java.hello.model.XmlStreamNodeObject;
import com.zachard.java.hello.xstream.model.XmlNodeValidityData;
import com.zachard.java.hello.xstream.model.XmlNodeWarnCodeEnum;

/**
 * description...
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class DiffEnumXStreamTest {
	
	private static Logger logger = LoggerFactory.getLogger(DiffEnumXStreamTest.class);
	
	private String getSourceEnumStr() {
		com.zachard.java.hello.model.XmlNodeValidityData validityData = 
				new com.zachard.java.hello.model.XmlNodeValidityData();
		Map<String, com.zachard.java.hello.model.XmlNodeWarnCodeEnum> enumData = 
				new HashMap<String, com.zachard.java.hello.model.XmlNodeWarnCodeEnum>();
		enumData.put("001", com.zachard.java.hello.model.XmlNodeWarnCodeEnum.WRAN_CODE_001);
		enumData.put("002", com.zachard.java.hello.model.XmlNodeWarnCodeEnum.WARN_CODE_002);
		Map<String, Map<String, com.zachard.java.hello.model.XmlNodeWarnCodeEnum>> dataMap = 
				new HashMap<String, Map<String,com.zachard.java.hello.model.XmlNodeWarnCodeEnum>>();
		dataMap.put("validityData", enumData);
		validityData.setNodeValidityData(dataMap);
		
		List<com.zachard.java.hello.model.XmlNodeValidityData> nodeList = 
				new ArrayList<com.zachard.java.hello.model.XmlNodeValidityData>();
		nodeList.add(validityData);
		
		XmlStreamNodeObject<com.zachard.java.hello.model.XmlNodeValidityData> nodeObject = 
				new XmlStreamNodeObject<com.zachard.java.hello.model.XmlNodeValidityData>();
		nodeObject.setNodeObject(nodeList);
		
		XStream xStream = new XStream();
		xStream.alias("xml", nodeObject.getClass());
		String xml = xStream.toXML(nodeObject);
		
		logger.info("打印原始数据结果, 原始数据为: ");
		logger.info("{}", xml);
		
		return xml;
	}
	
	private String getDesEnumStr() {
		XmlNodeValidityData nodeValidityData = 
				new XmlNodeValidityData();
		Map<String, XmlNodeWarnCodeEnum> enumData = 
				new HashMap<>();
		enumData.put("001", XmlNodeWarnCodeEnum.WRAN_CODE_001);
		enumData.put("002", XmlNodeWarnCodeEnum.WARN_CODE_002);
		Map<String, Map<String, XmlNodeWarnCodeEnum>> dataMap = 
				new HashMap<>();
		dataMap.put("validityData", enumData);
		nodeValidityData.setNodeValidityData(dataMap);
		
		List<XmlNodeValidityData> nodeList = new ArrayList<>();
		nodeList.add(nodeValidityData);
		
		XmlStreamNodeObject<XmlNodeValidityData> nodeObject = 
				new XmlStreamNodeObject<>();
		nodeObject.setNodeObject(nodeList);
		
		XStream xStream = new XStream();
		xStream.alias("xml", nodeObject.getClass());
		String xml = xStream.toXML(nodeObject);
		
		logger.info("打印新的数据结构, 数据结构为: ");
		logger.info("{}", xml);
		
		return xml;
	}
	
	@Test
	public void printDataStructure() {
		getSourceEnumStr();
		getDesEnumStr();
	}
	
	@Test
	public void diffEnumTranTest() {
		String sourceStr = getSourceEnumStr();
		XmlStreamNodeObject<XmlNodeValidityData> nodeObject = 
				new XmlStreamNodeObject<XmlNodeValidityData>();
		
		XStream xStream = new XStream();
		xStream.alias("xml", nodeObject.getClass());
		xStream.aliasPackage("com.zachard.java.hello.xstream.model", "com.zachard.java.hello.model");
		nodeObject = (XmlStreamNodeObject<XmlNodeValidityData>) xStream.fromXML(sourceStr);
		String destXml = xStream.toXML(nodeObject);
		
		logger.info("根据源字符串获取到了相应的结果对象, 得到的对象转换为xml结果如下: ");
		logger.info("{}", destXml);
		
		XStream readStream = new XStream();
		readStream.alias("xml", nodeObject.getClass());
		nodeObject = (XmlStreamNodeObject<XmlNodeValidityData>) readStream.fromXML(destXml);
		
		if (!CollectionUtils.isEmpty(nodeObject.getNodeObject())) {
			List<XmlNodeValidityData> dataList = 
					(List<XmlNodeValidityData>) nodeObject.getNodeObject();
			logger.info("数据节点的数据个数为: {}", dataList.size());
			
			XmlNodeValidityData validatyData = dataList.get(0);
			Map<String, Map<String, XmlNodeWarnCodeEnum>> dataMap = validatyData.getNodeValidityData();
			
			if (!MapUtils.isEmpty(dataMap)) {
				dataMap.keySet().forEach(key -> {
					logger.info("解析第一层数据结构, 数据节点为: {}", key);
					Map<String, XmlNodeWarnCodeEnum> enumMap = dataMap.get(key);
					
					if (!MapUtils.isEmpty(enumMap)) {
						enumMap.keySet().forEach(enumKey -> {
							logger.info("解析第二层数据结构, 数据节点为: {}", enumKey);
							XmlNodeWarnCodeEnum warnCodeEnum = enumMap.get(enumKey);
							
							if (Optional.of(warnCodeEnum).isPresent()) {
								logger.info("解析第二层数据结构, 数据节点相应数据为: {}", warnCodeEnum.getLab());
							}
						});
					}
				});
			}
		}
	}

}
