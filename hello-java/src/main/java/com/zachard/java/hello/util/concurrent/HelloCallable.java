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

package com.zachard.java.hello.util.concurrent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@link Callable}接口使用示例
 * 
 * <pre>
 *     (1) {@code Callable<V>}表示一个返回结果有返回结果(V)或可以抛出异常的任务. 其只定义了一个<code>call</code>
 *         方法. <code>Callable</code>接口与<code>Runnable</code>类似, 都定义了一个将被其他线程执行的实例, 
 *         只是, <code>Runnable</code>不能返回结果或是抛出检查时异常.
 *         {@link Executors}类中包含了一些将其他对象转化为{@link Callable}对象的方法
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class HelloCallable implements Callable<Integer> {
	
	private static final Logger logger = LoggerFactory.getLogger(HelloCallable.class);
	
	private File directory;
	private String keyword;
	private ExecutorService pool;
	private int count;
	
	/**
	 * {@link Callable}自定义实现类构造器
	 * 
	 * @param directory    文件路径
	 * @param keyword      关键词
	 * @param pool         线程池
	 */
	public HelloCallable(File directory, String keyword, ExecutorService pool) {
		this.directory = directory;
		this.keyword = keyword;
		this.pool = pool;
	}
	
	/**
	 * 需要处理的业务逻辑
	 * 
	 * <pre>
	 *     (1) <code>call</code>方法要么正常返回结果, 要么抛出异常
	 * </pre>
	 * 
	 * @return    任务执行结果
	 */
	@Override
	public Integer call() throws Exception {
		count = 0;
		File[] files = directory.listFiles();
		List<Future<Integer>> results = new ArrayList<>();
		
		// 查询目录下包含关键词的文件个数
		for (File file : files) {
			if (file.isDirectory()) {
				/*
				 *  如果当前路径为目录, 则创建另外一个任务, 遍历该目录下所有文件, 判断文件中是否
				 *  存在关键词, 如果该目录下有文件存在关键词, 则计数器加1
				 */
				HelloCallable callable = new HelloCallable(file, keyword, pool);
				Future<Integer> result = pool.submit(callable);
				results.add(result);
			} else {
				if (search(file)) {
					// 如果在文件中查找到关键词, 则计数器加一
					count++;
				}
			}
		}
		
		// 统计包含关键词的文件个数
		count = count + results.stream().mapToInt(value -> {
			try {
				return value.get();
			} catch (InterruptedException | ExecutionException e) {
				logger.error("获取Future对象的值异常: {}", value);
				return 0;
			}
		}).sum();
		
		return count;
	}
	
	/**
	 * 在文件中搜索关键字
	 * 
	 * @param file    被搜索的文件
	 * @return        文件是否包含关键字
	 */
	private boolean search(File file) {
		try {
			// 读取文件
			try (Scanner in = new Scanner(file)) {
				boolean found = false;
				
				// 遍历文件的每一行, 判断是否包含关键字, 包含则返回true
				while (!found && in.hasNextLine()) {
					String line = in.nextLine();
					
					if (line.contains(keyword)) {
						found = true;
					}
				}
				
				return found;
			}
		} catch (IOException e) {
			logger.error("读取文件异常: {}", e);
			return false;
		}
	}

}
