/**
 * Copyright (c) 2013-2033 by takeseem.com
 * All rights reserved.
 * @author <a href="mailto:takeseem@gmail.com">takeseem.com</a>
 */
package com.takeseem.opensource.junit;

import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.naming.NamingException;

import org.springframework.mock.jndi.SimpleNamingContextBuilder;

/**
 * 为junit测试提供jndi环境
 */
public class SimpleNamingContextService {
	private SimpleNamingContextBuilder builder = new SimpleNamingContextBuilder();
	
	public void setBinds(Map<String, Object> binds) {
		for (Entry<String, Object> entry : binds.entrySet()) {
			builder.bind(entry.getKey(), entry.getValue());
		}
	}
	
	@PostConstruct
	public void activate() throws NamingException {
		builder.activate();
	}
	
	@PreDestroy
	public void deactivate() {
		builder.deactivate();
	}
}
