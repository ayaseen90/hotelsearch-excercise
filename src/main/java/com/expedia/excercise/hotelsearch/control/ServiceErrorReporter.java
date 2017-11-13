package com.expedia.excercise.hotelsearch.control;

import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

public class ServiceErrorReporter {
	private static Logger LOG = Logger.getLogger(ServiceErrorReporter.class);
	
	public static void reportServiceError(String errorMessage) {
		reportServiceError(errorMessage, null);
	}

	public static void reportServiceError(String errorMessage, Throwable exception) {
		LOG.error(errorMessage, exception);
		
	}

	public static Response generateErrorResponseMessage(Exception ex) {
		return Response.serverError().entity(ex).build();
	}
	
}
