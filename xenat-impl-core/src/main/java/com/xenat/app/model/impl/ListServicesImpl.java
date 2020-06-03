package com.xenat.app.model.impl;

import com.xenat.app.model.impl.util.BrokerUtil;
import com.xenat.app.model.impl.util.Constants;
import com.xenat.app.model.list.ListCriteria;
import com.xenat.app.model.list.ListDetail;
import com.xenat.app.service.XenatBusinessException;
import com.xenat.app.service.list.ListResponse;
import com.xenat.app.service.list.ListServices;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import rpc.XenatList;

import java.util.ArrayList;

/**
 * Implementation of Xenat List services calling an Entirex RPC service for the data.
 */
public class ListServicesImpl extends AbstractRpcService implements ListServices {

	/**
	 * The logger instance for this class.
	 */
	private static final Log LOG = LogFactory.getLog(ListServicesImpl.class);

	@Override
	protected String getAuditLogName() {
		return "LIST";
	}

	@Override
	public ListResponse retrieveListDetails(ListCriteria listCriteria) throws XenatBusinessException {
		String natModule = "Wswhis02";
		String action = "List Results";
		String recordType = "LIST-RESULTS";

		XenatList req = null;
		try {
			// Setup service
			req = BrokerUtil.createListRPC();
			XenatList.Wswhis02_criteria criteria = req.new Wswhis02_criteria();
			criteria.set_module_from(listCriteria.getModuleFrom());
			criteria.set_module_to(listCriteria.getModuleTo());
			criteria.set_src_from_ts(listCriteria.getSourceFromTs());
			criteria.set_src_to_ts(listCriteria.getSourceToTs());
			if (listCriteria.getLibrary() != null && !listCriteria.getLibrary().isEmpty()) {
				String[] libs = listCriteria.getLibrary().toArray(new String[0]);
				criteria.set_lib(libs);
			}
			if (listCriteria.getUserId() != null && !listCriteria.getUserId().isEmpty()) {
				String[] users = listCriteria.getUserId().toArray(new String[0]);
				criteria.set_user_id(users);
			}

			// Do Search
//		ProfilePoint profile = new ProfilePoint(ProfilePoint.PC_PROCESS_REQUEST, "wswhis01");
//		profile.setAttribute("search", search); TODO Add profile points for service times
			try {
				// Call the service with the populated criteria
//			listService.wswhis02("", search); TODO Add PTID service tracking in MF
				req.wswhis02("", criteria);
			} catch (Exception e) {
//			profile.setException(e);
				LOG.error("an error occurred for list module information", e);
				throw e;
			}
//		finally{  TODO Add profile points for service times
//			profile.stop();
//		}
			BrokerUtil.logoff(req);

			ListResponse result = new ListResponse();

			// Check messages
			BrokerUtil.checkApiMessages(result, "", natModule, req); // TODO Add PTID when made

			// Extract details
			result.setData(extractModules(req.getWswhis02_searchresult_arr()));

			// Audit
			handleAudit(action, recordType, criteria, Constants.AUDIT_OUTCOME_SUCCESS);
			return result;

		} catch (Exception e) {
			throw handleException(action, recordType, listCriteria, e);
		} finally {
			BrokerUtil.disconnect(req);
		}
	}

	private ArrayList<ListDetail> extractModules(XenatList.Wswhis02_searchresult_arr[] wswhis02_searchresult_arr) {
		ArrayList<ListDetail> list = new ArrayList<>();
		for (XenatList.Wswhis02_searchresult_arr module : wswhis02_searchresult_arr) {
			ListDetail detail = new ListDetail();

			detail.setLibrary(BrokerUtil.trimStringValue(module.getLib()));
			detail.setObjDateTime(BrokerUtil.checkDate(module.getOut_cat_date_time()));
			detail.setObject(BrokerUtil.trimStringValue(module.getList_obj()));
			detail.setObjGdaName(BrokerUtil.trimStringValue(module.getOut_cat_gda_name()));
			detail.setObjSize(BrokerUtil.convertNumberToInteger(module.getOut_cat_bp_size()));
			detail.setObjType(BrokerUtil.trimStringValue(module.getList_type_mod()));
			detail.setObjUser(BrokerUtil.trimStringValue(module.getOut_cat_user()));
			detail.setObjVersion(BrokerUtil.trimStringValue(module.getOut_cat_version()));
			detail.setSrcDateTime(BrokerUtil.checkDate(module.getOut_src_date_time()));
			detail.setSrcProgMode(BrokerUtil.trimStringValue(module.getOut_src_prog_mode()));
			detail.setSrcSize(BrokerUtil.convertNumberToInteger(module.getOut_src_size()));
			detail.setSrcType(BrokerUtil.trimStringValue(module.getList_type_src()));
			detail.setSrcUser(BrokerUtil.trimStringValue(module.getOut_src_user()));
			detail.setSrcVersion(BrokerUtil.trimStringValue(module.getOut_src_version()));

			list.add(detail);
		}
		return list;
	}
}
