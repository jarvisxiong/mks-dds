package com.mks.dpl.jmx;

import javax.management.ObjectName;

import com.mks.dpl.plugin.PlugIn;
import com.mks.dpl.service.Service;
import com.mks.jmx.MBeanExportSupport;


public class DplMBeanExport extends MBeanExportSupport {

	private static final String DPL_MBEAN_DOMAIN = "0.dynamic.plugin.library";

	public void registerPlugInMBean(PlugIn plugIn) {
		try {
			PlugInMBean plugInMBean = new PlugInMBean(plugIn);
			ObjectName plugInObjectName = new ObjectName(DPL_MBEAN_DOMAIN + ":type="
					+ plugIn.getKey() + ",name=plugin");
			register(plugInMBean, plugInObjectName);
		} catch (Exception e) {
			throw new DplMBeanOperationException("registerPlugInMBean:", e);
		}
	}

	public void registerServiceMBean(PlugIn plugIn, Service service) {
		if (null == plugIn)
			throw new DplMBeanOperationException(new NullPointerException("plugIn"));
		if (null == service)
			throw new DplMBeanOperationException(new NullPointerException("service"));
		try {
			ObjectName serviceObjectName = new ObjectName(DPL_MBEAN_DOMAIN + ":type="
					+ plugIn.getKey() + ",name=serivice_" + service.getKey());
			register(new ServiceMBean(service), serviceObjectName);

			ServiceStatisticsMBean serviceStatisticsMBean = new ServiceStatisticsMBean(
					service.getStatistics());
			ObjectName serviceStatisticsObjectName = new ObjectName(DPL_MBEAN_DOMAIN + ":type="
					+ plugIn.getKey() + "_statistics,name=serivice_" + service.getKey());
			register(serviceStatisticsMBean, serviceStatisticsObjectName);
		} catch (Exception e) {
			throw new DplMBeanOperationException("registerServiceMBean:", e);
		}
	}
}
