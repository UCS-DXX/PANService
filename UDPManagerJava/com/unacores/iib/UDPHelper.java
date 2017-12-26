package com.unacores.iib;


public class UDPHelper {

	/**
	 * Sample method that can be called from a Mapping Custom Java transform.
	 * The content of this method provides the implementation for the Custom Java transform.
	 */
	public static java.lang.Object getProperty(String serviceName, String propertyName) {
		return UDPManager.getProperty(serviceName, propertyName);
	}
	
	public static java.lang.Object getBrokerUUID() {
		return UDPManager.getBrokerUUID();
	}

}
