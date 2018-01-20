/*
 *  Copyright 2015-2018 zachard, Inc.
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

package com.zachard.effective.java.object.life;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <code>Effitive Java</code>第二章: 创建和销毁对象
 * 采用静态工程方法创建对象相比于构造器的优势 -- 静态工厂方法可以返回原类型的任何子类型的对象
 * 
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class StaticMethodReturnDiffTest {
    
    private static final Logger logger = LoggerFactory.getLogger(StaticMethodReturnDiffTest.class);
    
    /**
     * 通过静态工厂返回子类类型实现的示例
     * (1) 可以根据参数的不同,得到类型的不同实现
     * (2) 可以对客户端隐藏类型的实现
     */
    @Test
    public void diffInstanceImplTest() {
        /*
         * 根据传入的参数不同,得到类型的不同实现
         */
        StaticMethodReturnDiff simple = StaticMethodReturnDiffFactory.diffInstanceImpl(false);
        StaticMethodReturnDiff complex = StaticMethodReturnDiffFactory.diffInstanceImpl(true);
        logger.info("简单实现的自我描述为: {}", simple.printSubTypeInfo());
        logger.info("复杂实现的自我描述为: {}", complex.printSubTypeInfo());
        
        /*
         * 当希望通过子类构造器直接创建一个类型的对象时, 由于作用域的限制, 无法创建对应的对象
         * 从而达到隐藏子类类型实现的效果
         */
//        StaticMethodReturnDiff anotherSimple = new SimpleStaticMethodReturnDiff();
//        StaticMethodReturnDiff anotherComplex = new ComplexStaticMethodReturnDiff();
    }

}
