package com.xenat.app.ui.util;

import com.github.bordertech.didums.Didums;
import com.xenat.app.service.list.ListServices;

import java.io.Serializable;

/**
 * Provide an instance of the client services.
 */
public final class ListServicesHelperFactory implements Serializable {

	/**
	 * Singleton instance.
	 */
	private static final ListServices INSTANCE = Didums.getService(ListServices.class);

	/**
	 * Don't allow external instantiation of this class.
	 */
	private ListServicesHelperFactory() {
		// Do nothing
	}

	/**
	 * @return the singleton instance of the CRM Services.
	 */
	public static ListServices getInstance() {
		return INSTANCE;
	}
}
