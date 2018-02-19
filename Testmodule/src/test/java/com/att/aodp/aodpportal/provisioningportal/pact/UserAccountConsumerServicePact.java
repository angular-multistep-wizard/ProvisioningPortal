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

public class UserAccountConsumerServicePact {
	
	@Rule
	    public PactProviderRule rule = new PactProviderRule("useraccountprovider", PactSpecVersion.V3, this);

	    @Pact(provider="useraccountprovider",consumer = "demo812017") 
	    public PactFragment createFragment(PactDslWithProvider builder) { 
	    	
	    	Map<String,String> headers = new HashMap<String,String>();
        	headers.put("Content-Type", "application/json");
		

		return builder
			.given("useraccounttest")
			
			.uponReceiving("a POST request for Nodes")
            .path("/csi-cc/v1/uverseAccountProfile/accounts").method("GET").query("accountNumber=181789541&assetType=IRD&assetStatus=Active&serviceCode= DTVM,ATTDV,DVPP,CONT,CCCI,SPP,CVOIP,DTV,FL,HSIA,IP,IPCP,MPP,RG,ROUTER,STB,SPP,VBP,VOIPCP,WSBAN,CWSBAN,CWSCTN,WS,DTVCP,QUCOM,UCONT&summary=true")
            .willRespondWith()
            .headers(headers).status(202)
            .body(new PactDslJsonBody().integerType("billingAccountNumber","181789541").integerType("accountId","1483456").stringType("accountStatus", "O").integerType("dealerId", "43671"))
            
                        
            .toFragment();
            
	    }
	    
	    @Test 
	    @PactVerification(value = "useraccountprovider" , fragment = "createFragment") 
	    public void runTest(){ 
	    	assertEquals(new ConsumerService(rule.getConfig().url()).getUserAccount().toString(),"{\"technologies\":\"Ciena 6500 SDN\"}");
	    }
	    


}