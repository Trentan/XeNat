package com.xenat.app.model.impl.mock;

import com.xenat.app.model.list.ListCriteria;
import com.xenat.app.model.list.ListDetail;
import com.xenat.app.service.list.ListResponse;
import com.xenat.app.service.list.ListServices;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Mock Client Services.
 */
@SuppressWarnings({"BED_HIERARCHICAL_EXCEPTION_DECLARATION", "PMB_POSSIBLE_MEMORY_BLOAT"})
public class ListServicesMockImpl implements ListServices {

	private static final ArrayList<ListDetail> MOCK_DETAILS = new ArrayList<>();

	static {
		Calendar cal = Calendar.getInstance();
		int idx = 0;
		for (int i = 0; i < 9; i++) {
			idx++;
			MOCK_DETAILS.add(createDetail(cal, idx, "WSTEST", "001"));
		}
	}

	private static ListDetail createDetail(Calendar cal, int idx, String module, String vers) {
		ListDetail item = new ListDetail();
		item.setLibrary("PR" + 0 + idx);
		item.setObject(module + idx);

		cal.add(Calendar.MONTH, 1);
		item.setSrcDateTime(cal.getTime());
		item.setSrcProgMode("S");
		item.setSrcSize(100 + idx);
		item.setSrcType("P");
		item.setSrcVersion(vers + "." + idx);
		item.setSrcUser("TEST0" + idx);

		item.setObjDateTime(cal.getTime());
		item.setObjGdaName("GDA" + idx);
		item.setObjSize(100 + idx);
		item.setObjType("P");
		item.setObjVersion(vers + "." + idx);
		item.setObjUser("TEST0" + idx);
		return item;
	}

	@Override
	public ListResponse retrieveListDetails(ListCriteria listCriteria) {
		ListResponse resp = new ListResponse();
		if (listCriteria.getModuleFrom().equals("*")) {
			resp.setData(MOCK_DETAILS);
		} else {
			ListDetail newDetail = createDetail(Calendar.getInstance(), MOCK_DETAILS.size()+1, listCriteria.getModuleFrom(), "9");
			MOCK_DETAILS.add(newDetail);
			resp.setData(new ArrayList<ListDetail>() {{
				add(newDetail);
			}});
		}
		return resp;
	}

}
