package com.xenat.app.rest.server.v1;

import com.github.bordertech.didums.Didums;
import com.github.bordertech.restfriends.exception.RestBusinessException;
import com.xenat.app.exception.ServiceException;
import com.xenat.app.rest.api.v1.ListServicesResource;
import com.xenat.app.service.list.ListResponse;
import com.xenat.app.service.list.ListServices;

/**
 * Xenat REST Resource call a backing service implementation.
 */
@SuppressWarnings("NoWhitespaceBefore")
public class ListServicesResourceImpl implements ListServicesResource {

	private final ListServices backing = Didums.getService(ListServices.class);

//	@Override TODO alternate the exception response here?
//	public DocumentContentResponse retrieveDocument(final String documentId)
//			throws RestBusinessException {
//		try {
//			DocumentContent resp = backing.retrieveDocument(documentId);
//			return new DocumentContentResponse(resp);
//		} catch (ServiceException | DocumentNotFoundException e) { TODO alternate the exception response here?
//			throw new RestBusinessException(e.getMessage(), e);
//		}
//	}

	@Override
	public ListResponse retrieveListDetails(String module, String dbid) throws RestBusinessException {
		try {
			return backing.retrieveListDetails(module, dbid);
		} catch (ServiceException e) {
			throw new RestBusinessException(e.getMessage(), e);
		}
	}
}
