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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.xstream.XStream;
import com.zachard.java.hello.model.XmlNodeReviewData;
import com.zachard.java.hello.model.XmlStreamNodeObject;

/**
 * description...
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class DiffStructureTranXStreamTest {
	
	private static Logger logger = 
			LoggerFactory.getLogger(DiffStructureTranXStreamTest.class);
	
	/**
	 * 返回历史数据
	 * 
	 * @return
	 */
	private String getOldDataStr() {
		XmlNodeReviewData oldNodeObject = new XmlNodeReviewData();
		// 设置reviewsMap属性
		Map<String, List<String>> reviewsInnerMap = new HashMap<String, List<String>>();
		List<String> reviewsList1 = Arrays.asList("Amy", "Bob", "Carl");
		reviewsInnerMap.put("names", reviewsList1);
		List<String> reviewsList2 = Arrays.asList("Beijing", "Shanghai", "Shenzhen");
		reviewsInnerMap.put("cities", reviewsList2);
		Map<String, Map<String, List<String>>> reviewsMap = new HashMap<String, Map<String,List<String>>>();
		reviewsMap.put("review", reviewsInnerMap);
		oldNodeObject.setReviewsMap(reviewsMap);
		
		// 设置reviewsValueMap属性
		Map<String, String> thirdMap1 = new HashMap<String, String>();
		thirdMap1.put("NanChang", "1000");
		thirdMap1.put("Jian", "9000");
		Map<String, String> thirdMap2 = new HashMap<String, String>();
		thirdMap2.put("XiAn", "10000");
		thirdMap2.put("XianYang", "5000");
		Map<String, Map<String, String>> provinceMap = new HashMap<String, Map<String,String>>();
		provinceMap.put("JiangXi", thirdMap1);
		provinceMap.put("ShanXi", thirdMap2);
		Map<String, Map<String, Map<String, String>>> reviewsValueMap = 
				new HashMap<String, Map<String,Map<String,String>>>();
		reviewsValueMap.put("reviewsValue", provinceMap);
		oldNodeObject.setReviewsValueMap(reviewsValueMap);
		
		// 设置reviewsDiffValueMap属性
		oldNodeObject.setReviewsDiffValueMap(reviewsValueMap);
		oldNodeObject.setMemo("我是memo");
		oldNodeObject.setRemark("我是remark");
		
		XmlStreamNodeObject<XmlNodeReviewData> nodeObject = 
				new XmlStreamNodeObject<XmlNodeReviewData>();
		List<XmlNodeReviewData> oldList = new ArrayList<XmlNodeReviewData>();
		oldList.add(oldNodeObject);
		nodeObject.setNodeObject(oldList);
		
		XStream xStream = new XStream();
		xStream.alias("xml", nodeObject.getClass());
		
		return xStream.toXML(nodeObject);
	}
	
	@Test
	public void tranDataTest() {
		String oldContent = getOldDataStr();
		logger.info("转换数据之前, 获取历史数据, 历史数据为: ");
		logger.info("{}", oldContent);
	}

}
