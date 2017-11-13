package com.expedia.excercise.hotelsearch.search.searchmodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.expedia.excercise.hotelsearch.model.HotelOffer;
import com.expedia.excercise.hotelsearch.search.ServiceException;
import static com.expedia.excercise.hotelsearch.search.searchmodel.ServiceConstants.SearchParameters;

public class OfferSearchValidationTestCase {

	@Test
	public void testPassingValidationBySupplyingAllTheParameters() throws ServiceException {
		OfferSearch<HotelOffer> search = new HotelSearch();
		
		search.parameters.put(SearchParameters.PARAM_NAME_PRODUCT_TYPE.getParameterName(), "Hotel");
		search.parameters.put(SearchParameters.PARAM_NAME_DESTINATION_NAME.getParameterName(), "Amman");
		search.parameters.put(SearchParameters.PARAM_NAME_MIN_TRIP_START_DATE.getParameterName(), "2030-12-12");
		search.parameters.put(SearchParameters.PARAM_NAME_MAX_TRIP_START_DATE.getParameterName(), "2030-12-13");
		search.parameters.put(SearchParameters.PARAM_NAME_MIN_TRIP_END_DATE.getParameterName(), "2030-12-14");
		search.parameters.put(SearchParameters.PARAM_NAME_MAX_TRIP_END_DATE.getParameterName(), "2030-12-15");
		
		
		search.validateParameters();
	}
	
	@Test
	public void testPassingValidationBySupplyingMinimumParameters() throws ServiceException {
		OfferSearch<HotelOffer> search = new HotelSearch();
		
		search.parameters.put(SearchParameters.PARAM_NAME_PRODUCT_TYPE.getParameterName(), "Hotel");
		search.parameters.put(SearchParameters.PARAM_NAME_DESTINATION_NAME.getParameterName(), "Amman");
		search.parameters.put(SearchParameters.PARAM_NAME_MIN_TRIP_START_DATE.getParameterName(), "2030-12-12");
		search.parameters.put(SearchParameters.PARAM_NAME_MAX_TRIP_END_DATE.getParameterName(), "2030-12-15");
		
		
		search.validateParameters();
	}
	

	@Test
	public void testFailingNotSupplyingMinimumParameters() throws ServiceException {
		OfferSearch<HotelOffer> search = new HotelSearch();
		
		search.parameters.put(ServiceConstants.SearchParameters.PARAM_NAME_PRODUCT_TYPE.getParameterName(), "Hotel");
		search.parameters.put(ServiceConstants.SearchParameters.PARAM_NAME_DESTINATION_NAME.getParameterName(), "Amman");
		search.parameters.put(ServiceConstants.SearchParameters.PARAM_NAME_MAX_TRIP_END_DATE.getParameterName(), "2030-12-15");
		
		try {
			search.validateParameters();
			fail("a validation for not having trip start date should have been thrown");
		} catch(ServiceException ex) {
			assertEquals("at least one of the required parameters [ minTripStartDate, maxTripStartDate ] should be supplied", ex.getMessage());
		}
		
	}

	@Test
	public void testvalidateAtLeastOneSuppliedParameterFailingForEmptyExpectedList() {
		OfferSearch<HotelOffer> search = new HotelSearch();
		try {
			search.validateAtLeastOneSuppliedParameter();
			fail("A runtime exception should have been thrown");
		} catch(Exception ex) {
			assertEquals(IllegalArgumentException.class, ex.getClass());
			assertEquals("this validation cannot hold if no minimal requiremnets were passed", ex.getMessage());
		}
		
		try {
			search.validateAtLeastOneSuppliedParameter(null);
			fail("A runtime exception should have been thrown");
		} catch(Exception ex) {
			assertEquals(IllegalArgumentException.class, ex.getClass());
			assertEquals("this validation cannot hold if no minimal requiremnets were passed", ex.getMessage());
		}
	}
	
	@Test
	public void testPassingValidateRequiredParameters() throws ServiceException {
		OfferSearch<HotelOffer> search = new HotelSearch();
		
		search.parameters.put(SearchParameters.PARAM_NAME_PRODUCT_TYPE.getParameterName(), "Hotel");
		search.parameters.put(SearchParameters.PARAM_NAME_DESTINATION_NAME.getParameterName(), "Amman");
		search.parameters.put(SearchParameters.PARAM_NAME_MAX_TRIP_END_DATE.getParameterName(), "2030-12-15");
		search.validateRequiredParameters(SearchParameters.PARAM_NAME_PRODUCT_TYPE);
	}
	
	@Test
	public void testFailingValidateRequiredParameters() {
		OfferSearch<HotelOffer> search = new HotelSearch();
		
		search.parameters.put(ServiceConstants.SearchParameters.PARAM_NAME_DESTINATION_NAME.getParameterName(), "Amman");
		search.parameters.put(ServiceConstants.SearchParameters.PARAM_NAME_MAX_TRIP_END_DATE.getParameterName(), "2030-12-15");
		try {
			search.validateRequiredParameters(ServiceConstants.SearchParameters.PARAM_NAME_PRODUCT_TYPE);
			fail("A Service Validation exception should have been thrown");
		} catch (ServiceException e) {
			assertEquals("the required parameter " + ServiceConstants.SearchParameters.PARAM_NAME_PRODUCT_TYPE.getParameterName() + " was not supplied", e.getMessage());
		}
	}
}
