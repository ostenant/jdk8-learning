package org.ostenant.jdk8.learning.examples.reference.optimistic;

public class OptimisticReference1 {

	public static class Parent {
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
		new Child();
	}

}
