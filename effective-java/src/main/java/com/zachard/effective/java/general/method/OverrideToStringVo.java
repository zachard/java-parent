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
 * 始终要覆写{@link #toString()}方法 
 *    -- 覆写{@link #toString()}方法类
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class OverrideToStringVo {
    
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
    public OverrideToStringVo(int areaCode, int prefix, int lineNumber) {
        rangeCheck(areaCode, 999, "area code");
        rangeCheck(prefix, 9999, "prefix");
        rangeCheck(lineNumber, 9999, "line number");
        this.areaCode = (short) areaCode;
        this.prefix = (short) prefix;
        this.lineNumber = (short) lineNumber;
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
    
    /**
     * <p>返回电话号码的字符串表示
     * 这个字符串由<em>14</em>个字符表示, 格式为"(XXX) YYY-ZZZZ", 
     * 其中 XXX 代表{@link #areaCode}, YYY 代表{@link #prefix},
     * 而 ZZZZ 代表{@link #lineNumber}</p>
     * 
     * <p>如果号码三个部分中的任意一个位数不足, 都会在它前面补上<em>0</em>
     * 例如: 如果{@link #lineNumber}的值为<em>123</em>时, 字符串中
     * 表示{@link #lineNumber}的部分将会是 "0123"</p>
     * 
     * <p>需要注意的是, 在{@link #areaCode}和{@link #prefix}之间存在
     * 一个空格</p>
     * 
     * @return  对象的字符串表示
     */
    @Override
    public String toString() {
        return String.format("(%03d) %03d-%04d", 
                areaCode, prefix, lineNumber);
    }

}
