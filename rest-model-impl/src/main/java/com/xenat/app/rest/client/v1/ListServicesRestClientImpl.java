package com.xenat.app.rest.client.v1;

import com.xenat.app.exception.ServiceException;
import com.xenat.app.rest.client.jersey.v1.invoker.ApiException;
import com.xenat.app.service.list.ListResponse;
import com.xenat.app.service.list.ListServices;

/**
 * Service implementation that calls Swagger generated REST Client.
 */
public class ListServicesRestClientImpl extends AbstractRestClient implements ListServices {

	@Override
	public ListResponse retrieveListDetails(String module, String dbid) throws ServiceException {
		try {
			return getApi().retrieveListDetails(module, dbid);
		} catch (ApiException e) {
			throw handleException(e);
		}
	}
}
