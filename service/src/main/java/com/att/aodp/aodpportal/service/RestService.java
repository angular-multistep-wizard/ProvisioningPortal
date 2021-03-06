package com.att.aodp.aodpportal.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import com.att.aodp.aodpportal.model.HelloWorld;

@Api
@Path("/service")
@Produces({ MediaType.APPLICATION_JSON })
public interface RestService {

	@GET
	@Path("/hello")
	@Produces({ MediaType.APPLICATION_JSON })
	@ApiOperation(
			value = "Respond Hello <name>!",
			notes = "Returns a JSON object with a string to say hello. "
					+ "Uses 'world' if a name is not specified",
			response = HelloWorld.class
	)
	@ApiResponses(
			value = {
					@ApiResponse(code = 404, message = "Service not available"),
					@ApiResponse(code = 500, message = "Unexpected Runtime error")
					})
	public Response getQuickHello(@QueryParam("name") String name);
}