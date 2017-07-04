package org.ostenant.jdk8.learning.examples;

import java.util.Arrays;
import java.util.List;

/**
 * JDK 1.8 对字符串拼接的改进
 * 
 * @author <a href="http://ostenant.coding.me" target="_blank">Madison</a>
 * @version 1.0
 * @Note <br>
 *       <b>Date:</b> 2017年7月4日 下午3:37:48
 */
public class ConcatUsingString {

	private final static List<String> LIST = Arrays.asList("a", "b", "c", "d", "e", "f", "g");
	private final static String DELIMITER = ", ";

	public static void main(String[] args) {
		String result = concatList(LIST, DELIMITER);
		System.out.println(result);
	}

	public static String concatList(List<String> list, String delimiter) {
		return String.join(delimiter, list);
	}
}
