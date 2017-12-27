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

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * {@link ExecutorService}工厂类, 主要通过{@link Executors}类来创建<code>ExecutorService</code>实例
 * 
 * <pre>
 *     (1) {@link ExecutorService}接口继承了{@link Executor}接口, <code>Executor</code>表示任务执行器, 
 *         提供了管理终端和方法用于生产{@link Future}对象, <code>Future</code>对象用于追踪一个或多个异步
 *         任务的进度.
 *         <code>ExecutorService</code>可以被关闭, 关闭之后它将拒绝新的任务.{@link ExecutorService#shutdown()}
 *         方法允许终止前执行完已提交的方法, 而{@link ExecutorService#shutdownNow()}方法会阻止等待执行的
 *         任务执行并且尝试终止正在执行的任务. 终止状态的执行器中, 没有正在执行的任务、没有等待执行的任务并且新的任务不能
 *         被提交. 一个不再使用的<code>ExecutorService</code>应该及时关闭并释放其占用的资源.
 *         
 *         <code>submit</code>方法继承自用于创建并返回{@link Future}对象的{@link ExecutorService#execute(Runnable)}
 *         方法, <code>Future</code>对象可以用于取消任务的执行或是等待任务的完成. <code>invokeAny</code>方法或是
 *         <code>invokeAll</code>方法提供了一种方便的方式执行大量任务, 并且<code>invokeAny</code>会等待其中至少一个
 *         任务完成, 而<code>invokeAll</code>则会等待所有任务完成
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class ExecutorServiceFactory {
	
	/**
	 * 调用{@link Executors#newFixedThreadPool(int)}方法创建固定数量的线程
	 * 
	 * <pre>
	 *     (1) <code>Executors</code>的<code>newFixedThreadPool</code>用于创建一定数量可重用的线程池
	 *         并且操作一个共享的无限队列.在创建的线程池中, 任何时刻最多只有<code>nThreads</code>个线程处理
	 *         任务. 当线程池中所有线程都处于非空闲状态并有新增的任务提交到线程池时, 新提交的任务将进入队列并等
	 *         待一个可用的线程. 如果有线程在执行先前的任务失败导致线程关闭, 并且还有后续任务需要处理, 那么线程
	 *         池将创建一个新的线程来执行. <code>newFixedThreadPool</code>创建的线程中的线程将一直存活直到
	 *         线程池被关闭(即使线程为空闲状态)
	 *     (2) 如果线程池中有线程在执行任务失败导致线程终止, 而此时在任务队列中没有需要处理的任务, 那么线程池会
	 *         立即创建一个线程代替之前的线程? 还是等待有任务需要执行并且线程资源不够时创建线程?
	 * </pre>
	 * 
	 * @param nThreads    线程池中线程的数量
	 * @return            线程执行器
	 */
	public static ExecutorService createFixedThreadPool(int nThreads) {
		return Executors.newFixedThreadPool(nThreads);
	}
	
	/**
	 * 调用{@link Executors#newCachedThreadPool()}在必要时创建线程
	 * 
	 * <pre>
	 *     (1) <code>Executors</code>的<code>newCachedThreadPool</code>方法用于创建一个线程池, 在必要
	 *         线程池将创建新的线程, 但是之前创建的线程可使用时, 将重用之前的线程. 当<code>execute</code>方
	 *         法执行时, 如果之前构建的线程可以使用则重用, 否则将创建新的线程并增加到线程池中. 
	 *     (2) 这种线程池通常用于提高存在大量短暂异步任务程序的性能(因为任务时间短暂, 线程在没有超过空闲销毁时间
	 *         便再次被使用, 线程池也不需要为维护大量线程而消耗资源)
	 *     (3) 一个超过60秒未使用的线程将被结束并从缓存中移除, 因此, 一个线程池不管空闲多长时间都不会消耗任何资源. 
	 *         此外, 还可以{@link ThreadPoolExecutor}构造器来创建属性相似但是具体细节不同的线程池
	 * </pre>
	 * 
	 * @return    缓存线程池
	 */
	public static ExecutorService createCachedThreadPool() {
		return Executors.newCachedThreadPool();
	}
	
	/**
	 * 调用{@link Executors#newSingleThreadExecutor()}创建一个只有一个线程的线程池
	 * 
	 * <pre>
	 *     (1) <code>Executors</code>的<code>newSingleThreadExecutor</code>方法创建一个只有一个
	 *         工作线程的线程池, 并通过此线程操作无边界队列(如果在执行任务失败导致线程池中线程结束, 当后续
	 *         有任务需要执行时将创建一个新的线程). 
	 *     (2) 线程池中的任务将被顺序执行, 并且在任何时刻最多只有一个任务被执行. 与<code>newFixedThreadPool(1)
	 *         </code>不同的是, <code>newSingleThreadExecutor</code>保证了创建的线程池无法通过修改配置
	 *         来增加线程池中的数量
	 * </pre>
	 * 
	 * @return
	 */
	public static ExecutorService createSingleThreadExecutor() {
		return Executors.newSingleThreadExecutor();
	}
	
	/**
	 * 调用{@link Executors#newScheduledThreadPool(int)}创建一个用于预定执行而构建的线程池
	 * 
	 * <pre>
	 *     (1) <code>Executors</code>的<code>newScheduledThreadPool</code>方法用于创建一个
	 *         用于在给定时间后执行命令或是周期性执行的线程池
	 * </pre>
	 * 
	 * @param corePoolSize   线程池中存在的线程数, 即使这些线程处于空闲状态
	 * @return               新创建的用于预定执行的线程池
	 */
	public static ExecutorService createScheduledThreadPool(int corePoolSize) {
		return Executors.newScheduledThreadPool(corePoolSize);
	}
	
	/**
	 * 调用{@link Executors#newSingleThreadScheduledExecutor()}方法创建一个用于预定执行的单线程执行器
	 * 
	 * <pre>
	 *     (1) <code>Executors</code>的<code>newSingleThreadScheduledExecutor</code>方法用于创建一个
	 *         预定执行命令或是周期性执行的单线程执行器(如果在执行任务失败导致线程池中线程结束, 当后续有任务需要
	 *         执行时将创建一个新的线程). 
	 *     (2) 线程池中的任务将被顺序执行, 并且在任何时刻最多只有一个任务被执行. 与<code>newScheduledThreadPool(1)
	 *         </code>不同的是, <code>newSingleThreadScheduledExecutor</code>保证了创建的线程池无法通过修改配置
	 *         来增加线程池中的数量
	 * </pre>
	 * 
	 * @return
	 */
	public static ExecutorService createSingleThreadScheduledExecutor() {
		return Executors.newSingleThreadScheduledExecutor();
	}

}
