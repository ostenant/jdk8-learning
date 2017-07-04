package org.ostenant.jdk8.learning.examples.checked.exception;

import java.util.Objects;
import java.util.function.Function;

public class Uncheck {

	/**
	 * 抛出非受检异常
	 * 
	 * @param mapper
	 * @return
	 */
	public static <T, R> Function<T, R> ofThrowsUncheck(UncheckedFunction<T, R> mapper) {
		Objects.requireNonNull(mapper);
		return t -> {
			try {
				return mapper.apply(t);
			} catch (Exception ex) {
				// 抛出运行时异常
				throw new RuntimeException(ex);
			}
		};
	}

	/**
	 * 以错误日志的方式打印出抛出的异常
	 * 
	 * @param mapper
	 *            通过Lambda表达式传入的函数，T代表函数的参数类型，R代表函数执行的返回值类型
	 * @param defaultR
	 * @return
	 */
	public static <T, R> Function<T, R> ofPrint(UncheckedFunction<T, R> mapper, R defaultR) {
		Objects.requireNonNull(mapper);
		return t -> {
			try {
				return mapper.apply(t);
			} catch (Exception ex) {
				// 打印出Lambda抛出的异常
				System.err.println(ex);
				// 返回默认的Stream值
				return defaultR;
			}
		};
	}

	@FunctionalInterface
	public static interface UncheckedFunction<T, R> {

		R apply(T t) throws Exception;
	}
}
