package com.att.aodp.aodpportal.provisioningportal.pact;

import static junit.framework.TestCase.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
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

public class ConsumerhirarcyServicePact {
	
		@Rule
	    public PactProviderRule rule = new PactProviderRule("testmodel", PactSpecVersion.V3, this);

	    @Pact(provider="testmodel",consumer = "testmodel") 
	    public PactFragment createFragmentB(PactDslWithProvider builder) { 
	    	
	    	Map<String, String> headers = new HashMap<>(); 
	    	headers.put("Content-Type", "application/json"); 
	    	headers.put("Authorization", "Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ=="); 
			
	    	return builder
	    			.given("IntegratedServiceAccountDetails")
	    			
	    			.uponReceiving("Get Account information")
	    			.path("/csi-cc/v1/uverseAccountProfile/accounts")
	    			.method("GET")
	    			.query("accountNumber=181789541&assetType=IRD&assetStatus=Active&serviceCode=DTVM,ATTDV,DVPP,CONT,CCCI,SPP,CVOIP,DTV,FL,HSIA,IP,IPCP,MPP,RG,ROUTER,STB,SPP,VBP,VOIPCP,WSBAN,CWSBAN,CWSCTN,WS,DTVCP,QUCOM,UCONT&serviceStatus=AC&summary=true")
	    			.willRespondWith()
	    			.headers(headers)
	    			.status(200)
	    			.body(
	    			new PactDslJsonBody()
	    			
	    			.stringType("languageCode", "SPANISH")

	    			.object("accountDetails")
	    			.stringType("accountId", "1483456")
	    			.stringType("billingAccountNumber", "181789541")
	    			.stringType("accountStatus", "0")
	    			.stringType("dealerId", "43671")
	    			.closeObject()
	    			
	    			.object("address")
	    			.object("serviceAddress")
	    			.stringType("countyZipCode", "23415")
	    			.stringType("timezone", "Eastern")
	    			.stringType("fipsStateCountyCode", "75001")
	    			.stringType("daylightSavingsIndicator", "true")
	    			.closeObject()
	    			.object("billingAddress")
	    			.stringType("billingName", "true")
	    			.closeObject()
	    			.closeObject()
	    			
	    			.object("billingAndFinancials")
	    			.stringType("statementDeliveryOption", "N")
	    			.closeObject()
	    			
	    			.object("customerName")
	    			.stringType("namePrefix", "Mr")
	    			.stringType("firstName", "Fred")
	    			.stringType("middleName", "")
	    			.stringType("lastName", "Castro")
	    			.stringType("nameSuffix", "")
	    			.stringType("additionalTitle", "")
	    			.closeObject()
	    			
	    			.object("emailAddress")
	    			.stringType("emailAddress", "fred.castro@gmail.com")
	    			.stringType("validEmailAddressIndicator", "true")
	    			.stringType("refusedEmailIndicator", "true")
	    			.closeObject()
	    			
	    			.array("smartAttribute")
	    			.object()
	    			.stringType("attributeName", "smartDTV1")
	    			.stringType("effectiveStartDate", "2016-03-19")
	    			.stringType("effectiveEndDate", "2016-03-19")
	    			.closeObject()
	    			
	    			.object()
	    			.stringType("attributeName", "smartDTV1")
	    			.stringType("effectiveStartDate", "2016-03-19")
	    			.stringType("effectiveEndDate", "2016-03-19")
	    			.closeObject()

	    			.object()
	    			.stringType("attributeName", "smartDTV3")
	    			.stringType("effectiveStartDate", "2016-03-19")
	    			.closeObject()
	    			.closeArray()
	    			
	    			.object("commonCustomerAttribute")
	    			.stringType("attributeName", "CCV")
	    			.stringType("attributeValue", "attributeValue")
	    			.stringType("effectiveStartDate", "2001-01-01")
	    			.stringType("effectiveEndDate", "2001-01-01")
	    			.closeObject()
	    			
	    			.object("package")
	    			.stringType("code", "code")
	    			.stringType("name", "name")
	    			.stringType("description", "description")
	    			.stringType("status", "0")
	    			.stringType("authorizationCodeList", "authorizationCodeList")
	    			.stringType("effectiveEndDate", "2001-12-31T12:00:00")
	    			.stringType("effectiveStartDate", "2001-12-31T12:00:00")
	    			.stringType("lastUpdateDate", "2001-12-31T12:00:00")
	    			.closeObject()
	    			
	    			.object("partnerDetails")
	    			.stringType("jointBillingStatus", "C")
	    			.closeObject()
	    			
	    			.array("asset")
	    			.object()
	    			.stringType("receiverId", "1782022839033")
	    			.stringType("equipmentOwnership", "OWN")
	    			.stringType("location", "OFFICE")
	    			.stringType("manufacturer", "Google")
	    			.stringType("model", "Model4")
	    			.stringType("serialNumber", "1782022839034")
	    			.stringType("memberId", "1238997")
	    			.stringType("assetStatus", "ACTV")
	    			.closeObject()
	    			
	    			.object()
	    			.stringType("receiverId", "1782022839000")
	    			.stringType("equipmentOwnership", "OWN")
	    			.stringType("location", "OFFICE")
	    			.stringType("manufacturer", "Google")
	    			.stringType("model", "Model4")
	    			.stringType("serialNumber", "1782022839068")
	    			.stringType("memberId", "1238993")
	    			.stringType("assetStatus", "ACTV")
	    			.closeObject()
	    			
	    			.object()
	    			.stringType("equipmentOwnership", "LEASE")
	    			.stringType("location", "OFFICE")
	    			.stringType("manufacturer", "Google")
	    			.stringType("model", "Model34")
	    			.stringType("serialNumber", "2782022839068")
	    			.stringType("memberId", "1238993")
	    			.stringType("assetStatus", "ACTV")
	    			.closeObject()
	    			.closeArray()
	    			
	    			.object("service")
	    			.stringType("type","DV")
	    			.object("DTVAttributes")
	    			.stringType("broadbandCallbackDate","2016-03-19")
	    			.stringType("oppvRestrictedIndicator","true")
	    			.stringType("videoServiceStatus","AC")
	    			.closeObject()
	    			.closeObject()
	    			
	    			.object("productComponentDetails")
	    			.stringType("serviceTypeCode","ATTDV")
	    			.stringType("status","status")
	    			.stringType("state","state")
	    			.object("data")
	    			.stringType("code","CONTRACT")
	    			.stringType("name","name of component code")
	    			.object("pricePlanData")
	    			.stringType("promSaleType","promSaleType")
	    			.stringType("effectivedate","2001-01-01")
	    			.stringType("expirationDate","2001-01-01")
	    			.stringType("promoDuration","promoDuration")
	    			.stringType("promoServiceType","promoServiceType")
	    			.stringType("promoInterval","promoInterval")
	    			.stringType("promoType","promoType")
	    			.object("chargeData")
	    			.stringType("name","name")
	    			.closeObject()
	                .closeObject()
	                .object("attribute")
	                .stringType("code","code")
	                .stringType("value","value")
	                .closeObject()
	                .closeObject()
	                .closeObject()

	    			)
	           		.toFragment();
	    	
	    }
	    
	    
	    @Test 
	    @PactVerification(value = "testmodel" , fragment = "createFragmentB") 
	    public void runTestB() { 

	    	assertEquals(new ConsumerService(rule.getConfig().url()).getAuthMsg().toString(),
    			"{\"smartAttribute\":[{\"effectiveEndDate\":\"2016-03-19\",\"effectiveStartDate\":\"2016-03-19\",\"attributeName\":\"smartDTV1\"},{\"effectiveEndDate\":\"2016-03-19\",\"effectiveStartDate\":\"2016-03-19\",\"attributeName\":\"smartDTV1\"},{\"effectiveStartDate\":\"2016-03-19\",\"attributeName\":\"smartDTV3\"}],\"address\":{\"serviceAddress\":{\"timezone\":\"Eastern\",\"countyZipCode\":\"23415\",\"fipsStateCountyCode\":\"75001\",\"daylightSavingsIndicator\":\"true\"},\"billingAddress\":{\"billingName\":\"true\"}},\"package\":{\"effectiveEndDate\":\"2001-12-31T12:00:00\",\"code\":\"code\",\"lastUpdateDate\":\"2001-12-31T12:00:00\",\"name\":\"name\",\"authorizationCodeList\":\"authorizationCodeList\",\"effectiveStartDate\":\"2001-12-31T12:00:00\",\"description\":\"description\",\"status\":\"0\"},\"partnerDetails\":{\"jointBillingStatus\":\"C\"},\"languageCode\":\"SPANISH\",\"customerName\":{\"firstName\":\"Fred\",\"lastName\":\"Castro\",\"namePrefix\":\"Mr\",\"nameSuffix\":\"\",\"additionalTitle\":\"\",\"middleName\":\"\"},\"productComponentDetails\":{\"data\":{\"code\":\"CONTRACT\",\"name\":\"name of component code\",\"pricePlanData\":{\"chargeData\":{\"name\":\"name\"},\"promoDuration\":\"promoDuration\",\"promoInterval\":\"promoInterval\",\"promoType\":\"promoType\",\"promSaleType\":\"promSaleType\",\"promoServiceType\":\"promoServiceType\",\"effectivedate\":\"2001-01-01\",\"expirationDate\":\"2001-01-01\"},\"attribute\":{\"code\":\"code\",\"value\":\"value\"}},\"state\":\"state\",\"serviceTypeCode\":\"ATTDV\",\"status\":\"status\"},\"emailAddress\":{\"refusedEmailIndicator\":\"true\",\"emailAddress\":\"fred.castro@gmail.com\",\"validEmailAddressIndicator\":\"true\"},\"accountDetails\":{\"accountStatus\":\"0\",\"accountId\":\"1483456\",\"dealerId\":\"43671\",\"billingAccountNumber\":\"181789541\"},\"billingAndFinancials\":{\"statementDeliveryOption\":\"N\"},\"service\":{\"DTVAttributes\":{\"broadbandCallbackDate\":\"2016-03-19\",\"oppvRestrictedIndicator\":\"true\",\"videoServiceStatus\":\"AC\"},\"type\":\"DV\"},\"asset\":[{\"equipmentOwnership\":\"OWN\",\"receiverId\":\"1782022839033\",\"serialNumber\":\"1782022839034\",\"location\":\"OFFICE\",\"model\":\"Model4\",\"assetStatus\":\"ACTV\",\"manufacturer\":\"Google\",\"memberId\":\"1238997\"},{\"equipmentOwnership\":\"OWN\",\"receiverId\":\"1782022839000\",\"serialNumber\":\"1782022839068\",\"location\":\"OFFICE\",\"model\":\"Model4\",\"assetStatus\":\"ACTV\",\"manufacturer\":\"Google\",\"memberId\":\"1238993\"},{\"equipmentOwnership\":\"LEASE\",\"serialNumber\":\"2782022839068\",\"location\":\"OFFICE\",\"model\":\"Model34\",\"assetStatus\":\"ACTV\",\"manufacturer\":\"Google\",\"memberId\":\"1238993\"}],\"commonCustomerAttribute\":{\"effectiveEndDate\":\"2001-01-01\",\"attributeValue\":\"attributeValue\",\"effectiveStartDate\":\"2001-01-01\",\"attributeName\":\"CCV\"}}");
	    }
	    
	    
}