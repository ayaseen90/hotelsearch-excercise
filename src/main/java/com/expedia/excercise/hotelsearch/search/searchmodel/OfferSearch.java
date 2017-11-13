package com.expedia.excercise.hotelsearch.search.searchmodel;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import com.expedia.excercise.hotelsearch.Utilities;
import com.expedia.excercise.hotelsearch.model.Offer;
import com.expedia.excercise.hotelsearch.search.ServiceException;
import com.expedia.excercise.hotelsearch.search.ServiceUtils;
import com.expedia.excercise.hotelsearch.search.client.SearchManager;
import com.expedia.excercise.hotelsearch.search.result.SearchResult;
import static com.expedia.excercise.hotelsearch.search.searchmodel.ServiceConstants.SearchParameters;

public abstract class OfferSearch<T extends Offer>  implements SearchModel {
	
	protected Map<String, String> parameters = new HashMap<>();
	
	private boolean validationEnabled = true;
	
	@Override
	public SearchResult<T> execute() throws ServiceException {
		
		addParameter(ServiceConstants.SearchParameters.PARAM_NAME_PRODUCT_TYPE, getProductType());
		validateParameters();
		
		String searchResult = SearchManager.getInstance().executeSearch(parameters);
		
		return deserialize(searchResult);
	}
	
	/**
	 * 
	 * Validates the basic search criteria, the Parameters validated here are common among all the Offer types
	 * This method can be overridden
	 * 
	 * 
	 * @throws ServiceException
	 */
	protected void validateParameters() throws ServiceException {
		if(!validationEnabled) {
			return;
		}
		
		validateDateParameters(SearchParameters.PARAM_NAME_MIN_TRIP_START_DATE, SearchParameters.PARAM_NAME_MAX_TRIP_START_DATE, SearchParameters.PARAM_NAME_MIN_TRIP_END_DATE, SearchParameters.PARAM_NAME_MAX_TRIP_END_DATE);
		validateAtLeastOneSuppliedParameter(SearchParameters.PARAM_NAME_MIN_TRIP_START_DATE, SearchParameters.PARAM_NAME_MAX_TRIP_START_DATE);
		validateAtLeastOneSuppliedParameter(SearchParameters.PARAM_NAME_MIN_TRIP_END_DATE, SearchParameters.PARAM_NAME_MAX_TRIP_END_DATE);
		validateRequiredParameters(SearchParameters.PARAM_NAME_DESTINATION_NAME);
	}


	protected abstract SearchResult<T> deserialize(String searchResult) throws ServiceException;


	public abstract String getProductType();
	
	public OfferSearch<T> addMinTripStartDate(String date) {
		addParameter(SearchParameters.PARAM_NAME_MIN_TRIP_START_DATE, date);
		return this;
	}
	public OfferSearch<T> addMaxTripEndDate(String date) {
		addParameter(SearchParameters.PARAM_NAME_MAX_TRIP_END_DATE, date);
		return this;
	}
	
	public OfferSearch<T> addDestinationName(String destinationName) {
		addParameter(SearchParameters.PARAM_NAME_DESTINATION_NAME, destinationName);
		return this;
		
	}

	protected void addParameter(SearchParameters searchParameter, String value) {
		if(searchParameter == null) {
			return;
		}
		
		String parameter = searchParameter.getParameterName();
		if(Utilities.isNullOrEmpty(parameter) || Utilities.isNullOrEmpty(value)) {
			return;
		}
		parameters.put(parameter, value);
	}
	
	/**
	 * 
	 * Validates the dates sent to the pairs sent to the method, to ensure that they
	 * can be parsed to the service format
	 * they are all in the future as no booking can be made in the past
	 * they are incremental, as the correct usage for this method is to send the min value then the max value
	 * or the start date then the end date
	 * 
	 * @param dates
	 * @throws ServiceException 
	 */
	protected void validateDateParameters(SearchParameters ... dateParameters) throws ServiceException {
		if(dateParameters == null) {
			return;
		}
		
		Pair<String, String> previousDatePair = null;
		Date previousDate = null;
		
		for(SearchParameters parameter : dateParameters) {
			
			if(parameter == null) {
				continue;
			}
			
			Pair<String, String> currentDatePair = getParameterPair(parameter);
			if(currentDatePair == null) {
				continue;
			}
			if(currentDatePair.getRight() == null) {
				continue;
			}
			
			Date currentDate = ServiceUtils.stringToDate(currentDatePair.getRight());
			
			if(currentDate.before(new Date())) {
				throw new ServiceException(String.format("the %s is in the past", currentDatePair.getLeft()));
			}
			
			if(previousDate != null && 
					previousDate.after(currentDate)) {
				throw new ServiceException(String.format("the value of %s(%s) is greater than the value of %s(%s)", previousDatePair.getLeft(), previousDatePair.getRight(), currentDatePair.getLeft(), currentDatePair.getRight()));
			}
			previousDatePair = currentDatePair;
			previousDate = currentDate;
			
		}
	}
	
	/**
	 * helper method to create a pair from the 
	 * @param param
	 * @return
	 */
	protected Pair<String, String> getParameterPair(SearchParameters param) {
		MutablePair<String, String> pair = new MutablePair<>();
		pair.setLeft(param.getParameterName());
		pair.setRight(parameters.get(param.getParameterName()));
		return pair;
	}

	protected void validateAtLeastOneSuppliedParameter(SearchParameters... params) throws ServiceException {
		
		if(params == null || params.length == 0) {
			throw new IllegalArgumentException("this validation cannot hold if no minimal requiremnets were passed");
		}
		
		for(SearchParameters param : params) {
			if(!Utilities.isNullOrEmpty(parameters.get(param.getParameterName()))) {
				return;
			}
		}
		
		
		throw new ServiceException(String.format("at least one of the required parameters %s should be supplied", SearchParameters.getAsString(params)));
		
	}

	protected void validateRequiredParameters(SearchParameters... params) throws ServiceException {
		for(SearchParameters param : params) {
			if(Utilities.isNullOrEmpty(parameters.get(param.getParameterName()))) {
				throw new ServiceException(String.format("the required parameter %s was not supplied", param.getParameterName()));
			}
		}
	}
	
	public void setValidationEnabled(boolean validate) {
		this.validationEnabled = validate;
	}
	
	public boolean isValidationEnabled() {
		return validationEnabled;
	}
}

