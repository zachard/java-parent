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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

import org.junit.Test;

/**
 * <code>Java</code>的Fork-Join框架测试类
 * 
 * <pre>
 *     (1) {@link ForkJoinPool}表示一个用于执行{@link ForkJoinTask}的{@link ExecutorService}线程池执行器对象.
 *         {@code ForkJoinPool}提供了一个从非<code>ForkJoinTask</code>客户端提交任务的客户端(?), 同时提供一个
 *         管理和监控的操作.
 *         
 *         {@code ForkJoinPool}与其他<code>ExecutorService</code>执行的主要区别在于它"工作的积极性": 线程池中的
 *         所有线程都在尝试寻找及执行提交到线程池中的任务或是正在执行的任务新生成的任务(如果没有需要执行的任务, 线程将会
 *         阻塞等待). 当大多数任务会产生子任务(例如: <code>ForkJoinTasks</code>)或是外部客户端将提交许多小任务到线
 *         程池, <code>ForkJoinPool</code>将采取一种有效的处理方式. 特别是当创建的<code>ForkJoinPool</code>为
 *         异步方式时, <code>ForkJoinPools </code>同样适合那种从不联结的事件型任务
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class ForkJoinTest {
	
	/**
	 * <code>Fork-Join</code>框架测试示例
	 */
	@Test
	public void invokeTest() {
		final int SIZE = 10000000;
		double[] numbers = new double[SIZE];
		
		// 初始化计算数据
		for (int i = 0; i < SIZE; i++) {
			numbers[i] = Math.random();
		}
		
		CustomRecursiveTask task = new CustomRecursiveTask(numbers, 0, SIZE);
		ForkJoinPool pool = new ForkJoinPool();
		
		/*
		 * invoke方法用于处理提交的任务, 并且在完成之后返回结果. 在处理任务过程中如果出现异常或是错误, 将作为
		 * 结果直接抛出
		 */
		pool.invoke(task);
		System.err.println(pool.getPoolSize());
		System.err.println(task.join());
	}

}
