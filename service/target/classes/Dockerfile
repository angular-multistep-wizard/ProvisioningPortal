#FROM zlp11313.vci.att.com:5100/com.att.api.framework/anapsix/alpine-java:jre8
#FROM anapsix/alpine-java:jre8
#FROM  zlp11313.vci.att.com:5100/com.att.ajsc.public/anapsix/alpine-java:8_jdk
#FROM dockercentral.it.att.com:5100/com.att.ajsc.public/anapsix/alpine-java:jre8
FROM dockercentral.it.att.com:5100/com.att.ajsc.public/anapsix/alpine-java:8_jdk

ENV AJSC_HOME=/opt/att/ajsc
ENV AJSC_CONFIG_HOME=${AJSC_HOME}/config


COPY startService.sh /startService.sh
RUN chmod 777 /startService.sh && dos2unix /startService.sh

ADD ProvisioningPortal-service-1.0-distribution.tar.gz app.jar
#ADD ProvisioningPortal-service-1.0-distribution.tar.gz ${AJSC_HOME}

ADD prometheus-java-agent.jar monitoring.jar
ADD ilib.jar ilib.jar

RUN (cd app.jar/ProvisioningPortal-service-1.0;tar -cf - src) | (cd ../..;tar -xf -) \
&& (cd app.jar/ProvisioningPortal-service-1.0;tar -cf - opt/att/ajsc/lib) | (cd ../..;tar -xf -) \
&& (cd app.jar/ProvisioningPortal-service-1.0;tar -cf - opt/att/ajsc/config) | (cd ../..;tar -xf -)
RUN (mv monitoring.jar opt/att/ajsc/lib)
RUN (mv ilib.jar opt/att/ajsc/lib)
RUN (rm -rf app.jar)

ENTRYPOINT /startService.sh