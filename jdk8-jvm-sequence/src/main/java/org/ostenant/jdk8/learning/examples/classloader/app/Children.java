package org.ostenant.jdk8.learning.examples.classloader.app;

public class Children extends Parent {
	static {
		CLASS_NAME = Children.class.getName();
		CLASS_LOADER_NAME = Children.class.getClassLoader().toString();
		System.out.println("Step b: " + CLASS_NAME + " is loaded by " + CLASS_LOADER_NAME);
	}

	{
		instanceID = this.toString();
		System.out.println("Step e: Children instance is created: " + CLASS_LOADER_NAME + " -> " + instanceID);
	}

	public Children() {
		System.out.println("Step f: Children instance：" + instanceID + ", constructor is invoked");
	}

	public void say() {
		System.out.println("My first class loader...");
	}
}
