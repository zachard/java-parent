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

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * <code>Effitive Java</code>第二章: 创建和销毁对象
 * 避免创建不必要的对象 -- 重用已知不会修改的对象比较适合的实现
 * 
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class ResuseVariableObjectSuit {
    
    /**
     * 20世纪开始时间
     */
    private static final Date century20Start;
    
    /**
     * 20世纪结束时间
     */
    private static final Date century20End;
    
 // 静态代码块, 用于初始化重用的已知不会被修改的对象
    static {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        calendar.set(1900, Calendar.JANUARY, 1, 0, 0, 0);
        century20Start = calendar.getTime();
        calendar.set(1999, Calendar.DECEMBER, 31, 23, 59, 59);
        century20End = calendar.getTime();
    }
    
    // 覆盖默认的构造器, 避免对象被实例化
    private ResuseVariableObjectSuit() {}
    
    /**
     * 判断某个时间是否处于20世纪
     * 
     * @param date   需要判断是否处于20世纪的时间
     * @return       {@code true}处于20世纪, {@code false}不处于20世纪
     */
    public static boolean isCentury20(Date date) {
        return date.compareTo(century20Start) >= 0 && 
                date.compareTo(century20End) < 0;
    }

}
