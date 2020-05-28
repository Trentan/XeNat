package com.xenat.app.rest.client.v1.helper;

import com.xenat.app.rest.client.jersey.v1.api.ListServicesApi;

/**
 * Default API invoker.
 */
public class DefaultApi extends ListServicesApi {

	/**
	 * Default constructor.
	 */
	public DefaultApi() {
		super(new DefaultApiClient());
	}
}
