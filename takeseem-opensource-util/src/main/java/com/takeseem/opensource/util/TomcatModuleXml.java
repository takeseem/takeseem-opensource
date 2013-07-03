/**
 * Copyright (c) 2013-2033 by takeseem.com
 * All rights reserved.
 * @author <a href="mailto:takeseem@gmail.com">takeseem.com</a>
 */
package com.takeseem.opensource.util;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * 生成tomcat module部署xml文档
 */
public class TomcatModuleXml {
	private static Set<String> classpathIgnoreKeys = new HashSet<>();
	static {
		classpathIgnoreKeys.add("servlet-api");
		classpathIgnoreKeys.add("tomcat");
	}
	
	public static void main(String[] args) {
		String webProject = System.getProperty("user.dir");
		List<String> classPath = new ArrayList<>(Arrays.asList(System.getProperty("java.class.path").split(":")));
		String tmpPath = classPath.get(0);
		if (tmpPath.endsWith("/target/classes")) {
			tmpPath = tmpPath.substring(0, tmpPath.length() - "classes".length()) + "test-classes";
			classPath.add(0, tmpPath);
			webProject = tmpPath.substring(0, tmpPath.length() - "/target/classes".length());
		}
		
		StringBuilder buf = new StringBuilder(8192);
		nextPath: 
			for (String path : new LinkedHashSet<>(classPath)) {
				int pos = path.lastIndexOf('/');
				if (pos == -1) pos = 0;
				
				for (String key : classpathIgnoreKeys) {
					if (path.indexOf(key, pos) != -1) continue nextPath;
				}
				
				buf.append(';').append(path);
			}
		
		PrintStream out = System.out;
		final String webClasspath = buf.substring(1);
		out.println("项目 classpath:" + webClasspath);

		File webapp = new File("src/main/webapp");
		if (!webapp.exists()) {
			webapp = new File(webProject, "src/main/webapp");
		} else {
			webProject = webapp.getAbsoluteFile().getParentFile().getParentFile().getParentFile().getAbsolutePath();
		}
		String baseName = new File(webProject).getName();
		if (!baseName.equalsIgnoreCase("web") && baseName.toLowerCase().endsWith("web")
				&& !Character.isLetterOrDigit(baseName.charAt(baseName.length() - "web".length() - 1))) {
			baseName = baseName.substring(0, baseName.length() - "web".length() - 1);
		}
		
		out.println();
		out.println("项目 Tomcat配置文件");
		out.println("$CATALINA_BASE/conf/Catalina/localhost/" + baseName + ".xml\n");
		out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
				+ "<Context docBase=\"" + webapp.getAbsolutePath() + "\" allowLinking=\"true\">\n"
				+ "	<Loader className=\"org.apache.catalina.loader.VirtualWebappLoader\" virtualClasspath=\"" + webClasspath + "\"/>\n"
				+ "	<JarScanner scanAllDirectories=\"true\" />\n"
				+ "	<Resource name=\"jdbc/mysql\" auth=\"Container\" type=\"javax.sql.DataSource\"\n"
				+ "		maxActive=\"4\" minIdle=\"2\" maxWait=\"3000\"\n"
				+ "		timeBetweenEvictionRunsMillis=\"14000\"\n"
				+ "		minEvictableIdleTimeMillis=\"28000\"\n"
				+ "		username=\"dev\" password=\"dev\" driverClassName=\"com.mysql.jdbc.Driver\"\n"
				+ "		url=\"jdbc:mysql://host:3306/dev?useUnicode=true&amp;characterEncoding=utf8\"/>\n"
				+ "</Context>\n");
	}
}
