package org.ostenant.jdk8.learning.examples.modifier;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

/**
 * @author <a href="http://ostenant.coding.me" target="_blank">Madison</a>
 * @version 1.0
 * @Note <br>
 *       闭包 <b>Date:</b> 2017年7月3日 下午5:09:25
 */
public class FinalModifierInnerClass {

	/**
	 * <p>
	 * 在JDK8以前，所有【闭包】内部类引用的外部变量都必须被声明为final，意味着不能为其重复赋值。
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFinalModifierBeforeJDK8() throws Exception {
		final String name = getUserName();
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.execute(new Runnable() {
			@Override
			public void run() {
				System.out.println(name);
			}
		});
	}

	/**
	 * <p>
	 * Lambda表达式中引用既成事实上的final变量
	 * </p>
	 * Lambda表达式中引用的变量被编译器标识为final。当试图去修改值时，会发生编译时异常 -
	 * 在闭包作用域内使用过的外部局部变量必须为final类型。
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFinalModifierInJDK8() throws Exception {
		String name = getUserName();
		Runnable runnable = () -> System.out.println(name);
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.execute(runnable);
	}

	protected String getUserName() {
		return "Madison";
	}

}
