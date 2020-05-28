import au.gov.immi.Xenat.model.crt.CrtEntryData;
import au.gov.immi.Xenat.model.crt.CrtRelationshipEntryData;
import au.gov.immi.Xenat.rpc.Crt;
import au.gov.immi.Xenat.rpc.Crt.Wscrt01_crt_codes;
import au.gov.immi.Xenat.rpc.Crt.Wscrt01_msg;
import au.gov.immi.Xenat.rpc.Crt.Wscrt02_msg;
import au.gov.immi.Xenat.rpc.Crt.Wscrt02_rel_code;
import au.gov.immi.Xenat.rpc.Crt.Wscrt02_rel_code_rel_arr;
import au.gov.immi.Xenat.service.XenatBusinessException;
import au.gov.immi.Xenat.service.core.util.BrokerUtil;
import au.gov.immi.Xenat.service.core.util.Constants;
import au.gov.immi.Xenat.service.crt.CrtServices;
import au.gov.immi.Xenat.service.crt.RetrieveCodeSetDataResponse;
import au.gov.immi.Xenat.service.crt.RetrieveCrtRelationshipDataResponse;
import au.gov.immi.ebiz.profiler.ProfilePoint;
import au.gov.immi.sfp.util.Util;
import com.softwareag.entirex.aci.Broker;

import java.util.ArrayList;
import java.util.Date;

import static au.gov.immi.Xenat.service.core.HealthCheckServicesImpl.performUserCheck;

/**
 * Implementation of CRT services calling an Entirex RPC service for the data.
 */
public class CrtServicesImpl extends AbstractRpcService implements CrtServices {

	@Override
	public RetrieveCodeSetDataResponse retrieveCodeSet(final String name, final Date date) throws XenatBusinessException {

		// Check name
		if (name == null || name.isEmpty()) {
			throw new XenatBusinessException("CRT code set name must be provided.");

		}

		Crt req = null;
		try {
			performUserCheck();
			// Setup service
			req = createCrtRPC();

			// RPC Call
			ProfilePoint profile = new ProfilePoint(ProfilePoint.PC_PROCESS_REQUEST, "wscrt01");
			profile.setAttribute("name", name);
			if (date != null) {
				profile.setAttribute("dt", date.toString());
			}

			try {
				profile.start();
				req.wscrt01(getPtid(), name, BrokerUtil.convertDateToEntirexDate(date));
			} catch (Exception e) {
				profile.setException(e);
				throw e;
			} finally {
				profile.stop();
			}
			BrokerUtil.logoff(req);

			RetrieveCodeSetDataResponse result = new RetrieveCodeSetDataResponse();

			// Check messages
			if (req.getWscrt01_msg() != null) {
				Wscrt01_msg msg = req.getWscrt01_msg();
				checkMessages(result, msg.get_msg_typ(), msg.get_msg_txt());
			}

			// Extract codes
			Wscrt01_crt_codes[] entries = req.getWscrt01_crt_codes();
			if (entries != null && entries.length > 0) {
				ArrayList<CrtEntryData> data = createCrtList(name, entries);
				result.setData(data);
			}

			// Audit
			handleAudit("Retrieve crt", "crt", name, Constants.AUDIT_OUTCOME_SUCCESS);

			return result;
		} catch (Exception e) {
			throw handleException("Retrieve crt", "crt", name, e);
		} finally {
			BrokerUtil.disconnect(req);
		}
	}

	@Override
	public RetrieveCrtRelationshipDataResponse retrieveCrtRelationship(final String name, final Date date) throws XenatBusinessException {

		// Check name
		if (name == null || name.isEmpty()) {
			throw new XenatBusinessException("CRT Relationship name must be provided.");

		}

		Crt req = null;
		try {
			performUserCheck();
			// Setup service
			req = createCrtRPC();

			// RPC Call
			ProfilePoint profile = new ProfilePoint(ProfilePoint.PC_PROCESS_REQUEST, "wscrt02");
			profile.setAttribute("name", name);
			if (date != null) {
				profile.setAttribute("dt", date);
			}

			try {
				profile.start();
				req.wscrt02(getPtid(), name, BrokerUtil.convertDateToEntirexDate(date));
			} catch (Exception e) {
				profile.setException(e);
				throw e;
			} finally {
				profile.stop();
			}
			BrokerUtil.logoff(req);

			RetrieveCrtRelationshipDataResponse result = new RetrieveCrtRelationshipDataResponse();

			// Check messages
			if (req.getWscrt02_msg() != null) {
				Wscrt02_msg msg = req.getWscrt02_msg();
				checkMessages(result, msg.get_msg_typ(), msg.get_msg_txt());
			}

			// Extract relationships
			Wscrt02_rel_code[] entries = req.getWscrt02_rel_code();
			if (entries != null && entries.length > 0) {
				ArrayList<CrtRelationshipEntryData> data = createCrtRelationshipList(name, entries);
				result.setData(data);
			}

			// Audit
			handleAudit("Retrieve crt relationship", "crtrel", name, Constants.AUDIT_OUTCOME_SUCCESS);

			return result;
		} catch (Exception e) {
			throw handleException("Retrieve crt relationship", "crtrel", name, e);
		} finally {
			BrokerUtil.disconnect(req);
		}
	}

