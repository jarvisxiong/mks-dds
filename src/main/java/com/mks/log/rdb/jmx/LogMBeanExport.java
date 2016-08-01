package com.mks.log.rdb.jmx;

import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

import com.mks.jmx.MBeanExportSupport;

public class LogMBeanExport extends MBeanExportSupport {

	public void registerLogAppenderMBean(LogAppenderMBean logAppender, String plugInName) {
		try {
			register(logAppender, new ObjectName("0.log:type=" + plugInName + ",name=logAppender"));
		} catch (MalformedObjectNameException e) {
			throw new LogMBeanOperationException("registerLogAppenderMBean:", e);
		} catch (NullPointerException e) {
			throw new LogMBeanOperationException("registerLogAppenderMBean:", e);
		}
	}

	public void registerRecordControllerMBean(RecordControllerMBean recordController,
			String plugInName, Object logInfo) {
		try {
			register(recordController, new ObjectName("0.log:type=" + plugInName
					+ ",name=recordController_" + logInfo.getClass().getSimpleName()));
		} catch (MalformedObjectNameException e) {
			throw new LogMBeanOperationException("registerRecordControllerMBean:", e);
		} catch (NullPointerException e) {
			throw new LogMBeanOperationException("registerRecordControllerMBean:", e);
		}
	}
}
