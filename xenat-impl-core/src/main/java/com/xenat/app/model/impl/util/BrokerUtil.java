package com.xenat.app.model.impl.util;

import com.github.bordertech.config.Config;
import com.google.gson.Gson;
import com.softwareag.entirex.aci.Broker;
import com.softwareag.entirex.aci.BrokerException;
import com.softwareag.entirex.aci.RPCService;
import com.xenat.app.service.AbstractServiceMessagesResponse;
import com.xenat.app.service.Messages;
import com.xenat.app.service.MessagesMeta;
import com.xenat.app.service.XenatBusinessException;
import com.xenat.app.service.XenatSystemException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import rpc.XenatList;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Utility class to Handle Broker Services.
 *
 * @author Trentan Healey
 * @since POC1
 */
public final class BrokerUtil {

	/**
	 * The logger instance for this class.
	 */
	private static final Log LOG = LogFactory.getLog(BrokerUtil.class);


	/**
	 * Broker ID.
	 */
	private static final String XENAT_BROKER_ID = "xenat.broker.id";
	private static final String BROKER_ID = Config.getInstance().getString(XENAT_BROKER_ID, "mvsimda:18024");

	/**
	 * Broker Server.
	 */
	private static final String XENAT_BROKER_SERVER = "xenat.broker.server";
	private static final String BROKER_SERVER = Config.getInstance().getString(XENAT_BROKER_SERVER, "RPC/DEV/CALLNAT");

	/**
	 * User Id.
	 */
	private static final String XENAT_USER_ID = "xenat.userid";
	private static final String USER_ID = Config.getInstance().getString(XENAT_USER_ID, null);

	/**
	 * Don't allow external instantiation of this class.
	 */
	private BrokerUtil() {
		// Do nothing
	}

//	/**
//	 * @return the current profile transaction id
//	 */
//	public static String getProfileTransactionId() { // FIXME To add Profile transaction ID
//		AppContext appContext = AppContextManager.getCurrent();
//		if (appContext == null) {
//			return null;
//		}
//		InvocationContext invContext = appContext.getInvocationContext();
//		if (invContext == null) {
//			return null;
//		}
//
//		return invContext.getTransactionId();
//	}

	/**
	 * Trim the string. Empty is treated as null.
	 *
	 * @param value the string value to trim
	 * @return the string value trimmed
	 */
	public static String trimStringValue(final String value) {
		if (StringUtils.isEmpty(value)) {
			return null;
		}
		return value.trim();
	}

	/**
	 * Convert a Number to an Integer. Zero is treated as Null
	 *
	 * @param num the number to convert
	 * @return number as an integer or null
	 */
	public static Integer convertNumberToInteger(final Number num) {
		if (num == null) {
			return null;
		}
		int value = num.intValue();
		return value == 0 ? null : value;
	}

	/**
	 * Convert a Number to a Long. Zero is treated as Null
	 *
	 * @param num the number to convert
	 * @return number as a long or null
	 */
	public static Long convertNumberToLong(final Number num) {
		if (num == null) {
			return null;
		}
		long value = num.longValue();
		return value == 0 ? null : value;
	}

	/**
	 * Convert a Number to Short. Zero is treated as Null.
	 *
	 * @param num the number to convert
	 * @return the number as a Short
	 */
	public static Short convertIntegerToShort(final Integer num) {
		if (num == null) {
			return null;
		}
		short value = num.shortValue();
		return value == 0 ? null : value;
	}

