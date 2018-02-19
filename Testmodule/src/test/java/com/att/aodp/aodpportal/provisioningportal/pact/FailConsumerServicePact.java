package com.att.aodp.aodpportal.provisioningportal.pact;

import static junit.framework.TestCase.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.springframework.web.client.HttpClientErrorException;

import com.att.aodp.aodpportal.provisioningportal.pact.ConsumerService;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRule;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.PactFragment;
import au.com.dius.pact.model.PactSpecVersion;

public class FailConsumerServicePact {
	
	@Rule
	    public PactProviderRule rule = new PactProviderRule("csiprovider2", PactSpecVersion.V3, this);

	    @Pact(provider="csiprovider2",consumer = "failedconsumer") 
	    public PactFragment createFragment(PactDslWithProvider builder) { 
	    
	    	Map<String,String> request_headers = new HashMap<String,String>();
	    	Map<String,String> response_headers = new HashMap<String,String>();
	    	request_headers.put("Content-Type", "application/json");
	    	request_headers.put("Authorization", "Basic Y3NpdGVzdDp0ZXN0aW5nY3Np");
	    //	response_headers.put("status", "202");
	    	String value = "[\"Ciena 6500 SDN\"]";
	    	

		return builder
			.given("csiTest")
			
			.uponReceiving("a POST request for Nodes")
            .path("/Services/rest/csi-bobpm/v503/sdncapacityplanning/nodes/query").method("POST")
            .headers(request_headers)
            .willRespondWith()
          //  .headers(response_headers).status(401)
            .status(401)
            .body(new PactDslJsonBody().object("RequestError").object("ServiceException").stringType("MessageId", "400").stringType("Text", "Bad Request Missing or Invalid Payload"))
            
                        
            .toFragment();
	 
	    
	    }
	    
	    @Test 
	    @PactVerification(value = "csiprovider2" , fragment = "createFragment") 
	    public void runTest(){ 
	    	assertEquals(new ConsumerService(rule.getConfig().url()).geterrorcsiMsg().toString(),"{\"RequestError\":{\"ServiceException\":{\"Text\":\"Bad Request Missing or Invalid Payload\",\"MessageId\":\"400\"}}}");
	    }
	    


}