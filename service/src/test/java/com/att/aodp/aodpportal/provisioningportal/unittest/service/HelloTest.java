package com.att.aodp.aodpportal.provisioningportal.unittest.service;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

//import com.att.api.framework.JerseyConfiguration;
import com.att.aodp.aodpportal.service.RestService;
import com.att.aodp.aodpportal.service.RestServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = { Application.class, RestServiceImpl.class })
public class HelloTest {
	
	
	@Autowired
	RestService service;
	

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testQuickHello() throws Exception {
		Response helloWorld = service.getQuickHello("test");
		assertEquals("message = Hello test!", helloWorld.getEntity().toString());
	}
	
	}