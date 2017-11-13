package com.expedia.excercise.hotelsearch.search;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServiceUtils {
	private static final String SERVICE_DATE_FORMAT_STRING = "yyyy-MM-dd";
	private static final SimpleDateFormat SERVICE_DATE_FORMAT = new SimpleDateFormat(SERVICE_DATE_FORMAT_STRING);
	public static String dateToString(Date d) {
		return SERVICE_DATE_FORMAT.format(d);
	}
	
	public static Date stringToDate(String s) throws ServiceException {
		try {
			return SERVICE_DATE_FORMAT.parse(s);
		} catch(ParseException ex) {
			throw new ServiceException(String.format("Invalid supplied date format %s", s));
		}
	}
}
