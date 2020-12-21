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

import java.util.List;

/**
 * description...
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class XmlStreamPerson {
    
    private String name;
    private int age;
    private List<XmlStreamBook> books;
    
    private float height;   // 特有的属性
    
    public XmlStreamPerson(String name, int age, List<XmlStreamBook> books) {
        super();
        this.name = name;
        this.age = age;
        this.books = books;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }
    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }
    /**
     * @return the books
     */
    public List<XmlStreamBook> getBooks() {
        return books;
    }
    /**
     * @param books the books to set
     */
    public void setBooks(List<XmlStreamBook> books) {
        this.books = books;
    }
    /**
     * @return the height
     */
    public float getHeight() {
        return height;
    }
    /**
     * @param height the height to set
     */
    public void setHeight(float height) {
        this.height = height;
    }

}
