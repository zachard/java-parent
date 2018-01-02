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

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * {@link RecursiveTask}对应的自定义实现类
 * 
 * <pre>
 *     (1) {@link RecursiveTask}代表一个递归的{@link ForkJoinTask}, 而{@code ForkJoinTask}表示一个
 *         通过{@link ForkJoinPool}执行的任务. 它表示一个与{@link Thread}类型对象类似但比正常的
 *         {@code Thread}更轻量的对象. 在<code>ForkJoinPool</code>中, 通过少量的线程就可以对大量的任务
 *         或是子任务进行处理.
 *         
 *         其实, {@code RecursiveTask}对象和{@code Thread}对象、{@code Runnable}对象及{@code Callable}
 *         对象属于同一类型的对象, 其<code>compute</code>方法与对应的<code>run</code>方法或者<code>call</code>
 *         方法作用类似
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public class CustomRecursiveTask extends RecursiveTask<Integer> {

	/**
	 * 序列化ID，显示声明时有如下好处:
	 * <pre>
	 *   1. 提高程序运行效率
	 *   2. 避免不同操作系统间计算方式不一致而导致生成值不一致
	 *   3. 避免类文件修改后导致同一个类出现不同ID值情况
	 * </pre>
	 */
	private static final long serialVersionUID = 3350753795398533922L;
	
	private static final int THRESHOLD = 1000;
	private double[] values;
	private int from;
	private int to;
	
	/**
	 * 任务构造函数
	 * 
	 * @param values
	 * @param from
	 * @param to
	 */
	public CustomRecursiveTask(double[] values, int from, int to) {
		this.values = values;
		this.from = from;
		this.to = to;
	}

	/**
	 * {@link RecursiveTask}对应的计算逻辑
	 * 
	 * <pre>
	 *      (1) 与{@code Callable}对象的<code>call</code>方法作用类似, 完成任务对应的逻辑(计算或是其他), 
	 *          并返回对应的结果
	 * </pre>
	 * 
	 * @return   逻辑计算结果
	 */
	@Override
	protected Integer compute() {
		
		/*
		 * 将任务进行拆分的示例: 对一个计算量大的计算任务拆分为多个计算量在THRESHOLD以内的多个子任务
		 */
		if (to - from < THRESHOLD) {
			int count = 0;
			
			/*
			 *  如果在下面的invokeAll中无法将Task放到线程池中执行, 是否可以
			 *  考虑将这个单个的逻辑封装成一个Callable任务, 放入到线程池中执行
			 */
			for (int i = from; i < to; i++) {
				if (values[i] > 0.5) {
					count++;
				}
			}
			
			return count;
		} else {
			int mid = (from + to) / 2;
			CustomRecursiveTask first = new CustomRecursiveTask(values, from, mid);
			CustomRecursiveTask second = new CustomRecursiveTask(values, mid, to);
			
			/*
			 * invokeAll方法执行并跟踪每个任务, 等到每一个任务都执行完或是抛出一个未检查异常, 当多于
			 * 一个任务在执行的过程中抛出异常时, invokeAll方法将选择其中一个异常抛出. 并且只要有一个
			 * 任务在执行过程中抛出异常, 其他任务都将被取消, 此时每个任务的执行状态并不保证为异常返回.
			 * 可以通过 getException() 方法获取每个任务的状态, 或是其他方法来判断任务是否被取消、完
			 * 成、出现异常还是未处理
			 * 
			 * first、second可以放到线程池中执行吗？
			 */
			invokeAll(first, second);
			
			/*
			 *  也可以通过get方法来获取Task中的值, 但是一般避免使用, 因为它抛出了已检查异常,
			 *  而在compute方法中不允许抛出这些异常
			 */
			return first.join() + second.join();
		}
	}

}
