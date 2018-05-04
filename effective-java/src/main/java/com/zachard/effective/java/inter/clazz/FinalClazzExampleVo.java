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

package com.zachard.effective.java.inter.clazz;

/**
 * <code>Effitive Java</code>第四章: 类和接口
 * 使可变性最小化 
 *    -- 不可变类示例
 * <pre>
 * </pre>
 *
 * @author zachard
 * @version 1.0.0
 */
public final class FinalClazzExampleVo {
	
	/**
	 * 所有的域都是final和私有
	 */
	private final double re;
	private final double im;
	
	/**
	 * 带参数构造器
	 * 
	 * @param re   实部
	 * @param im   虚部
	 */
	public FinalClazzExampleVo(double re, double im) {
		this.re = re;
		this.im = im;
	}
	
	/**
	 * 实部访问器
	 * @return
	 */
	public double realPart() {
		return re;
	}
	
	/**
	 * 虚部访问器
	 * @return
	 */
	public double imaginaryPart() {
		return im;
	}
	
	/**
	 * 相加操作
	 * 
	 * @param vo
	 * @return
	 */
	public FinalClazzExampleVo add(FinalClazzExampleVo vo) {
		return new FinalClazzExampleVo(re + vo.re, im + vo.im);
	} 
	
	/**
	 * 复数的相减操作
	 * 
	 * @param vo
	 * @return
	 */
	public FinalClazzExampleVo subtract(FinalClazzExampleVo vo) {
		return new FinalClazzExampleVo(re - vo.re, im - vo.im);
	}
	
	/**
	 * 复数的相乘操作
	 * 
	 * @param vo
	 * @return
	 */
	public FinalClazzExampleVo multiply(FinalClazzExampleVo vo) {
		return new FinalClazzExampleVo(re * vo.re - im * vo.im, 
				re * vo.im + im * vo.re);
	}
	
	/**
	 * 复数的相除操作
	 * 
	 * @param vo
	 * @return
	 */
	public FinalClazzExampleVo divide(FinalClazzExampleVo vo) {
		double tmp = vo.re * vo.re + vo.im * vo.im;
		return new FinalClazzExampleVo((re * vo.re + im * vo.im) / tmp, 
				(im * vo.re - re * vo.im) / tmp);
	}
	
	/**
	 * 覆写{@link #equals(Object)}方法
	 * 
	 * @param  o   需要比较的对象
	 * @return  两个对象是否相等
	 */
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		
		if (!(o instanceof FinalClazzExampleVo)) {
			return false;
		}
		
		FinalClazzExampleVo vo = (FinalClazzExampleVo) o;
		
		return Double.compare(re, vo.re) == 0 && 
				Double.compare(im, vo.im) == 0;
	}
	
	/**
	 * 计算对象的{@code hashCode}
	 * 
	 * @return  对象的{@code hashCode}值
	 */
	public int hashCode() {
		int result = 17 + hashDouble(re);
		result = 31 * result + hashDouble(im);
		
		return result;
	} 
	
	/**
	 * 总是覆写{@link #toString()}方法
	 * 
	 * @return  对象的字符串表示
	 */
	@Override
	public String toString() {
		return "(" + re + " + " + im + "i)";
	}
	
	/**
	 * 计算{@code double}类型的{@code hashCode}值
	 * 
	 * @param val    {@code double}类型值
	 * @return
	 */
	private int hashDouble(double val) {
		long longBits = Double.doubleToLongBits(val);
		
		return (int) (longBits ^ (longBits >>> 2));
	}

}