	/**
	 * Convert rpcEntries for the CRT to Xenat CRT entries.
	 *
	 * @param crtName the CRT table name
	 * @param rpcEntries the RPC Xenat CRT entries
	 * @return the Xenat CRT entries
	 */
	private ArrayList<CrtEntryData> createCrtList(final String crtName, final Wscrt01_crt_codes[] rpcEntries) {
		ArrayList<CrtEntryData> entries = new ArrayList<>();
		for (Wscrt01_crt_codes rpcEntry : rpcEntries) {
			CrtEntryData entry = new CrtEntryData();

			entry.setCrtName(crtName);
			entry.setCode(BrokerUtil.trimStringValue(rpcEntry.get_crt_code()));
			entry.setLongDesc(BrokerUtil.trimStringValue(rpcEntry.get_crt_long_desc()));
			entry.setShortDesc(BrokerUtil.trimStringValue(rpcEntry.get_crt_short_desc()));
			entry.setAbbreviation(BrokerUtil.trimStringValue(rpcEntry.get_crt_abbrev()));
			entry.setEffectiveFrom(BrokerUtil.checkDate(rpcEntry.get_crt_st_ts()));
			entry.setEffectiveTo(BrokerUtil.checkDate(rpcEntry.get_crt_end_ts()));

			entries.add(entry);
		}
		return entries;
	}

	/**
	 * Convert rpcEntries for the relationship to Xenat CRT entries.
	 *
	 * @param relName the relationship name
	 * @param rpcEntries the rpc crt entries
	 * @return the Xenat crt entries
	 */
	private ArrayList<CrtRelationshipEntryData> createCrtRelationshipList(final String relName,
			final Wscrt02_rel_code[] rpcEntries) {

		ArrayList<CrtRelationshipEntryData> entries = new ArrayList<>();
		for (Wscrt02_rel_code rpcEntry : rpcEntries) {
			// Check code provided
			if (Util.empty(rpcEntry.get_pri_cd())) {
				continue;
			}

			CrtRelationshipEntryData entry = new CrtRelationshipEntryData();

			entry.setRelationshipName(relName);
			entry.setRelationshipStart(BrokerUtil.checkDate(rpcEntry.get_pri_st_ts()));
			entry.setRelationshipEnd(BrokerUtil.checkDate(rpcEntry.get_pri_end_ts()));

			// Primary
			CrtEntryData primary = new CrtEntryData();
			primary.setCrtName(BrokerUtil.trimStringValue(rpcEntry.get_pri_table_name()));
			primary.setCode(BrokerUtil.trimStringValue(rpcEntry.get_pri_cd()));
			primary.setLongDesc(BrokerUtil.trimStringValue(rpcEntry.get_pri_crt_long_desc()));
			primary.setShortDesc(BrokerUtil.trimStringValue(rpcEntry.get_pri_crt_short_desc()));
			primary.setAbbreviation(BrokerUtil.trimStringValue(rpcEntry.get_pri_crt_abbrev()));
			primary.setEffectiveFrom(BrokerUtil.checkDate(rpcEntry.get_pri_crt_st_ts()));
			primary.setEffectiveTo(BrokerUtil.checkDate(rpcEntry.get_pri_crt_end_ts()));

			entry.setPrimaryCode(primary);

			// Related
			Wscrt02_rel_code_rel_arr[] rpcRelatedCodes = rpcEntry.get_rel_arr();
			if (rpcRelatedCodes != null && rpcRelatedCodes.length > 0) {
				for (Wscrt02_rel_code_rel_arr rpcRelatedCode : rpcRelatedCodes) {
					// Check related code provided
					if (Util.empty(rpcRelatedCode.get_rel_cd())) {
						continue;
					}

					CrtEntryData related = new CrtEntryData();
					related.setCrtName(BrokerUtil.trimStringValue(rpcRelatedCode.get_rel_table_name()));
					related.setCode(BrokerUtil.trimStringValue(rpcRelatedCode.get_rel_cd()));
					related.setLongDesc(BrokerUtil.trimStringValue(rpcRelatedCode.get_rel_crt_long_desc()));
					related.setShortDesc(BrokerUtil.trimStringValue(rpcRelatedCode.get_rel_crt_short_desc()));
					related.setAbbreviation(BrokerUtil.trimStringValue(rpcRelatedCode.get_rel_crt_abbrev()));
					related.setEffectiveFrom(BrokerUtil.checkDate(rpcRelatedCode.get_rel_crt_st_ts()));
					related.setEffectiveTo(BrokerUtil.checkDate(rpcRelatedCode.get_rel_crt_end_ts()));

					entry.getRelatedCodes().add(related);
				}
			}

			if (!entry.getRelatedCodes().isEmpty()) {
				entries.add(entry);
			}

		}

		return entries;
	}

	/**
	 * @return the CRT RPC client
	 */
	private Crt createCrtRPC() {
		Broker broker = BrokerUtil.createBroker();
		Crt crt = new Crt(broker, BrokerUtil.getBrokerServer());
		BrokerUtil.setCredentials(crt);
		return crt;
	}

	@Override
	protected String getAuditLogName() {
		return "CRT";
	}

}
