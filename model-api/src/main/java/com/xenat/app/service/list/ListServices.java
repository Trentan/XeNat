package com.xenat.app.service.list;

import com.xenat.app.exception.ServiceException;

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
		ListResponse retrieveListDetails(String module, String dbid) throws ServiceException;
}
