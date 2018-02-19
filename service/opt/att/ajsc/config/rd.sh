#!/bin/bash
 
docker run -ti --rm \
    -p 8080:8080 \
    -e AJSC_CONFIGMAP_MOUNT_PATH=/opt/att/ajsc/config \
	-e AJSC_CONFIG_HOME=/opt/att/ajsc/config \
	-e BUNDLEVERSION=1.0 -e STICKYSELECTORKEY= -e ENVCONTEXT=DEV \
    -e AFTENVIRONMENT="AFTUAT" -e AFTLONGITUDE="38.62" -e AFTLATITUDE="-90.19" \
    -e DiscoveryURL="aftdsc://38.62/-90.19/AFTUAT?service=dmeQCF,version=1.0,bindingType=jms,envContext=U" \   
    dockercentral.it.att.com:5100/com.att.aodp/provisioningportal
    
