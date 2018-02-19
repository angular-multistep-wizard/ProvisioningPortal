package com.att.aodp.aodpportal.provisioningportal.componenttest.mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.att.aodp.aodpportal.provisioningportal.pact.ConsumerService;


public class ITComponentTest {
 
	   ConsumerService consumerService = mock(ConsumerService.class);

	   @Test
	   public void greets() {
	   String msg = "{\"message\":\"Hello world!\"}";
	   when(consumerService.getWelcomeMsg()).thenReturn(msg);
	   assertEquals(consumerService.getWelcomeMsg().toString(),"{\"message\":\"Hello world!\"}");
	   }
	   
	}