package com.att.aodp.aodpportal;

import java.util.logging.Logger;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.att.api.framework.ajsc.util.DateTimeParamConverterProvider;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.att.aodp.aodpportal.service.RestServiceImpl;


@Component
public class JerseyConfiguration extends ResourceConfig {
	private static final Logger log = Logger.getLogger(JerseyConfiguration.class.getName());
	
	@Bean
	@Primary
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		objectMapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES);
		objectMapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
		return objectMapper;
	}
	
	@Autowired
    public JerseyConfiguration() {
		register(RestServiceImpl.class);
        property(ServletProperties.FILTER_FORWARD_ON_404, true);
        register(DateTimeParamConverterProvider.class);
        register(new LoggingFeature(log));
    }


}