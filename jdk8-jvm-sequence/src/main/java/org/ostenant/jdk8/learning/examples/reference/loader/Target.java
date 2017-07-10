package org.ostenant.jdk8.learning.examples.reference.loader;

public class Target {
	static {
		System.out.println(Target.class.getSimpleName() + " is loaded!");
	}
}
