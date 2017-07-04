package org.ostenant.jdk8.learning.examples;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

/**
 * JDK 1.8 对字符串拼接的改进
 * 
 * @author <a href="http://ostenant.coding.me" target="_blank">Madison</a>
 * @version 1.0
 * @Note <br>
 *       <b>Date:</b> 2017年7月4日 下午3:37:48
 */
public class ConcatUsingStringJoiner {

	private final static List<String> LIST = Arrays.asList("a", "b", "c", "d", "e", "f", "g");
	private final static String DELIMITER = ", ";
	private final static String PREFIX = "{ ";
	private final static String SUFFIX = " }";

	public static void main(String[] args) {
		String result = concatList(LIST, DELIMITER);
		System.out.println(result);
		result = concatList(LIST, DELIMITER, PREFIX, SUFFIX);
		System.out.println(result);
	}

	public static String concatList(List<String> list, String delimiter) {
		StringJoiner result = new StringJoiner(DELIMITER);
		for (String i : list) {
			result.add(i);
		}
		return result.toString();
	}

	public static String concatList(List<String> list, String delimiter, String prefix, String suffix) {
		StringJoiner result = new StringJoiner(DELIMITER, PREFIX, SUFFIX);
		for (String i : list) {
			result.add(i);
		}
		return result.toString();
	}
}
