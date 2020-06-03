package com.xenat.app.rest.server.v1;

import com.github.bordertech.didums.Didums;
import com.github.bordertech.restfriends.exception.RestBusinessException;
import com.xenat.app.model.list.ListCriteria;
import com.xenat.app.model.util.DateAdapterUtil;
import com.xenat.app.rest.api.v1.ListServicesResource;
import com.xenat.app.service.list.ListResponse;
import com.xenat.app.service.list.ListServices;

import java.util.List;

/**
 * Xenat REST Resource call a backing service implementation.
 */
@SuppressWarnings("NoWhitespaceBefore")
public class ListServicesResourceImpl implements ListServicesResource {

	private final ListServices backing = Didums.getService(ListServices.class);

	@Override
	public ListResponse retrieveListDetails(String moduleFrom, String moduleTo, String dateFrom, String dateTo, List<String> libs, List<String> users) throws RestBusinessException {
		ListCriteria listCriteria = new ListCriteria();
		listCriteria.setModuleFrom(moduleFrom);
		listCriteria.setModuleTo(moduleTo);
		listCriteria.setSourceFromTs(DateAdapterUtil.parseStringToDate(dateFrom));
		listCriteria.setSourceToTs(DateAdapterUtil.parseStringToDate(dateTo));
		listCriteria.setLibrary(libs);
		listCriteria.setUserId(users);
		return backing.retrieveListDetails(listCriteria);
	}
}
