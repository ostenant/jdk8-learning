package org.ostenant.jdk8.learning.examples.classloader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import org.junit.Test;

public class CustomerClassLoaderTester {

	private static final String SOURCE_CODE_LOCATION = "E://eclipse-learning-workspace//jdk8-learning//jdk8-jvm-sequence//src//main//java//org//ostenant//jdk8//learning//examples//classloader//";

	private static final String CHILDREN_SOURCE_CODE_NAME = SOURCE_CODE_LOCATION + "Children.java";
	private static final String PARENT_SOURCE_CODE_NAME = SOURCE_CODE_LOCATION + "Parent.java";
	private static final List<String> SOURCE_CODE = Arrays.asList(CHILDREN_SOURCE_CODE_NAME, PARENT_SOURCE_CODE_NAME);

	private static final String TARGET_CODE_LOCALTION = "E://myclassloader//classpath";

	static {
		SOURCE_CODE.stream().map(path -> new File(path))
				// 路径转文件对象
				.filter(f -> !f.isDirectory())
				// 文件遍历
				.forEach(f -> {
					// 拷贝后源代码
					File targetFile = copySourceFile(f);
					// 编译源代码
					compileSourceFile(targetFile);
				});
	}

	/**
	 * 拷贝源文件到目标目录
	 * 
	 * @param 源文件
	 * @return
	 */
	protected static File copySourceFile(File f) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			reader = new BufferedReader(new FileReader(f));
			// package ...;
			String firstLine = reader.readLine();

			StringTokenizer tokenizer = new StringTokenizer(firstLine, " ");
			String packageName = "";
			while (tokenizer.hasMoreElements()) {
				String e = tokenizer.nextToken();
				if (e.contains("package")) {
					continue;
				} else {
					packageName = e.trim().substring(0, e.trim().length() - 1);
				}
			}

			// package -> path
			String packagePath = packageName.replace(".", "//");
			// java file path
			String targetFileLocation = TARGET_CODE_LOCALTION + "//" + packagePath + "//";

			String sourceFilePath = f.getPath();
			String fileName = sourceFilePath.substring(sourceFilePath.lastIndexOf("\\") + 1);

			File targetFile = new File(targetFileLocation, fileName);
			File targetFileLocationDir = new File(targetFileLocation);
			if (!targetFileLocationDir.exists()) {
				targetFileLocationDir.mkdirs();
			}
			// writer
			writer = new BufferedWriter(new FileWriter(targetFile));
			// 写入第一行
			writer.write(firstLine);
			writer.newLine();
			writer.newLine();

			String input = "";
			while ((input = reader.readLine()) != null) {
				writer.write(input);
				writer.newLine();
			}

			return targetFile;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 手动编译java源码
	 * 
	 * @param f
	 *            类源文件的文件路径
	 * @param specifiedName
	 *            类的全路径
	 * @return
	 */
	protected static void compileSourceFile(File f) {
		try {
			JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
			StandardJavaFileManager standardFileManager = javaCompiler.getStandardFileManager(null, null, null);
			Iterable<? extends JavaFileObject> javaFileObjects = standardFileManager.getJavaFileObjects(f);

			// 执行编译任务
			CompilationTask task = javaCompiler.getTask(null, standardFileManager, null, null, null, javaFileObjects);
			task.call();
			standardFileManager.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void test() throws Exception {
		// 创建自定义类加载器
		CustomClassLoader classLoader = new CustomClassLoader(TARGET_CODE_LOCALTION);
		// 动态加载class文件到内存中（无连接）
		Class<?> c = classLoader.loadClass("org.ostenant.jdk8.learning.examples.classloader.Children");
		// 通过反射拿到所有的方法
		Method[] declaredMethods = c.getDeclaredMethods();
		for (Method method : declaredMethods) {
			if ("cry".equals(method.getName())) {
				// 通过反射拿到children对象
				Object children = c.newInstance();
				method.invoke(children);
				break;
			}
		}

	}

}
