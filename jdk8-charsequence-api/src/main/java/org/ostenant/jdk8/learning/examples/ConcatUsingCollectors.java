package org.ostenant.jdk8.learning.examples;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * JDK 1.8 对字符串拼接的改进
 * 
 * @author <a href="http://ostenant.coding.me" target="_blank">Madison</a>
 * @version 1.0
 * @Note <br>
 *       <b>Date:</b> 2017年7月4日 下午3:37:48
 */
public class ConcatUsingCollectors {

	private final static List<String> LIST = Arrays.asList("a", "b", "c", "d", "e", "f", "g");
	private final static String DELIMITER = ", ";
	private final static String PREFIX = "{ ";
	private final static String SUFFIX = " }";

	public static void main(String[] args) {
		String result = concatList(LIST, DELIMITER, PREFIX, SUFFIX);
		System.out.println(result);
	}

	public static String concatList(List<String> list, String delimiter, String prefix, String suffix) {
		return list.stream().collect(Collectors.joining(delimiter, prefix, suffix));
	}
}
