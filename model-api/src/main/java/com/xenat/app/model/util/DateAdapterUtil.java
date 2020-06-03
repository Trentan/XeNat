package com.xenat.app.model.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

/**
 * This class is used by the JAXB (Java API for XML Binding) Generation code, to parse the XML Schema (http://www.w3.org/TR/xmlschema-2) xs:date /
 * xs:dateTime data types.
 * <p>
 *
 * <pre>
 * xs:date     := ('-')? yyyy '-' mm '-' dd
 * xs:dateTime := ('-')? yyyy '-' mm '-' dd 'T' hh ':' mm ':' ss ('.' s+)?
 * </pre>
 */
public class DateAdapterUtil {

	/**
	 * A <b>SimpleDateFormat</b> string used to output xs:date objects.
	 */
	private static final String DATE_FORMAT = "yyyy-MM-dd";

	/**
	 * A <b>SimpleDateFormat</b> string used to output xs:datetime objects.
	 */
	private static final String DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

	/**
	 * A <b>SimpleDateFormat</b> string used to output xs:datetime objects.
	 */
	private static final String DATETIME_FORMAT_UTC = "yyyy-MM-dd'T'HH:mm:ss.SXXX";

	private DateAdapterUtil() {
	}

	/**
	 * Parse a xs:date.
	 *
	 * @param date A string representing the xs:date to be parsed.
	 * @return A <b>Date</b> object representing the parsed value of the <em>date</em> parameter.
	 */
	public static Date parseStringToDate(final String date) {
		if (date == null) {
			return null;
		}
		DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		try {
			return dateFormat.parse(date);
		} catch (ParseException e) {
			throw new Error("Could not parse date " + date + ". Expected format " + DATE_FORMAT, e);
		}
	}

	/**
	 * Parse a xs:dateTime.
	 *
	 * @param dateTime A string representing the xs:dateTime to be parsed.
	 * @return A <b>Date</b> object representing the parsed value of the <em>dateTime</em> parameter.
	 */
	public static Date parseStringToDateTime(final String dateTime) {
		if (dateTime == null) {
			return null;
		}
		DateFormat dateFormat = new SimpleDateFormat(DATETIME_FORMAT);
		try {
			return dateFormat.parse(dateTime);
		} catch (ParseException e) {
			throw new Error("Could not parse dateTime " + dateTime + ". Expected format " + DATETIME_FORMAT, e);
		}

	}

	/**
	 * This method converts a <b>java.util.Date</b> into a <b>String</b> representing the equivalent XML Schema Date (xs:date).
	 *
	 * @param date The java.util.Date object to be converted into a String representing an xs:date.
	 * @return A <b>String</b> representing the xs:date representation of the specified java.util.Date.
	 */
	public static String formatDateToString(final Date date) {
		if (date == null) {
			return "";
		}
		DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		String dateString = dateFormat.format(date);
		return dateString;
	}

	/**
	 * This method converts a <b>java.util.Date</b> into a <b>String</b> representing the equivalent XML Schema DateTime (xs:dateTime).
	 *
	 * @param dateTime The java.util.Date object to be converted into a String representing an xs:dateTime.
	 * @return A <b>String</b> representing the xs:dateTime representation of the specified java.util.Date.
	 */
	public static String formatDateTimeToString(final Date dateTime) {
		if (dateTime == null) {
			return "";
		}
		DateFormat dateTimeFormat = new SimpleDateFormat(DATETIME_FORMAT);
		String dateTimeString = dateTimeFormat.format(dateTime);
		return dateTimeString;
	}

	/**
	 * @param date the java date
	 * @return the local date
	 */
	public static LocalDate convertDateToLocalDate(final Date date) {
		if (date == null) {
			return null;
		}
		return date.toInstant()
				.atZone(ZoneId.systemDefault())
				.toLocalDate();
	}

	/**
	 * @param date the java date
	 * @return the local date
	 */
	public static LocalDateTime convertDateToLocalDateTime(final Date date) {
		if (date == null) {
			return null;
		}
		return date.toInstant()
				.atZone(ZoneId.systemDefault())
				.toLocalDateTime();
	}

	public static String formatMFDateTimeToStringUTC(Date dateTime) {
		if (dateTime == null) {
			return "";
		}
		DateFormat dateTimeUTCFormat = new SimpleDateFormat(DATETIME_FORMAT_UTC);
		dateTimeUTCFormat.setTimeZone(TimeZone.getTimeZone("Australia/Sydney")); // Mainframe located Sydney

		return dateTimeUTCFormat.format(dateTime);
	}
}
