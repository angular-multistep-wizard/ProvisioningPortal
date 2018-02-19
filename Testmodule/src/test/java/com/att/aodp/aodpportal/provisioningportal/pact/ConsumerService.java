package com.att.aodp.aodpportal.provisioningportal.pact;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
/*Used for the PACT Contract verification only*/
public class ConsumerService {

	private String url;
	private RestTemplate restTemplate;
	public Object getcsiMsg;
	public Object getUverseMsg(){
		
			Map<String, Object> parameters = new HashMap<String,Object>();
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type","application/json; charset=UTF-8");
			HttpEntity<Map<String,Object>> entity = new HttpEntity<Map<String, Object>>(parameters, headers);	
			return restTemplate.exchange(url+ "/csi-cc/v1/uverseAccountProfile/accounts? accountNumber=181789541&assetType=IRD&assetStatus=Active&serviceCode= DTVM,ATTDV,DVPP,CONT,CCCI,SPP,CVOIP,DTV,FL,HSIA,IP,IPCP,MPP,RG,ROUTER,STB,SPP,VBP,VOIPCP,WSBAN,CWSBAN,CWSCTN,WS,DTVCP,QUCOM,UCONT&serviceStatus=AC",HttpMethod.GET, entity, String.class).getBody();
	
	}

	@Autowired
	public ConsumerService(@Value("${producer}") String url) {
		this.url = url;
		this.restTemplate = new RestTemplate();
	}

	public Object getWelcomeMsg() {
		return restTemplate.exchange(url+ "/web/service/hello?name=User",HttpMethod.GET, null, String.class).getBody();
	}
	public Object getcsiMsg() {
		Map<String, Object> parameters = new HashMap<String,Object>();
		parameters.put("technologies", "[\"Ciena 6500 SDN\"]");
		//String request_body = "{ \"technologies\": [\"Ciena 6500 SDN\"] }";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type","application/json; charset=UTF-8");
		headers.add("Authorization", "Basic Y3NpdGVzdDp0ZXN0aW5nY3Np");
				HttpEntity<Map<String,Object>> entity = new HttpEntity<Map<String, Object>>(parameters, headers);	
				
				
		return restTemplate.exchange(url+ "/Services/rest/csi-bobpm/v503/sdncapacityplanning/nodes/query",HttpMethod.POST, entity, String.class).getBody();
	}
	@RequestMapping(headers = "401")
	public Object geterrorcsiMsg() {
		
		
		Map<String, Object> parameters = new HashMap<String,Object>();
		parameters.put("technologies", "[\"Ciena 6500 SDN\"]");
		//String request_body = "{ \"technologies\": [\"Ciena 6500 SDN\"] }";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type","application/json; charset=UTF-8");
		headers.add("Authorization", "Basic Y3NpdGVzdDp0ZXN0aW5nY3Np");
		headers.add("status", "401");
				HttpEntity<Map<String,Object>> entity = new HttpEntity<Map<String, Object>>(parameters, headers);	
				
				
		return restTemplate.exchange(url+ "/Services/rest/csi-bobpm/v503/sdncapacityplanning/nodes/query",HttpMethod.POST, entity, String.class).getBody();
		
	
	}
	
	public Object getUserAccount() {
		Map<String, Object> parameters = new HashMap<String,Object>();
		HttpHeaders headers = new HttpHeaders();
		headers.add("accept","application/json");
		headers.add("Host", "api.att.com");
		headers.add("Authorization", "Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==");
	/*	headers.add("X-ATT-MessageId", "97ffa-6c80-11e4-b116-123b93f75cba");
		headers.add("X-ATT-TransactionId", "abcdefgh");*/
		HttpEntity<Map<String,Object>> entity = new HttpEntity<Map<String, Object>>(parameters, headers);	
		return restTemplate.exchange(url+ "/csi-cc/v1/uverseAccountProfile/accounts? accountNumber=181789541&assetType=IRD&assetStatus=Active&serviceCode= DTVM,ATTDV,DVPP,CONT,CCCI,SPP,CVOIP,DTV,FL,HSIA,IP,IPCP,MPP,RG,ROUTER,STB,SPP,VBP,VOIPCP,WSBAN,CWSBAN,CWSCTN,WS,DTVCP,QUCOM,UCONT&serviceStatus=AC",HttpMethod.GET, entity, String.class).getBody();
	}

	public Object getAuthMsg() {
		return restTemplate.exchange(url+ "/csi-cc/v1/uverseAccountProfile/accounts?accountNumber=181789541&assetType=IRD&assetStatus=Active&serviceCode=DTVM,ATTDV,DVPP,CONT,CCCI,SPP,CVOIP,DTV,FL,HSIA,IP,IPCP,MPP,RG,ROUTER,STB,SPP,VBP,VOIPCP,WSBAN,CWSBAN,CWSCTN,WS,DTVCP,QUCOM,UCONT&serviceStatus=AC&summary=true",HttpMethod.GET, null, String.class).getBody();
	}
	


}