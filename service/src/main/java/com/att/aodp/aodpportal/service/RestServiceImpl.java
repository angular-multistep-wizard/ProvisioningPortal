package com.att.aodp.aodpportal.service;

import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import com.att.aodp.aodpportal.model.HelloWorld;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class RestServiceImpl implements RestService {

	private static Logger log = LoggerFactory.getLogger(RestServiceImpl.class);

	public RestServiceImpl() {
	}

	@Override
	public Response getQuickHello(String name) {

		log.debug("In Rest Service");
		if (name == null || name.isEmpty()) {
			name = "world";
		}
		String message = "Hello " + name + "!";
		HelloWorld helloWorld = new HelloWorld(message);
		return Response.ok().entity(helloWorld).build();
	}
}
