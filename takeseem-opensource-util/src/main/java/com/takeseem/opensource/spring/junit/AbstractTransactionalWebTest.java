/**
 * Copyright (c) 2013-2033 by takeseem.com
 * All rights reserved.
 * @author <a href="mailto:takeseem@gmail.com">takeseem.com</a>
 */
package com.takeseem.opensource.spring.junit;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.ServletTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.ServletWebRequest;

@WebAppConfiguration
@TestExecutionListeners(value = {
	ServletTestExecutionListener.class,
	DependencyInjectionTestExecutionListener.class, 
	DirtiesContextTestExecutionListener.class, 
	TransactionalTestExecutionListener.class
}, inheritListeners = false)
@Transactional
public abstract class AbstractTransactionalWebTest extends AbstractTransactionalJUnit4SpringContextTests {
	protected @Autowired WebApplicationContext wac;
	protected @Autowired MockServletContext servletContext;
	protected @Autowired MockHttpSession session;
	protected @Autowired MockHttpServletRequest request;
	protected @Autowired MockHttpServletResponse response;
	protected @Autowired ServletWebRequest webRequest;
    protected MockMvc mvc;
    
    @Before
    public void _setup() {
    	mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
}
