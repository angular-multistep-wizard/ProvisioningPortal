				import java.util.Properties;
				import com.att.aft.dme2.api.DME2Endpoint;
				import com.att.aft.dme2.iterator.DME2EndpointIterator;
				import com.att.aft.dme2.iterator.DME2EndpointIteratorFactory;
				import com.att.aft.dme2.iterator.DME2EndpointReference;
				import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
				import org.jasypt.properties.EncryptableProperties;
				public class DMELookupService {	
				 String brokerEndPointURL = null;
				 String serviceURL=null;
				 String userName=null;
				 String passWord=null;
				 String brokerProxyEndPointURL = null;
				public  String getServiceURL(){
					return serviceURL;
				}
				public  String getUserName(){
					return userName;
				}
				public  String getPassword(){
					return passWord;
				}
				public  String getBrokerEndPointURL(){
					return brokerEndPointURL;
				}
				public  String getBrokerProxyEndPointURL(){
					return brokerProxyEndPointURL;
				}
				public void getServiceLookup(String serviceURI, boolean isProxyEnabled)  {
				// Returning an instance of the DME2EndpointIteratorFactory
				try{
				Properties prop = new Properties();
				InputStream input = new FileInputStream("Testmodule" + File.separator +"src" + File.separator + "test" + File.separator + "resources" + File.separator + "application-test.properties");
				prop.load(input);
				String key = prop.getProperty("pactBroker.key");
				StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
				encryptor.setPassword(key); // could be got from web, env variable...
				input = new FileInputStream("Testmodule" + File.separator +"src" + File.separator + "test" + File.separator + "resources" + File.separator + "application-test.properties");
				prop = new EncryptableProperties(encryptor);
				prop.load(input);
				userName = prop.getProperty("pactBroker.user");
				passWord = prop.getProperty("pactBroker.password");
				Properties props = System.getProperties();

				props.setProperty("com.att.aft.dme.DiscoveryURL", prop.getProperty("com.att.aft.dme.DiscoveryURL"));
				props.setProperty("DME2_EP_REGISTRY_CLASS", prop.getProperty("DME2_EP_REGISTRY_CLASS"));
				props.setProperty("AFT_ENVIRONMENT", prop.getProperty("AFT_ENVIRONMENT"));
				props.setProperty("AFT_LATITUDE", prop.getProperty("AFT_LATITUDE"));
				props.setProperty("AFT_LONGITUDE", prop.getProperty("AFT_LONGITUDE"));
				props.setProperty("AFT_DME2_HTTP_EXCHANGE_TRACE_ON", prop.getProperty("AFT_DME2_HTTP_EXCHANGE_TRACE_ON"));
				props.setProperty("DME2.DEBUG", prop.getProperty("DME2.DEBUG"));

				DME2EndpointIteratorFactory iterFactory = DME2EndpointIteratorFactory.getInstance();
				// Creating the Iterator
	
				// Beginning iteration
				if (serviceURI != null) {
					DME2EndpointIterator iterator = (DME2EndpointIterator) iterFactory.getIterator(serviceURI, null, null);
					while (iterator.hasNext()) {
						DME2EndpointReference ref = iterator.next();
						DME2Endpoint endpoint = ref.getEndpoint();
						serviceURL = endpoint != null ? endpoint.toURLString() : "";
						break;
					}
				}
				if (isProxyEnabled) {
					DME2EndpointIterator iterator = (DME2EndpointIterator) iterFactory.getIterator(System.getProperty("PACT_BROKER_DME2_URI"), null, null);
					// Beginning iteration
					while (iterator.hasNext()) {
						DME2EndpointReference ref = iterator.next();
						DME2Endpoint endpoint = ref.getEndpoint();
						brokerEndPointURL = endpoint != null ? endpoint.toURLString() : "";
						break;
					}
					iterator = (DME2EndpointIterator) iterFactory.getIterator(prop.getProperty("pactBrokerProxy.url"), null,null);
					// Beginning iteration
					while (iterator.hasNext()) {
						DME2EndpointReference ref = iterator.next();
						DME2Endpoint endpoint = ref.getEndpoint();
						brokerProxyEndPointURL = endpoint != null ? endpoint.toURLString() : "";
						break;
					}
				} else {

						
						DME2EndpointIterator iterator = (DME2EndpointIterator) iterFactory.getIterator(System.getProperty("PACT_BROKER_DME2_URI"), null, null);
						// Beginning iteration
						while (iterator.hasNext()) {
							DME2EndpointReference ref = iterator.next();
							DME2Endpoint endpoint = ref.getEndpoint();
							brokerEndPointURL = endpoint != null ? endpoint.toURLString() : "";
							break;
						}

				}
				}catch(Exception er){
					
				}

			}

		}	 	
				
				 String demoLookupURI =System.getProperty('DMELOOKUP') != null?System.getProperty('DMELOOKUP'):null ;	
				
				 DMELookupService dmeLookupService = new DMELookupService();
				 dmeLookupService.getServiceLookup(demoLookupURI, System.getProperty('PROXY_FLAG').toBoolean());
				
				 if(demoLookupURI != null){				 	
				 	URL url = new URL(dmeLookupService.getServiceURL());	
				  	println("Provider Port " + url.toString());
					String host = url.getHost();
				 	String port = url.getPort();						
				 	project.properties.setProperty('APP_URL',host);
					project.properties.setProperty('APP_PORT',port);
				    println("provider Port " + project.properties['APP_URL']);
				    println("Pactprovider Host " + project.properties['APP_PORT']);
				 }
				
				  	 String ATTUserName =System.getProperty('USERNAME') != null?System.getProperty('USERNAME'):'';
				 	 String ATTPassword =System.getProperty('PASSWORD')!=null?System.getProperty('PASSWORD'):'';	
				 	 String brokerProxy=dmeLookupService.getBrokerProxyEndPointURL() != null?dmeLookupService.getBrokerProxyEndPointURL():'';			 	
				 	 project.properties.setProperty('BROKER_PROXY_URL',brokerProxy);
				 	 String userName=dmeLookupService.getUserName() != null?dmeLookupService.getUserName():'';
				 	 project.properties.setProperty('userName',userName);
				 	 String passWord=dmeLookupService.getPassword() != null?dmeLookupService.getPassword():'';
				     project.properties.setProperty('passWord',passWord);
				     String brokerURL=dmeLookupService.getBrokerEndPointURL()!= null?dmeLookupService.getBrokerEndPointURL():'';
				     project.properties.setProperty('BROKER_URL',brokerURL);
				     
				 	 project.properties.setProperty('attuserName',ATTUserName);
					 project.properties.setProperty('attpassword',ATTPassword);
					 String version=System.getProperty('PACT_VERSION')!= null?System.getProperty('PACT_VERSION'):'';
					 project.properties.setProperty('PACT_VERSION',version);
					// println("PactBrokerProxy url " + project.properties['BROKER_PROXY_URL']);
					 println("PactBroker url " + project.properties['BROKER_URL']);
					// println("ATTUserName " + project.properties['attuserName']);
					// println("ATTPassword " + project.properties['attpassword']);
					 println("userName " + project.properties['userName']);
				     println("password " + project.properties['passWord']);
					 println("pact version " + project.properties['PACT_VERSION']);
					  def url = "curl -v -u \""+project.properties['userName']+":"+project.properties['passWord']+"\" -XPOST -H \"Content-Type: application/json\" -d  \"{\\\"name\\\":\\\""+System.getProperty('ARTIFACT_NAME')+"\\\"}\" "+project.properties['BROKER_URL']+"pacticipants";
					 def check_url = "curl -I -v -u \""+project.properties['userName']+":"+project.properties['passWord']+"\" -XGET -H \"Content-Type: application/json\" "+project.properties['BROKER_URL']+"pacticipants/"+System.getProperty('ARTIFACT_NAME');
					 println("check_url:::"+check_url);
					  println("url:::"+url);
        			 String response= executeCurlCommand(check_url);
        			 if(response.contains("200")){
        				 println('Pacticipant already registered!!!');
        			 }else{
        			 	println(executeCurlCommand(url));
        			 }
					 def static executeCurlCommand(URL){
         
       				 def url = URL;
        			 def proc = url.execute();
        			 def outputStream = new StringBuffer();
       				 proc.waitForProcessOutput(outputStream, System.err)
        			 return outputStream.toString();
        			 }
					
					
				
				
				