	/**
	 * Check for invalid RPC date.
	 * <p>
	 * NOTE from Entirex Doco: If you use the value null (null pointer) as an input parameter (for IN and INOUT
	 * parameters) for types D/T, the current date/time will be used. You change this with the property
	 * entirex.marshal.date. Setting entirex.marshal.date=null will map the value null to the invalid date 0000-01-01 of
	 * the RPC marshalling. This is the invalid date value in Natural, too. With this setting the invalid date as an
	 * output parameter will be mapped to null. The default is to map the invalid date to 0001-01-01.
	 * </p>
	 *
	 * @param date the RPC date
	 * @return the date or null if invalid RPC date
	 */
	public static Date checkDate(final Date date) {
		if (date == null) {
			return null;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		// "Null" date in Entirex is 0001-01-01
		if (cal.get(Calendar.YEAR) == 1 && cal.get(Calendar.MONTH) == Calendar.JANUARY
				&& cal.get(Calendar.DAY_OF_MONTH) == 1) {
			return null;
		}

		// Clear time (Just in case values set)
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}

	/**
	 * Check for invalid RPC dateTime.
	 *
	 * @param time the RPC date
	 * @return the date or null if invalid RPC date
	 */
	public static Date checkTime(final Date time) {
		if (time == null) {
			return null;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(time);

		// "Null" time in Entirex is 0001-01-01 00:00:00.0
		if (cal.get(Calendar.YEAR) == 1 && cal.get(Calendar.MONTH) == Calendar.JANUARY
				&& cal.get(Calendar.DAY_OF_MONTH) == 1 && cal.get(Calendar.HOUR) == 0 && cal.get(Calendar.MINUTE) == 0
				&& cal.get(Calendar.SECOND) == 0 && cal.get(Calendar.MILLISECOND) == 0) {
			return null;
		}

		return time;
	}

	/**
	 * Convert a java date to an EntireX date.
	 *
	 * @param date the java date
	 * @return the entireX date format
	 */
	public static Date convertDateToEntirexDate(final Date date) {
		Calendar cal = Calendar.getInstance();

		if (date == null) {
			// "Null" date in EntireX is 0001-01-01
			cal.set(Calendar.YEAR, 1);
			cal.set(Calendar.MONTH, Calendar.JANUARY);
			cal.set(Calendar.DAY_OF_MONTH, 1);
		} else {
			cal.setTime(date);
		}

		// Clear time (Just in case values set)
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}

	/**
	 * Convert a java date to an Entirex Date.
	 *
	 * @param time the java time
	 * @return the entireX time format
	 */
	public static Date convertTimeToEntireXTime(final Date time) {
		Calendar cal = Calendar.getInstance();

		if (time == null) {
			// "Null" time in Entirex is 0001-01-01 00:00:00.0
			cal.set(Calendar.YEAR, 1);
			cal.set(Calendar.MONTH, Calendar.JANUARY);
			cal.set(Calendar.DAY_OF_MONTH, 1);
			cal.set(Calendar.HOUR, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
		} else {
			cal.setTime(time);
		}

		return cal.getTime();
	}

	/**
	 * Add number array to integers list.
	 *
	 * @param list the list of integers
	 * @param array the array of numbers
	 */
	public static void addNumberArrayToList(final List<Integer> list, final Number[] array) {
		if (array == null) {
			return;
		}

		for (Number item : array) {
			if (item != null) {
				list.add(item.intValue());
			}
		}
	}

	/**
	 * Add string array to string list.
	 *
	 * @param list the list of integers
	 * @param array the array of numbers
	 */
	public static void addStringArrayToList(final List<String> list, final String[] array) {
		if (array == null) {
			return;
		}

		for (String item : array) {
			if (StringUtils.isNotEmpty(item)) {
				list.add(item);
			}
		}
	}

	/**
	 * Logoff from the broker.
	 *
	 * @param rpc the rpc service
	 * @throws BrokerException a broker exception
	 */
	public static void logoff(final RPCService rpc) throws BrokerException {
		if (rpc != null) {
			rpc.getBroker().logoff();
		}
	}

	/**
	 * Disconnect from the broker.
	 *
	 * @param rpc the rpc service
	 */
	public static void disconnect(final RPCService rpc) {
		if (rpc != null) {
			rpc.getBroker().disconnect();
		}
	}

	/**
	 * Trim the string. Empty is treated as null.
	 *
	 * @param value the string value to trim
	 * @return the string value trimmed
	 */
	public static String[] trimStringValues(String[] value) {
		for (int i = 0; i < value.length; i++) {
			if (StringUtils.isEmpty(value[i])) {
				value[i] = null;
			} else {
				value[i] = value[i].trim();
			}
		}

		return value;
	}

	/**
	 * @param list the list of Strings
	 * @return the list as a String array
	 */
	public static String[] listToStringArr(final List<String> list) {
		String[] stringArr = new String[list.size()];
		stringArr = list.toArray(stringArr);
		return stringArr;
	}

	/** Used for generic message handling and standardised API meta message responses */
	public static void checkApiMessages(AbstractServiceMessagesResponse response, String ptid, String moduleName, RPCService req) throws XenatBusinessException, NoSuchMethodException {
		Method method = req.getClass().getDeclaredMethod("get"+moduleName+"_msg");
		try {
			Gson gson = new Gson();
			String tmp = gson.toJson(method.invoke(req)); // Moving the class to a String to move to generic class below
			List<Messages> msgs = new ArrayList<>();
			for (MessageRPC msg : gson.fromJson(tmp, MessageRPC[].class)) {
				if ("E".equals(trimStringValue(msg.get_msg_typ()))) throw new XenatSystemException(msg.get_msg_txt());
				Messages messagesApi = new Messages(ptid, moduleName, msg.get_msg_cod(), msg.get_msg_fld(), msg.get_msg_txt(), msg.get_msg_typ());
				msgs.add(messagesApi);
			}
			if (!msgs.isEmpty()) {
				response.setMeta(new MessagesMeta(msgs));
			}
		} catch (IllegalAccessException | InvocationTargetException e) {
			LOG.error("an error occurred for message handling", e);
			throw new XenatBusinessException(moduleName);
		}
	}

	/**
	 * Concatenate list of errors into a String.
	 *
	 * @param errors list of errors
	 * @return errors as a concatenated string
	 */
	public static String concatErrors(final List<String> errors) {
		if (errors == null || errors.isEmpty()) {
			return "";
		}

		StringBuilder buffer = new StringBuilder();
		boolean first = true;
		for (String msg : errors) {
			if (!first) {
				buffer.append("; ");
			}
			buffer.append(msg);
			first = false;
		}

		return buffer.toString();
	}

	/**
	 * @return the broker instance
	 */
	public static Broker createBroker() {
		return new Broker(getBrokerId(), getUserId(), getBrokerServer());
	}

	/**
	 * @return the broker server name
	 */
	public static String getBrokerServer() {
		return BROKER_SERVER;
	}

	/**
	 * @return the broker id
	 */
	public static String getBrokerId() {
		return BROKER_ID;
	}

	/**
	 * @return the current user id in the context
	 */
	public static String getUserId() {


//		AppContext appContext = AppContextManager.getCurrent(); FIXME may need to set a rest profile filter =/
//		if (appContext == null) {
//			throw new XenatSystemException("No app context set.");
//		}
//		SessionContext sessionContext = appContext.getSessionContext();
//		if (sessionContext == null) {
//			throw new XenatSystemException("No session context set.");
//		}
//		String userId = sessionContext.getUsername();
		if (USER_ID == null) {
			throw new XenatSystemException("No user id set.");
		}
		return USER_ID;
	}

	/**
	 * Set the user credentials.
	 *
	 * @param service the RPC service to set credentials on
	 */
	public static void setCredentials(final RPCService service) {
		// User details
		service.setRPCUserId(getUserId());
		service.setRPCPassword(getUserId());
		service.setNaturalLogon(true);
	}

	/**
	 * @return the List RPC client
	 */
	public static XenatList createListRPC() {
		XenatList svc = new XenatList(createBroker(), getBrokerServer());
		setCredentials(svc);
		return svc;
	}

}
