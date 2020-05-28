package com.xenat.app.service.list;

import com.xenat.app.model.list.ListDetail;
import com.xenat.app.service.AbstractServiceMessagesResponse;
import com.xenat.app.service.ServiceDataListResponse;

import java.util.ArrayList;

/**
 * Retrieve List detail response.
 * @author Trentan Healey
 * @since POC1
 */
public class ListResponse extends AbstractServiceMessagesResponse implements ServiceDataListResponse<ListDetail> {

	private ArrayList<ListDetail> data;

	/**
	 * Default constructor.
	 */
	public ListResponse() {
		// Do nothing
	}

	/**
	 * @param entries the List Detail entries
	 */
	public ListResponse(final ArrayList<ListDetail> entries) {
		this.data = entries;
	}

	@Override
	public ArrayList<ListDetail> getData() {
		if (data == null) {
			data = new ArrayList<>();
		}
		return data;
	}

	@Override
	public void setData(final ArrayList<ListDetail> data) {
		this.data = data;
	}
}
