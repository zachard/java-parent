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

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;
import java.util.concurrent.ThreadPoolExecutor.DiscardPolicy;
import java.util.concurrent.TimeUnit;

/**
 * {@link ThreadPoolExecutor}类的使用与创建示例
 * 
 * <pre>
 *     (1) {@link ThreadPoolExecutor}表示一个{@link ExecutorService}类型的线程池, 对于提交的任务, 将从任务
 *         池中选取一个用于执行此任务, 一般都是通过{@link Executors}的工厂方法来配置该对象.
 *         
 *         从广义上来说, {@code ThreadPoolExecutor}提供了许多可调整的参数及灵活的钩子, 但是, 程序员可能更倾向于
 *         使用{@code Executors}中工厂方法来创建线程池(例如: {@code Executors#newCachedThreadPool()}), 因
 *         为它更便利. <code>Executors</code>为许多通用的场景预先进行了配置. 
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class ThreadPoolExecutorFactory {
	
	/**
	 * 创建一个全部参数的{@link ThreadPoolExecutor}对象, 直接返回一个新的实例
	 * 
	 * <pre>
	 *     (1) 线程池处理任务的过程: 
	 *         (1.1) 当线程池中的工作的线程数小于<code>corePoolSize</code>时, 执行器会选择创建新的线程
	 *               处理任务而非将任务加入阻塞队列
	 *         (1.2) 如果线程池中的工作的线程数大于或等于<code>corePoolSize</code>, 执行器会选择将新的
	 *               任务放入阻塞队列, 等待空闲线程对它进行处理, 而不会创建新的线程
	 *         (1.3) 如果任务阻塞队列已满, 当线程池中工作的线程数小于<code>maximumPoolSize</code>时, 
	 *               将创建新的线程对任务进行处理, 否则将按照饱和策略来处理新提交的任务
	 * </pre>
	 * 
	 * @param corePoolSize    线程池的基本大小, 当提交一个任务到线程池时, 线程池会创建一个线程来执行任务.
	 *                        即使其他空闲的基本线程能够执行新任务, 线程也会创建, 等到需要执行的任务数大于
	 *                        线程池基本大小时就不再创建. 但是如果调用了{@link ThreadPoolExecutor#prestartAllCoreThreads()}
	 *                        方法, 线程池会提前创建并启动基本线程
	 *                        
	 * @param maximumPoolSize  线程池允许创建的最大线程数. 如果队列满了, 并且已创建的线程数小于最大线程数, 则线程池会再创建新的线程
	 *                         执行任务. 注: 如果使用了无界的任务队列, 这个参数没有效果
	 *                         
	 * @param keepAliveTime    线程池中空闲线程保持存活的时间, 如果需要执行的任务很多, 并且每个任务执行的时间较短, 可以提高这个时间,
	 *                         提高线程的利用率
	 *                         
	 * @param unit             线程活动保持时间的单位, 可选单位有: {@link TimeUnit#DAYS}(天), {@link TimeUnit#HOURS}(小时), 
	 *                         {@link TimeUnit#MINUTES}(分钟)等
	 *                         
	 * @param workQueue        用于等待执行的任务阻塞队列, 可供选择的队列如下: 
	 *                         (1) {@link ArrayBlockingQueue}表示基于数组结构的有界阻塞队列, 按照FIFO(先进先出)
	 *                             原则对元素进行排序
	 *                         (2) {@link LinkedBlockingQueue}表示基于链表结构的阻塞队列, 按照FIFO(先进先出)原则
	 *                             对元素进行排序
	 *                         (3) {@link SynchronousQueue}表示一个不存储元素的阻塞队列, 每个插入操作必须等到另外一个
	 *                             线程调用移除操作, 否则插入操作将一直处于阻塞状态
	 *                         (4) {@link PriorityBlockingQueue}表示一个具有优先级的无限阻塞队列
	 *                         
	 * @param threadFactory    用于创建线程的工厂, 可以通过线程工厂给每个创建出来的线程设置更有意义的名字
	 * 
	 * @param handler          当队列和线程池都满了, 用于处理新提交任务的饱和策略, 不同的饱和策略如下: 
	 *                         (1) {@link AbortPolicy}默认的饱和策略, 表示无法处理新提交任务时抛出异常
	 *                         (2) {@link CallerRunsPolicy}表示只用调用者的线程来运行任务
	 *                         (4) {@link DiscardOldestPolicy}表示丢弃队列中最近的一个任务, 并执行当前任务
	 *                         (3) {@link DiscardPolicy}表示不处理任务, 将其丢弃
	 *                         
	 * @return                 线程池对象
	 */
	public static ThreadPoolExecutor createFullThreadPoolExecutor(
			int corePoolSize,
            int maximumPoolSize,
            long keepAliveTime,
            TimeUnit unit,
            BlockingQueue<Runnable> workQueue,
            ThreadFactory threadFactory,
            RejectedExecutionHandler handler) {
		return new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, 
				unit, workQueue, threadFactory, handler);
	}

}
