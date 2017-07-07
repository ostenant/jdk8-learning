package org.ostenant.jdk8.learning.examples.reference.negative;

/**
 * 通过数组定义来引用类，不会触发此类的初始化
 * 
 * @author <a href="http://ostenant.coding.me" target="_blank">Madison</a>
 * @version 1.0
 * @Note <br>
 *       <b>Date:</b> 2017年7月7日 下午4:56:31
 */
public class NegativeReference1 {

	public static class Child {
		static {
			System.out.println(Child.class.getSimpleName() + " is referred!");
		}
	}

	public static void main(String[] args) {
		Child[] childs = new Child[10];
	}

}
