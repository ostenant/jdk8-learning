package org.ostenant.jdk8.learning.examples.checked.exception;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.junit.Test;

/**
 * 通过Lambda表达式和Nio实现指定目录下的所有.java文件的行数统计
 * 
 * @author <a href="http://ostenant.coding.me" target="_blank">Madison</a>
 * @version 1.0
 * @Note <br>
 *       <b>Date:</b> 2017年7月4日 下午7:28:17
 */
public class FileLineCounterLambdaTemplate {

	/**
	 * 使用try/catch处理异常
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFileLineCount1() throws Exception {
		Long count = Files
				.walk(Paths.get(
						// 1. 获取项目目录下的所有文件
						"E:/eclipse-learning-workspace"))
				// 2. 筛选过滤掉文件夹，得到所有的文件
				.filter(path -> !Files.isDirectory(path))
				// 3. 筛选出所有的.java文件
				.filter(path -> path.toString().endsWith(".java"))
				// 4. 返回单个文件的每一行，异常时返回一个空的Stream
				.flatMap(path -> {
					try {
						return Files.lines(path);
					} catch (IOException e) {
						e.printStackTrace();
						return Stream.empty();
					}
				})
				// 5. 筛选过滤掉所有的空行，即length=0的行
				.filter(line -> !line.trim().isEmpty())
				// 6. 统计有效行
				.count();
		System.out.println("代码行数： " + count);
	}

	/**
	 * 自定义一个FunctionInterface，作为会抛出受检异常的 Lambda表达式的目标类型 <br>
	 * Lambda中并不需要捕获异常，因为目标类型的apply方法已经将异常抛出了。
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFileLineCount2() throws Exception {
		Long count = Files
				.walk(Paths.get(
						// 1. 获取项目目录下的所有文件
						"E:/eclipse-learning-workspace"))
				// 2. 筛选过滤掉文件夹，得到所有的文件
				.filter(path -> !Files.isDirectory(path))
				// 3. 筛选出所有的.java文件
				.filter(path -> path.toString().endsWith(".java"))
				// 4. 返回单个文件的每一行，使用自定义的目标类型，将受检异常作为非受检异常抛出
				.flatMap(Uncheck.ofThrowsUncheck(path -> Files.lines(path)))
				// 5. 筛选过滤掉所有的空行，即length=0的行
				.filter(line -> !line.trim().isEmpty())
				// 6. 统计有效行
				.count();
		System.out.println("代码行数： " + count);
	}

	/**
	 * 自定义一个FunctionInterface，作为会抛出受检异常的 Lambda表达式的目标类型 <br>
	 * Lambda中并不需要捕获异常，因为目标类型的apply方法已经将异常抛出了。
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFileLineCount3() throws Exception {
		Long count = Files
				.walk(Paths.get(
						// 1. 获取项目目录下的所有文件
						"E:/eclipse-learning-workspace"))
				// 2. 筛选过滤掉文件夹，得到所有的文件
				.filter(path -> !Files.isDirectory(path))
				// 3. 筛选出所有的.java文件
				.filter(path -> path.toString().endsWith(".java"))
				// 4. 返回单个文件的每一行，使用自定义的目标类型，将受检异常打印出来
				.flatMap(Uncheck.ofPrint((path -> Files.lines(path)), Stream.empty()))
				// 5. 筛选过滤掉所有的空行，即length=0的行
				.filter(line -> !line.trim().isEmpty())
				// 6. 统计有效行
				.count();
		System.out.println("代码行数： " + count);
	}

}