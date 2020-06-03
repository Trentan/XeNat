package com.xenat.app.service.list;

import com.xenat.app.exception.ServiceException;
import com.xenat.app.model.list.ListCriteria;
import com.xenat.app.service.XenatBusinessException;

/**
* List services interface.
 * @author Trentan Healey
 * @since POC1
*/
	public interface ListServices {

		/**
		 * Retrieve list of details.
		 *
		 * @return the list of table names
		 * @throws ServiceException a service exception
		 */
		ListResponse retrieveListDetails(ListCriteria listCriteria) throws XenatBusinessException;
}
