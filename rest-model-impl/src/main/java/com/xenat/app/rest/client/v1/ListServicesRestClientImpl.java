package com.xenat.app.rest.client.v1;

import com.xenat.app.model.list.ListCriteria;
import com.xenat.app.rest.client.jersey.v1.invoker.ApiException;
import com.xenat.app.rest.client.util.RestAdapterUtil;
import com.xenat.app.service.XenatBusinessException;
import com.xenat.app.service.list.ListResponse;
import com.xenat.app.service.list.ListServices;

/**
 * Service implementation that calls Swagger generated REST Client.
 */
public class ListServicesRestClientImpl extends AbstractRestClient implements ListServices {

	@Override
	public ListResponse retrieveListDetails(ListCriteria listCriteria) throws XenatBusinessException {
		try {
			return getApi().retrieveListDetails(
					listCriteria.getModuleFrom(),
					listCriteria.getModuleTo(),
					RestAdapterUtil.convertDateToLocalDate(listCriteria.getSourceFromTs()),
					RestAdapterUtil.convertDateToLocalDate(listCriteria.getSourceToTs()),
					listCriteria.getLibrary(),
					listCriteria.getUserId());

		} catch (ApiException e) {
			throw handleException(e);
		}
	}
}
