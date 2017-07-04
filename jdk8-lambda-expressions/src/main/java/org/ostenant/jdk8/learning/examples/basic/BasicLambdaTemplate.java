package org.ostenant.jdk8.learning.examples.basic;

import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BinaryOperator;

import org.junit.Test;

public class BasicLambdaTemplate {

	/**
	 * <p>
	 * Lambda 表达式不包含参数，使用空括号() 表示没有参数。
	 * </p>
	 * 该Lambda 表达式 实现了Runnable接口，该接口也只有一个run方法，没有参数，且返回类型为void。
	 * 
	 * @throws Exception
	 */
	@Test
	public void testNoArguments() throws Exception {
		Runnable noArguments = () -> System.out.println("Invoke the method logic!");
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.execute(noArguments);
	}

	/**
	 * <p>
	 * Lambda 表达式包含且只包含一个参数，可省略参数的括号
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testOneArgument() throws Exception {
		ActionListener oneArgument = event -> System.out.println("Button is clicked!");
		System.out.println("Create a new listener" + oneArgument.hashCode());
	}

	/**
	 * <p>
	 * Lambda 表达式的主体不仅可以是一个表达式，而且也可以是一段代码块，使用大括号 （{}）将代码块括起来
	 * </p>
	 * 代码块和普通方法遵循的规则别无二致，可以用返回或抛出异常来退出。
	 * 
	 * @throws Exception
	 */
	@Test
	public void testMultipleStatements() throws Exception {
		Runnable noArguments = () -> {
			System.out.println("Start a new thread!");
			System.out.println("Invoke the method logic!");
			System.out.println("Stop an existed thread!");
		};
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.execute(noArguments);
	}

	/**
	 * <p>
	 * Lambda表达式也可以表示包含多个参数的方法
	 * </p>
	 * 这段代码并不是计算两个数字的和，而是创建一个函数，并传入BinaryOperator中。
	 * 
	 * @throws Exception
	 */
	@Test
	public void testMultipleParameters() throws Exception {
		BinaryOperator<Long> addFunction = (x, y) -> x + y;
		System.out.println(addFunction.apply(100L, 100L));
	}

	/**
	 * <p>
	 * 所有Lambda表达式中的参数类型都是由编译器通过类型推断得出的。
	 * </p>
	 * 多个参数最好也可以显式声明参数类型
	 * 
	 * @throws Exception
	 */
	@Test
	public void testMultipleExplicitParameters() throws Exception {
		BinaryOperator<Long> addFunction = (Long x, Long y) -> x + y;
		System.out.println(addFunction.apply(100L, 100L));
	}

}