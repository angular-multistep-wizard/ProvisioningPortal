#!/bin/sh

nodeName=ProvisioningPortal_1.0_$(cat /proc/self/cgroup | grep docker | sed s/\\//\\n/g | tail -1)

export AJSC_HOME=/opt/att/ajsc
export AJSC_CONFIG_HOME=${AJSC_HOME}/config
#source /etc/run2.source
source ${AJSC_CONFIG_HOME}/run.source
