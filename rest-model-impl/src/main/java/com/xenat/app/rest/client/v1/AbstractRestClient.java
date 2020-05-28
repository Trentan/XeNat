package com.xenat.app.rest.client.v1;

import com.xenat.app.exception.ServiceException;
import com.xenat.app.rest.client.jersey.v1.api.ListServicesApi;
import com.xenat.app.rest.client.jersey.v1.invoker.ApiException;
import com.xenat.app.rest.client.v1.helper.DefaultApi;

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
	protected ServiceException handleException(final ApiException e) {
		return new ServiceException(e.getMessage(), e);
	}

}
