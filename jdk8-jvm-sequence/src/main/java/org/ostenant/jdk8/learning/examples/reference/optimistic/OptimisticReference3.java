package org.ostenant.jdk8.learning.examples.reference.optimistic;

public class OptimisticReference3 {

	public static class Child {

		protected static String name;

		static {
			System.out.println(Child.class.getSimpleName() + " is referred!");
		}
	}

	public static void main(String[] args) {
		Child.name = "My SON";
	}

}
