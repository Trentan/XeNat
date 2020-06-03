package com.xenat.app.rest.client.util;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Convert date and times to joda.
 */
public class RestAdapterUtil {

	private RestAdapterUtil() {
	}

	/**
	 * @param date the java date
	 * @return the local date
	 */
	public static LocalDate convertDateToLocalDate(final Date date) {
		if (date == null) {
			return null;
		}
		return new LocalDate(date);
	}

	/**
	 * @param date the java date
	 * @return the local date
	 */
	public static LocalDateTime convertDateToLocalDateTime(final Date date) {
		if (date == null) {
			return null;
		}
		return new LocalDateTime(date);
	}

	/**
	 * @param enums the list of enum items
	 * @return the list of enum names
	 */
	public static List<String> getEnumNames(final List<? extends Enum> enums) {
		if (enums == null) {
			return null;
		}
		List<String> values = new ArrayList<>();
		for (Enum item : enums) {
			values.add(item.name());
		}
		return values;
	}

	/**
	 * @param item the enum item
	 * @return the enum name
	 */
	public static String getEnumName(final Enum item) {
		return item == null ? null : item.name();
	}

}
