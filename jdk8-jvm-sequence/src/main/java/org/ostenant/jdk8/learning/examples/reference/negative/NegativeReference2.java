package org.ostenant.jdk8.learning.examples.reference.negative;

/**
 * 常量在编译阶段会存入调用类的常量池中，本质上没有直接引用到定义常量的类，因此不会触发定义常量的类的初始化
 * 
 * @author <a href="http://ostenant.coding.me" target="_blank">Madison</a>
 * @version 1.0
 * @Note <br>
 *       <b>Date:</b> 2017年7月7日 下午4:56:31
 */
public class NegativeReference2 {

	public static class Child {

		public static final String name = "Child";

		static {
			System.out.println(Child.class.getSimpleName() + " is referred!");
		}
	}

	public static void main(String[] args) {
		System.out.println(Child.name);
	}

}
