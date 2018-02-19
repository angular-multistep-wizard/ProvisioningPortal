package com.att.aodp.aodpportal.provisioningportal.pact;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.att.aodp.aodpportal.provisioningportal.pact.ITConsumerServicePact;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	ITConsumerServicePact.class
	//NewConsumerServicePact.class
	//UserAccountConsumerServicePact.class
	//UverseproviderPact.class
	//FailConsumerServicePact.class
	
})
public class PactTestSuit {
}