package com.att.aodp.aodpportal.provisioningportal.pact;

import static junit.framework.TestCase.assertEquals;
import java.util.HashMap;
import java.util.Map;
import org.junit.Rule;
import org.junit.Test;
import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRule;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.PactFragment;
import au.com.dius.pact.model.PactSpecVersion;
import com.att.aodp.aodpportal.provisioningportal.pact.ConsumerService;

public class UverseproviderPact {
	@Rule
	public PactProviderRule rule = new PactProviderRule("uverseprovider", PactSpecVersion.V3, this);

	@Pact(provider = "uverseprovider", consumer = "demo812017")
	public PactFragment createFragment(PactDslWithProvider builder) {

		Map<String, String> request_headers = new HashMap<>();
		//request_headers.put("Host", "api.att.com");

		/*request_headers.put("accept", "application/json");
		request_headers.put("Authorization", "Bearer $access_token");*/
		Map<String, String> response_headers = new HashMap<>();
		response_headers.put("status", "200");
		response_headers.put("X-ATT-ConversationId", "client1~CNG-CSI~859cdfd1-d28e-4611-899d-be6ff1aa492c");
		response_headers.put("Content-Type", "application/json");
		response_headers.put("X-ATT-MessageId", "97ffa-6c80-11e4-b116-123b93f75cba");
		response_headers.put("X-ATT-TransactionId", "abcdefgh");

		return builder.given("").uponReceiving("uverse account profile")
				.path("/csi-cc/v1/uverseAccountProfile/accounts").method("GET").headers(request_headers)
				.query("accountNumber=181789541").query("assetStatus=Active").query("assetType=IRD")
				.query("serviceStatus=AC")
				.query("serviceCode=DTVM,ATTDV,DVPP,CONT,CCCI,SPP,CVOIP,DTV,FL,HSIA,IP,IPCP,MPP,RG,ROUTER,STB,SPP,VBP,VOIPCP,WSBAN,CWSBAN,CWSCTN,WS,DTVCP,QUCOM,UCONT")
				.willRespondWith()
				.body("{\"accountDetails\":{\"billingAccountNumber\":\"181789541\",\"accountId\":\" 1483456\",\"accountStatus\":\"O\",\"dealerId\":\"43671\"},\"address\":{\"serviceAddress\":{\"fipsStateCountyCode\":\"75001\",\"countyZipCode\":\"2341\",\"timezone\":\"Eastern\",\"daylightSavingsIndicator\":\"true\"},\"billingAddress\":{\"billingName\":\"Fred Castro\"}},\"billingAndFinancials\":{\"statementDeliveryOption\":\"N\"   },   \"customerName\":{      \"namePrefix\":\"Mr\",      \"firstName\":\"Fred\",      \"middleName\":\"\",      \"lastName\":\"Castro\",      \"nameSuffix\":\"\",      \"additionalTitle\":\"\"  },   \"emailAddress\":{      \"emailAddress\":\"fred.castro@gmail.com\",      \"validEmailAddressIndicator\":\"true\",      \"refusedEmailIndicator\":\"true\"   },   \"languageCode\":\" SPANISH\",   \"smartAttribute\":[      {         \"attributeName\":\"smartDTV1\",         \"effectiveStartDate\":\"2016-03-19\",         \"effectiveEndDate\":\"2017-03-19\"      },      {         \"attributeName\":\"smartDTV1\",         \"effectiveStartDate\":\"2017-03-19\",         \"effectiveEndDate\":\"2017-03-19\"      },      {         \"attributeName\":\"smartDTV3\",         \"effectiveStartDate\":\"2016-03-19\"      }   ],   \"commonCustomerAttribute\":{      \"attributeName\":\" CCV\",      \"attributeValue\":\"attributeValue\",      \"effectiveStartDate\":\"2001-01-01\",      \"effectiveEndDate\":\"2001-01-01\"   },   \"package\":{      \"code\":\"code\",      \"name\":\"name\",      \"description\":\"description\",      \"status\":\"0\",      \"authorizationCodeList\":\"authorizationCodeList\",      \"effectiveEndDate\":\"2001-12-31T12:00:00\",      \"effectiveStartDate\":\"2001-12-31T12:00:00\",      \"lastUpdateDate\":\"2001-12-31T12:00:00\"   },   \"partnerDetails\":{      \"jointBillingStatus\":\"C\"   },   \"asset\":[      {         \"receiverId\":\"1782022839033\",         \"equipmentOwnership\":\"OWN\",         \"location\":\"OFFICE\",         \"manufacturer\":\"Google\",         \"model\":\"Model4\",         \"serialNumber\":\"1782022839034\",         \"memberId\":\"1238997\",         \"assetStatus\":\"ACTV\"      },     {         \"receiverId\":\"1782022839000\",         \"equipmentOwnership\":\"OWN\",        \"location\":\"OFFICE\",         \"manufacturer\":\"Google\",         \"model\":\"Model4\",         \"serialNumber\":\"1782022839068\",         \"memberId\":\"1238993\",        \"assetStatus\":\"ACTV\"      },      {         \"equipmentOwnership\":\"LEASE\",         \"location\":\"OFFICE\",        \"manufacturer\":\"Google\",         \"model\":\"Model34\",         \"serialNumber\":\"2782022839068\",         \"memberId\":\"1238993\",         \"assetStatus\":\"ACTV\"      }   ],   \"service\":{      \"type\":\"DV\",      \"DTVAttributes\":{         \"broadbandCallbackDate\":\"2016-03-19\",         \"oppvRestrictedIndicator\":\"true\",         \"videoServiceStatus\":\"AC\"      }   },   \"productComponentDetails\":{      \"serviceTypeCode\":\"ATTDV\",      \"data\":{         \"code\":\"CONTRACT\",         \"name\":\"name of component code\",         \"pricePlanData\":{            \"chargeData\":{               \"name\":\"name\"            },            \"promSaleType\":\"promSaleType\",            \"effectivedate\":\"2001-01-01\",            \"expirationDate\":\"2001-01-01\",            \"promoDuration\":\"promoDuration\",            \"promoServiceType\":\"promoServiceType\",            \"promoInterval\":\"promoInterval\",            \"promoType\":\"promoType\"         },         \"attribute\":{            \"code\":\"code\",            \"value\":\"value\"         }      },      \"status\":\"status\",      \"state\":\"state\"   }}")
				.toFragment();
	}

