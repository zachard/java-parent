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

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@link ThreadFactory}的自定义实现类
 * 
 * <pre>
 *     (1) {@link ThreadFactory}用于在需要的时候创建新的线程, 通过线程工厂类可以避免显示的调用{@code new Thread}
 *         创建线程, 为程序指定特殊的线程实现类, 线程优先级等
 *     (2) 可以通过{@link Executors#defaultThreadFactory()}创建一个线程工厂的线程实现
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class CustomThreadFactoryImpl implements ThreadFactory {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomThreadFactoryImpl.class);
	
	/**
	 * 线程名称前缀
	 */
	private static final String THREAD_NAME_PREFIX = "custom-thread-";
	
	/**
	 * 线程创建数量计数器 (线程安全)
	 */
	private final AtomicInteger threadCreationCounter = new AtomicInteger();

	/**
	 * 创建新的线程对象
	 * 
	 * @return  新创建的线程对象
	 */
	@Override
	public Thread newThread(Runnable runnable) {
		int threadNum = threadCreationCounter.incrementAndGet();
		// 通过ThreadFactory创建线程并设置其属性
		Thread workerThread = new Thread(runnable, THREAD_NAME_PREFIX + threadNum);
		workerThread.setUncaughtExceptionHandler(
				(thread, throwable) -> logger.error("Thread: {} {}", thread.getName(), throwable));
		
		return workerThread;
	}

}
