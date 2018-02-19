package com.att.aodp.aodpportal.provisioningportal.pact;

import static junit.framework.TestCase.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import com.att.aodp.aodpportal.provisioningportal.pact.ConsumerService;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRule;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.PactFragment;
import au.com.dius.pact.model.PactSpecVersion;

public class NewConsumerServicePact {
	
	@Rule
	    public PactProviderRule rule = new PactProviderRule("ProvisioningPortal", PactSpecVersion.V3, this);

	    @Pact(provider="ProvisioningPortal",consumer = "ProvisioningPortal") 
	    public PactFragment createFragment(PactDslWithProvider builder) { 
	    	
	    	Map<String,String> headers = new HashMap<String,String>();
        	headers.put("Content-Type", "application/json");
		

		return builder
			.given("csiTest")
			
			.uponReceiving("a POST request for Nodes")
            .path("/Services/rest/csi-bobpm/v503/sdncapacityplanning/nodes/query").method("POST")
            .willRespondWith()
            .headers(headers).status(202)

            .body(new PactDslJsonBody().stringType("technologies","Ciena 6500 SDN"))
            
                        
            .toFragment();
            
	    }
	    
	    @Test 
	    @PactVerification(value = "ProvisioningPortal" , fragment = "createFragment") 
	    public void runTest(){ 
	    	assertEquals(new ConsumerService(rule.getConfig().url()).getcsiMsg().toString(),"{\"technologies\":\"Ciena 6500 SDN\"}");
	    }
	    


}