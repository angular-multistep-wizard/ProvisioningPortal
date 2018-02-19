package com.att.aodp.aodpportal.provisioningportal.pact;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
/*Used for the PACT Contract verification only*/
public class HelloConsumerService {

	private String url;
	private RestTemplate restTemplate;

	@Autowired
	public HelloConsumerService(@Value("${producer}") String url) {
		this.url = url;
		this.restTemplate = new RestTemplate();
	}

	public Object getQuickHello() {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("X-ATT-ServiceName", "helloworld");
		headers.add("Authorization", "Basic Y3NpdGVzdDp0ZXN0aW5nY3Np");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		
		return restTemplate.exchange(url+ "/restservices/helloworld/v1/service/hello",HttpMethod.GET, entity, String.class).getBody();
	}
}