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
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <code>Callable</code>自定义实现类测试
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class HelloCallableTest {
	
	private static final Logger logger = LoggerFactory.getLogger(HelloCallableTest.class);
	
	/**
	 * 测试通过线程池执行异步{@code Callable}任务
	 * 
	 * <pre>
	 *     (1) 步骤如下: 
	 *         (1.1) 实现{@link Callable}接口, 并在其<code>call</code>方法中实现业务逻辑
	 *         (1.2) 通过{@link Executors}创建适合的线程池{@link ExecutorService}对象(pool)
	 *         (1.3) 创建{@code Callable}实现类的对象, 并通过<code>pool</code>中的<code>submit</code>
	 *               方法将其加入到线程中执行
	 *         (1.4) 通过{@link Future#get()}获取任务执行的结果
	 *         (1.5) 通过{@link ExecutorService#shutdown()}方法关闭线程池并释放资源
	 * </pre>
	 * @throws InterruptedException
	 */
	@Test
	public void callTest() throws InterruptedException {
		String directory = "/data/credit_micro/logs/srv/credit_micro_base";
		String keyword = "DEBUG";
		
		ExecutorService pool = ExecutorServiceFactory.createCachedThreadPool();
		HelloCallable callable = new HelloCallable(new File(directory), keyword, pool);
		Future<Integer> result = pool.submit(callable);
		
		try {
			logger.info("{} 目录下总共有: {} 个文件包含关键词: {}", directory, result.get(), keyword);
		} catch (InterruptedException | ExecutionException e) {
			logger.error("获取任务执行结果失败: {}", e);
		}
		
		pool.shutdown();
		
		int largestPoolSize = ((ThreadPoolExecutor) pool).getLargestPoolSize();
		logger.info("线程池拥有过最多线程数量为: {}", largestPoolSize);
	}

}
