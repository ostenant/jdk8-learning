package org.ostenant.jdk8.learning.examples.basic;

import java.util.Random;

import org.junit.Test;

/**
 * ThreadLocal Lambda的表达式
 * 
 * @author <a href="http://ostenant.coding.me" target="_blank">Administrator</a>
 * @version 1.0
 * @Note <br>
 *       <b>Date:</b> 2017年7月3日 下午8:11:54
 */
public class ThreadLocalLambdaTemplate {

	/**
	 * ThreadLocal的工厂方法创建方式
	 * 
	 * @throws Exception
	 */
	@Test
	public void testThreadLocalLambda() throws Exception {
		ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> {
			Random r = new Random();
			return r.nextInt();
		});
		System.out.println(threadLocal.get());
	}
}