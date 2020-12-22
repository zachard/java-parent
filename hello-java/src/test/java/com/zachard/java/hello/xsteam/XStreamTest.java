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
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.mapper.MapperWrapper;
import com.zachard.java.hello.model.XmlStreamBook;
import com.zachard.java.hello.model.XmlStreamNodeObject;
import com.zachard.java.hello.model.XmlStreamPerson;

/**
 * description...
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class XStreamTest {
    
    private static Logger logger = LoggerFactory.getLogger(XStreamTest.class);
    
    @Test
    public void xmlStreamTest() {
        XmlStreamBook firstBook = new XmlStreamBook("bookName", new Date());
        firstBook.setPage(364);
        XmlStreamBook secondBook = new XmlStreamBook("Java", new Date());
        secondBook.setPage(250);
        
        List<XmlStreamBook> books = new ArrayList<>(2);
        books.add(firstBook);
        books.add(secondBook);
        
        XmlStreamPerson person = new XmlStreamPerson("张三", 30, books);
        person.setSex("M");
        
        List<XmlStreamPerson> persons = new ArrayList<>();
        persons.add(person);
        
        XmlStreamNodeObject<XmlStreamPerson> nodeObject = new XmlStreamNodeObject<>();
        nodeObject.setNodeObject(persons);
        
        // 开始处理xml序列化
        XStream xStream = new XStream();
        
        String xml = xStream.toXML(nodeObject);
        logger.info("以xml格式序列化NodeObject, 结果为: ");
        logger.info("{}", xml);
        
        logger.info("======严肃的分割线=======");
        
        // 对包进行重命名
//        xStream.aliasPackage("com.richard", "com.zachard");
        xStream.alias("xml", nodeObject.getClass());
//        xStream.aliasPackage("com.zachard.java.hello.xstream.model", "com.zachard.java.hello.model");
        xml = xStream.toXML(nodeObject);
        logger.info("对包进行重命名, 重命名后的结果为: ");
        logger.info("{}", xml);
        
        logger.info("======严肃的分割线=======");
        
        // 省略多出的字段
//        xStream.omitField(com.zachard.java.hello.xstream.model.XmlStreamPerson.class, "sex");
//        xml = xStream.toXML(nodeObject);
//        logger.info("去除特有的字段, 去除之后的结果为: ");
//        logger.info("{}", xml);
        
//        XStream readStream = new XStream() {
//        	@Override
//            protected MapperWrapper wrapMapper(MapperWrapper next) {
//                return new MapperWrapper(next) {
//                    @Override
//                    public boolean shouldSerializeMember(Class definedIn, String fieldName) {
//                        if (definedIn == Object.class) {
//                            return false;
//                        }
//                        return super.shouldSerializeMember(definedIn, fieldName);
//                    }
//                };
//            }
//        };   // 在这里加过滤多余属性的Mapper, 起不到过滤多余属性的作用
        XStream readStream = new XStream();
        readStream.alias("xml", nodeObject.getClass());
        readStream.aliasPackage("com.zachard.java.hello.xstream.model", "com.zachard.java.hello.model");
//        readStream.setClassLoader(com.zachard.java.hello.xstream.model.XmlStreamPerson.class.getClassLoader());
        // 在这里做重复的变更, 去除不了多余的属性
        XmlStreamNodeObject<com.zachard.java.hello.xstream.model.XmlStreamPerson> packObject = 
        		(XmlStreamNodeObject<com.zachard.java.hello.xstream.model.XmlStreamPerson>) readStream.fromXML(xml);
        xml = readStream.toXML(packObject);
        logger.info("转换为新类型后, 先改变包的结果, 输出结果为: ");  // 为了去除多余的字段
        logger.info("{}", xml);
        
        XStream repeaterStream = new XStream() {
        	@Override
            protected MapperWrapper wrapMapper(MapperWrapper next) {
                return new MapperWrapper(next) {
                    @Override
                    public boolean shouldSerializeMember(Class definedIn, String fieldName) {
                        if (definedIn == Object.class) {
                            return false;
                        }
                        return super.shouldSerializeMember(definedIn, fieldName);
                    }
                };
            }
        };  // 必须是这种方式获取一个Stream
        
        repeaterStream.alias("xml", nodeObject.getClass());
//        repeaterStream.setClassLoader(com.zachard.java.hello.xstream.model.XmlStreamPerson.class.getClassLoader());
        XmlStreamNodeObject<com.zachard.java.hello.xstream.model.XmlStreamPerson> destObject = 
        		(XmlStreamNodeObject<com.zachard.java.hello.xstream.model.XmlStreamPerson>) repeaterStream.fromXML(xml);
        xml = repeaterStream.toXML(destObject);
        logger.info("去除多余的字段属性, 输出结果为: ");
        logger.info("{}", xml);
    }
    
    /**
     * 读和写用同一个XStream, 发现不能将重复的字段去除
     */
    @Test
    public void xmlStreamMapperWrapper() {
    	XmlStreamBook firstBook = new XmlStreamBook("bookName", new Date());
        firstBook.setPage(364);
        XmlStreamBook secondBook = new XmlStreamBook("Java", new Date());
        secondBook.setPage(250);
        
        List<XmlStreamBook> books = new ArrayList<>(2);
        books.add(firstBook);
        books.add(secondBook);
        
        XmlStreamPerson person = new XmlStreamPerson("张三", 30, books);
        person.setSex("M");
        
        List<XmlStreamPerson> persons = new ArrayList<>();
        persons.add(person);
        
        XmlStreamNodeObject<XmlStreamPerson> nodeObject = new XmlStreamNodeObject<>();
        nodeObject.setNodeObject(persons);
        
        // 开始处理xml序列化
        XStream xStream = new XStream() {
        	@Override
            protected MapperWrapper wrapMapper(MapperWrapper next) {
                return new MapperWrapper(next) {
                    @Override
                    public boolean shouldSerializeMember(Class definedIn, String fieldName) {
                        if (definedIn == Object.class) {
                            return false;
                        }
                        return super.shouldSerializeMember(definedIn, fieldName);
                    }
                };
            }
        };
        
        String xml = xStream.toXML(nodeObject);
        logger.info("以xml格式序列化NodeObject, 结果为: ");
        logger.info("{}", xml);
        
        logger.info("======严肃的分割线=======");
        
        // 对包进行重命名
//        xStream.aliasPackage("com.richard", "com.zachard");
        xStream.alias("xml", nodeObject.getClass());
        xStream.aliasPackage("com.zachard.java.hello.xstream.model", "com.zachard.java.hello.model");
        xml = xStream.toXML(nodeObject);
        logger.info("对包进行重命名, 重命名后的结果为: ");
        logger.info("{}", xml);
        
        logger.info("======严肃的分割线=======");
        
        // 省略多出的字段
//        xStream.omitField(com.zachard.java.hello.xstream.model.XmlStreamPerson.class, "sex");
//        xml = xStream.toXML(nodeObject);
//        logger.info("去除特有的字段, 去除之后的结果为: ");
//        logger.info("{}", xml);
        
//        XStream readStream = new XStream() {
//        	@Override
//            protected MapperWrapper wrapMapper(MapperWrapper next) {
//                return new MapperWrapper(next) {
//                    @Override
//                    public boolean shouldSerializeMember(Class definedIn, String fieldName) {
//                        if (definedIn == Object.class) {
//                            return false;
//                        }
//                        return super.shouldSerializeMember(definedIn, fieldName);
//                    }
//                };
//            }
//        };  // 必须是这种方式获取一个Stream
//        readStream.alias("xml", nodeObject.getClass());
        xStream.setClassLoader(com.zachard.java.hello.xstream.model.XmlStreamPerson.class.getClassLoader());
        XmlStreamNodeObject<com.zachard.java.hello.xstream.model.XmlStreamPerson> destObject = 
        		(XmlStreamNodeObject<com.zachard.java.hello.xstream.model.XmlStreamPerson>) xStream.fromXML(xml);
        xml = xStream.toXML(destObject);
        logger.info("转换为新类型后, 输出结果为: ");  // 为了去除多余的字段
        logger.info("{}", xml);
    }

}
