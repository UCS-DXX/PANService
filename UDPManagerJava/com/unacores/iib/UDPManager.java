package com.unacores.iib;

import java.util.Properties;

import com.ibm.broker.config.proxy.BrokerProxy;
import com.ibm.broker.config.proxy.ConfigManagerProxyLoggedException;
import com.ibm.broker.config.proxy.ConfigManagerProxyPropertyNotInitializedException;
import com.ibm.broker.config.proxy.ConfigurableService;

public class UDPManager {

	private static BrokerProxy b;
	private static Object mutex = new Object();

	private static void initialize() {
		if (null != b) {
			return;
		}
		try {
			synchronized (mutex) {
				b = BrokerProxy.getLocalInstance();
				while (!b.hasBeenPopulatedByBroker()) {
					Thread.sleep(100);
				}
			}
		} catch (ConfigManagerProxyLoggedException e) {
			;
		} catch (InterruptedException e) {
			;
		}
	}

	public static String getProperty(String ServiceName, String propertyName) {
		ConfigurableService service;

		try {
			initialize();
			service = b.getConfigurableService("UserDefined", ServiceName);
			Properties properties = service.getProperties();
			return properties.getProperty(propertyName);

		} catch (ConfigManagerProxyPropertyNotInitializedException e) {
			return null; // no service by that name
		}
	}

	public static String getBrokerUUID() {
		initialize();
		return b.getUUID();
	}
	
}