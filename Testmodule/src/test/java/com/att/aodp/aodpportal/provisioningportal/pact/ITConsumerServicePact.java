package com.att.aodp.aodpportal.provisioningportal.pact;

import static junit.framework.TestCase.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import com.att.aodp.aodpportal.provisioningportal.pact.HelloConsumerService;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRule;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.PactFragment;
import au.com.dius.pact.model.PactSpecVersion;

public class ITConsumerServicePact {

    @Rule
        public PactProviderRule rule = new PactProviderRule("ProvisioningPortal", PactSpecVersion.V3, this);

        @Pact(provider="ProvisioningPortal",consumer = "ProvisioningPortal") 
	    public PactFragment createFragment(PactDslWithProvider builder) { 
	    	
	    	Map<String, String> responseHeaders = new HashMap<>();
	    	Map<String, String> requestHeaders = new HashMap<>(); 
	    	
	    	responseHeaders.put("Content-Type", "application/json"); 
	    	requestHeaders.put("X-ATT-ServiceName", "helloworld");
	    	requestHeaders.put("Authorization", "Basic Y3NpdGVzdDp0ZXN0aW5nY3Np"); 
	    	
	    	return builder
	    			.given("getQuickHello")
	    			.uponReceiving("a request to get a hello")
	    			.path("/restservices/helloworld/v1/service/hello")
	    			.method("GET")
	    			.headers(requestHeaders)
	    			.willRespondWith()
	    			.headers(responseHeaders)
	    			.status(200)
	    			.body(new PactDslJsonBody()
	    			.stringType("message", "Hello world!"))
	           		.toFragment();
	    	
	    }

        @Test
        @PactVerification(value = "ProvisioningPortal" , fragment = "createFragment") 
	    public void runTest(){ 
	    	assertEquals(new HelloConsumerService(rule.getConfig().url()).getQuickHello().toString(),"{\"message\":\"Hello world!\"}");
	    }
	    

}
