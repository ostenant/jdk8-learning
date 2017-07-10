package org.ostenant.jdk8.learning.examples.classloader;

import org.junit.Test;

public class AppClassLoaderTester {

	@Test
	public void testExecutionSequence() throws Exception {
		new Children();
	}

}
