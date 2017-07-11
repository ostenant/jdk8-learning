package org.ostenant.jdk8.learning.examples.classloader.custom;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 1. 继承ClassLoader 2. 覆写findClass()方法
 * 
 * @author <a href="http://ostenant.coding.me" target="_blank">Madison</a>
 * @version 1.0
 * @Note <br>
 *       <b>Date:</b> 2017年7月10日 下午3:06:31
 */
public class CustomClassLoader extends ClassLoader {
	private String classPath;

	public CustomClassLoader(String classPath) {
		this.classPath = classPath;
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		Class<?> c = findLoadedClass(name); // 可省略
		if (c == null) {
			byte[] data = loadClassData(name);
			if (data == null) {
				throw new ClassNotFoundException();
			}
			return defineClass(name, data, 0, data.length);
		}
		return null;
	}

	protected byte[] loadClassData(String name) {
		try {
			// package -> file folder
			name = name.replace(".", "//");
			FileInputStream fis = new FileInputStream(new File(classPath + "//" + name + ".class"));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int len = -1;
			byte[] b = new byte[2048];
			while ((len = fis.read(b)) != -1) {
				baos.write(b, 0, len);
			}
			fis.close();
			return baos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
