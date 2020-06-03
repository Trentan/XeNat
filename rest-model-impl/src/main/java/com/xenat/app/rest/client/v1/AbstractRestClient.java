package com.xenat.app.rest.client.v1;

import com.xenat.app.rest.client.jersey.v1.api.ListServicesApi;
import com.xenat.app.rest.client.jersey.v1.invoker.ApiException;
import com.xenat.app.rest.client.v1.helper.DefaultApi;
import com.xenat.app.service.XenatSystemException;

/**
 * Provide the default Swagger API implementation.
 */
public abstract class AbstractRestClient {

	/**
	 * @return the Swagger API
	 */
	protected ListServicesApi getApi() {
		return new DefaultApi();
	}

	/**
	 * Handle the API exception.
	 *
	 * @param e the API exception
	 * @return the system exception
	 */
	protected XenatSystemException handleException(final ApiException e) {
		return new XenatSystemException(e.getMessage(), e);
	}

}
