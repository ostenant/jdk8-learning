package org.ostenant.jdk8.learning.examples.reference.negative;

/**
 * 通过子类引用父类的的静态字段，不会导致子类初始化
 * 
 * @author <a href="http://ostenant.coding.me" target="_blank">Madison</a>
 * @version 1.0
 * @Note <br>
 *       <b>Date:</b> 2017年7月7日 下午4:56:31
 */
public class NegativeReference0 {

	public static class Parent {
		public static String name = "Father";
		static {
			System.out.println(Parent.class.getSimpleName() + " is referred!");
		}
	}

	public static class Child extends Parent {
		static {
			System.out.println(Child.class.getSimpleName() + " is referred!");
		}
	}

	public static void main(String[] args) {
		System.out.println(Child.name);
	}

}
