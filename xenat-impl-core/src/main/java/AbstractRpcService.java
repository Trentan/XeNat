import com.xenat.app.service.XenatBusinessException;
import com.xenat.app.service.XenatSystemException;
import com.xenat.app.service.XenatUnauthorisedException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import util.BrokerUtil;
import util.Constants;

/**
 * Abstract RPC Service.
 */
public abstract class AbstractRpcService {

	/**
	 * The logger instance for this class.
	 */
	private static final Log LOG = LogFactory.getLog(AbstractRpcService.class);

	/**
	 * Log the exception and convert it to a Xenat Exception.
	 *
	 * @param action the error prefix
	 * @param recordType the record type
	 * @param recordId the record Id
	 * @param exception the related exception
	 *
	 * @return the Xenat business exception
	 */
	protected XenatBusinessException handleException(final String action, final String recordType, final Object recordId,
	                                                 final Exception exception) {
		// Exception msg
		String msg = exception.getMessage();

		// Log error
		String prefix = "Exception in " + action + " [" + recordType + "-" + recordId + "]. ";
		LOG.error(prefix + msg, exception);

		// Audit log
		LOG.trace(getAuditLogName() + action + recordType + recordId.toString() + Constants.AUDIT_OUTCOME_EXCEPTION + ": " + msg);

		// FIXME Handle specific HTTP responses here as interpreted from broker - right now a bit manual...
		// Already a Business Exception
		if (exception instanceof XenatBusinessException) {
			return (XenatBusinessException) exception;
		}

		// Dont need to create new exception
		if (exception instanceof XenatSystemException) {
			throw (XenatSystemException) exception;
		}

		if (msg.contains("NAT0873")) { // Unauthorised indicator from Mainframe (no valid user id)
			throw new XenatUnauthorisedException("User does not have appropriate Mainframe System access, " + msg, exception);
		}

		// Create System Exception
		throw new XenatSystemException(msg, exception);
	}

	/**
	 * Handle audit messages.
	 *
	 * @param action the action
	 * @param recordType the record type
	 * @param recordId the record Id
	 * @param outcome the outcome details
	 */
	protected void handleAudit(final String action, final String recordType, final Object recordId, final String outcome) {
		String type = recordType == null ? "" : recordType;
		String id = recordId == null ? "" : recordId.toString();
		String out = outcome == null ? Constants.AUDIT_OUTCOME_SUCCESS : outcome;

		LOG.trace(getAuditLogName() + action + type + id + out);
	}

	/**
	 * @return the profile transaction id
	 */
	protected String getPtid() {
		return BrokerUtil.getProfileTransactionId();
	}

	/**
	 * @return the audit log name
	 */
	protected abstract String getAuditLogName();
}
