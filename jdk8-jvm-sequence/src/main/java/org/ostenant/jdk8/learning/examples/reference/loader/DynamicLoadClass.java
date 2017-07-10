package org.ostenant.jdk8.learning.examples.reference.loader;

import org.junit.Test;

public class DynamicLoadClass {

	private static final String SPECIFIED_CLASS_PATH = "org.ostenant.jdk8.learning.examples.reference.loader.Target";

	@Test
	public void testClassForName() throws Exception {
		Class.forName(SPECIFIED_CLASS_PATH);
		// Output: Target is loaded!
	}

	@Test
	public void testClassForNameNonInitializing() throws Exception {
		Class.forName(SPECIFIED_CLASS_PATH, false, DynamicLoadClass.class.getClassLoader());
		// Non output
	}

	@Test
	public void testLoadClass() throws Exception {
		ClassLoader loader = DynamicLoadClass.class.getClassLoader();
		loader.loadClass(SPECIFIED_CLASS_PATH);
		// Non output
	}

}
