/*
 *  Copyright 2015-2017 zachard, Inc.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.zachard.java.hello.constant;

/**
 * 存放常量的接口
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public interface Constant {
    
    final String DEFAULT_CHARSET = "UTF-8";   // 默认编码字符串
    final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";  // 时间格式
    final String CODEC_FILTER_NAME = "codec";  // Mina编码过滤器名称
    final String LOGGER_FILTER_NAME = "logger";  // Mina日志过滤器名称
    final int MINA_PORT = 9123;  // Mina通讯端口号

}
