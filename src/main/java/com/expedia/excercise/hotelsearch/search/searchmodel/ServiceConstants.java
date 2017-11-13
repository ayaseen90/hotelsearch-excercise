package com.expedia.excercise.hotelsearch.search.searchmodel;

public interface ServiceConstants {

	String PARAM_NAME_PRODUCT_TYPE_STR = "productType";
	String PARAM_NAME_DESTINATION_NAME_STR = "destinationName";
	String PARAM_NAME_MIN_TRIP_START_DATE_STR = "minTripStartDate";
	String PARAM_NAME_MAX_TRIP_START_DATE_STR = "maxTripStartDate";
	String PARAM_NAME_MAX_TRIP_END_DATE_STR = "maxTripEndDate";
	String PARAM_NAME_MIN_TRIP_END_DATE_STR = "minTripEndDate";

	public enum SearchParameters {
	
		PARAM_NAME_PRODUCT_TYPE(PARAM_NAME_PRODUCT_TYPE_STR),
		PARAM_NAME_DESTINATION_NAME(PARAM_NAME_DESTINATION_NAME_STR),
		PARAM_NAME_MIN_TRIP_START_DATE(PARAM_NAME_MIN_TRIP_START_DATE_STR),
		PARAM_NAME_MAX_TRIP_START_DATE(PARAM_NAME_MAX_TRIP_START_DATE_STR),
		PARAM_NAME_MIN_TRIP_END_DATE(PARAM_NAME_MIN_TRIP_END_DATE_STR),
		PARAM_NAME_MAX_TRIP_END_DATE(PARAM_NAME_MAX_TRIP_END_DATE_STR)
	
	
	;
	SearchParameters(String parameterName) {
		this.parameterName = parameterName;
	}
	
	private String parameterName;
	public String getParameterName() {
		return parameterName;
	}
	
	public static String getAsString(SearchParameters[] parameters) {
		StringBuilder sb = new StringBuilder("[ ");
		if(parameters == null) {
			return sb.append("]").toString();
		}
		
		for(int i=0; i< parameters.length; i++) {
			sb.append(parameters[i].getParameterName());
			
			if(i < parameters.length-1) {
				sb.append(", ");
			}
		}

		return sb.append(" ]").toString();
	}
}
}
