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
        
        XmlStreamNodeObject nodeObject = new XmlStreamNodeObject();
        nodeObject.setNodeObject(persons);
        
        // 开始处理xml序列化
        XStream xStream = new XStream();
        String xml = xStream.toXML(nodeObject);
        logger.info("以xml格式序列化NodeObject, 结果为: ");
        logger.info("{}", xml);
        
        logger.info("======严肃的分割线=======");
        
        // 对包进行重命名
//        xStream.aliasPackage("com.richard", "com.zachard");
        xStream.aliasPackage("com.zachard.java.hello.xstream.model", "com.zachard.java.hello.model");
        xml = xStream.toXML(nodeObject);
        logger.info("对包进行重命名, 重命名后的结果为: ");
        logger.info("{}", xml);
        
        logger.info("======严肃的分割线=======");
        
        // 省略多出的字段
        xStream.omitField(com.zachard.java.hello.model.XmlStreamPerson.class, "sex");
        xml = xStream.toXML(nodeObject);
        logger.info("去除特有的字段, 去除之后的结果为: ");
        logger.info("{}", xml);
    }

}
