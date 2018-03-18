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

package com.zachard.effective.java.general.method;

/**
 * <code>Effitive Java</code>第三章: 对于所有对象都通用的方法
 * 覆写{@link #equals(Object)}时总要覆写{@link #hashCode()} 
 *    -- 没有覆写{@link #hashCode()}时类无法与所有基于散列的集合一起正常工作
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public final class NotOverrideHashCodeVo {
    private final short areaCode;
    private final short prefix;
    private final short lineNumber;
    
    /**
     * 构造器
     * 
     * @param areaCode   地区编码
     * @param prefix     前缀
     * @param lineNumber 手机号码
     */
    public NotOverrideHashCodeVo(int areaCode, int prefix, int lineNumber) {
        rangeCheck(areaCode, 999, "area code");
        rangeCheck(prefix, 9999, "prefix");
        rangeCheck(lineNumber, 9999, "line number");
        this.areaCode = (short) areaCode;
        this.prefix = (short) prefix;
        this.lineNumber = (short) lineNumber;
    }
    
    /**
     * 覆写{@link #equals(Object)}方法
     * 
     * @param o   需要比较的对象
     * @return    逻辑相等返回true, 否则返回false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        
        if (!(o instanceof NotOverrideHashCodeVo)) {
            return false;
        }
        
        NotOverrideHashCodeVo vo = (NotOverrideHashCodeVo) o;
        
        return vo.lineNumber == lineNumber 
                && vo.prefix == prefix && vo.areaCode == areaCode;
    }
    
//    /**
//     * 合法但是没有任何价值的{@link #hashCode()}覆写方式
//     * 
//     * @param  hashCode整数值
//     */
//    @Override
//    public int hashCode() {
//        return 42;
//    }
    
    /**
     * 合法并且有价值的覆写{@link #hashCode()}方法
     * 
     * @return  散列函数计算的散列值
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + areaCode;
        result = 31 * result + prefix;
        result = 31 * result + lineNumber;
        
        return result;
    }
    
    /**
     * 检查参数是否合法
     * 
     * @param arg   参数
     * @param max   参数最大值
     * @param name  参数名称
     */
    private static void rangeCheck(int arg, int max, String name) {
        if (arg < 0 || arg > max) {
            throw new IllegalArgumentException(name + ": " + arg);
        }
    }
}