	@Test
	@PactVerification(value = "uverseprovider", fragment = "createFragment")
	public void runTest() throws Exception {
		System.out.println("$$$$$$$"+new ConsumerService(rule.getConfig().url()).getUserAccount().toString());
		assertEquals(new ConsumerService(rule.getConfig().url()).getUserAccount().toString(),
				"{\"accountDetails\":{\"billingAccountNumber\":\"181789541\",\"accountId\":\" 1483456\",\"accountStatus\":\"O\",\"dealerId\":\"43671\"},\"address\":{\"serviceAddress\":{\"fipsStateCountyCode\":\"75001\",\"countyZipCode\":\"2341\",\"timezone\":\"Eastern\",\"daylightSavingsIndicator\":\"true\"},\"billingAddress\":{\"billingName\":\"Fred Castro\"}},\"billingAndFinancials\":{\"statementDeliveryOption\":\"N\"   },   \"customerName\":{      \"namePrefix\":\"Mr\",      \"firstName\":\"Fred\",      \"middleName\":\"\",      \"lastName\":\"Castro\",      \"nameSuffix\":\"\",      \"additionalTitle\":\"\"  },   \"emailAddress\":{      \"emailAddress\":\"fred.castro@gmail.com\",      \"validEmailAddressIndicator\":\"true\",      \"refusedEmailIndicator\":\"true\"   },   \"languageCode\":\" SPANISH\",   \"smartAttribute\":[      {         \"attributeName\":\"smartDTV1\",         \"effectiveStartDate\":\"2016-03-19\",         \"effectiveEndDate\":\"2017-03-19\"      },      {         \"attributeName\":\"smartDTV1\",         \"effectiveStartDate\":\"2017-03-19\",         \"effectiveEndDate\":\"2017-03-19\"      },      {         \"attributeName\":\"smartDTV3\",         \"effectiveStartDate\":\"2016-03-19\"      }   ],   \"commonCustomerAttribute\":{      \"attributeName\":\" CCV\",      \"attributeValue\":\"attributeValue\",      \"effectiveStartDate\":\"2001-01-01\",      \"effectiveEndDate\":\"2001-01-01\"   },   \"package\":{      \"code\":\"code\",      \"name\":\"name\",      \"description\":\"description\",      \"status\":\"0\",      \"authorizationCodeList\":\"authorizationCodeList\",      \"effectiveEndDate\":\"2001-12-31T12:00:00\",      \"effectiveStartDate\":\"2001-12-31T12:00:00\",      \"lastUpdateDate\":\"2001-12-31T12:00:00\"   },   \"partnerDetails\":{      \"jointBillingStatus\":\"C\"   },   \"asset\":[      {         \"receiverId\":\"1782022839033\",         \"equipmentOwnership\":\"OWN\",         \"location\":\"OFFICE\",         \"manufacturer\":\"Google\",         \"model\":\"Model4\",         \"serialNumber\":\"1782022839034\",         \"memberId\":\"1238997\",         \"assetStatus\":\"ACTV\"      },     {         \"receiverId\":\"1782022839000\",         \"equipmentOwnership\":\"OWN\",        \"location\":\"OFFICE\",         \"manufacturer\":\"Google\",         \"model\":\"Model4\",         \"serialNumber\":\"1782022839068\",         \"memberId\":\"1238993\",        \"assetStatus\":\"ACTV\"      },      {         \"equipmentOwnership\":\"LEASE\",         \"location\":\"OFFICE\",        \"manufacturer\":\"Google\",         \"model\":\"Model34\",         \"serialNumber\":\"2782022839068\",         \"memberId\":\"1238993\",         \"assetStatus\":\"ACTV\"      }   ],   \"service\":{      \"type\":\"DV\",      \"DTVAttributes\":{         \"broadbandCallbackDate\":\"2016-03-19\",         \"oppvRestrictedIndicator\":\"true\",         \"videoServiceStatus\":\"AC\"      }   },   \"productComponentDetails\":{      \"serviceTypeCode\":\"ATTDV\",      \"data\":{         \"code\":\"CONTRACT\",         \"name\":\"name of component code\",         \"pricePlanData\":{            \"chargeData\":{               \"name\":\"name\"            },            \"promSaleType\":\"promSaleType\",            \"effectivedate\":\"2001-01-01\",            \"expirationDate\":\"2001-01-01\",            \"promoDuration\":\"promoDuration\",            \"promoServiceType\":\"promoServiceType\",            \"promoInterval\":\"promoInterval\",            \"promoType\":\"promoType\"         },         \"attribute\":{            \"code\":\"code\",            \"value\":\"value\"         }      },      \"status\":\"status\",      \"state\":\"state\"   }}");
	}
}
