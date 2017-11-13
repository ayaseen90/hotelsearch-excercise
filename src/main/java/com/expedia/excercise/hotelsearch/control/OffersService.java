
package com.expedia.excercise.hotelsearch.control;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import com.expedia.excercise.hotelsearch.model.FlightOffer;
import com.expedia.excercise.hotelsearch.model.HotelOffer;
import com.expedia.excercise.hotelsearch.search.result.SearchResult;
import com.expedia.excercise.hotelsearch.search.searchmodel.FlightSearch;
import com.expedia.excercise.hotelsearch.search.searchmodel.HotelSearch;
import com.expedia.excercise.hotelsearch.search.searchmodel.ServiceConstants;

/**
 * This is the controller of the web application
 * it contains various Rest operations which are used to retrieve the offers (Hotel Deals, Flights, Packages and Activities)
 * @author Anas
 *
 */
@Path("/")
public class OffersService {
    
    /** Method processing HTTP GET requests, producing "text/plain" MIME media
     * type.
     * @return String that will be send back as a response of type "text/plain".
     */
    @GET 
    @Produces("text/plain")
    public String getIt() {
        return "Hi there!";
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/allhotels")
    public List<HotelOffer> getAllHotelOffers() {
    	
    	HotelSearch hotelSearch = new HotelSearch();
    	hotelSearch.setValidationEnabled(false);
    	try {
    		SearchResult<HotelOffer> result = hotelSearch.execute();
    		return result.getAllItems();
    	} catch(Exception ex) {
    		ServiceErrorReporter.reportServiceError("Failed to execute service request", ex);
    		throw new RuntimeException("An error occured while accessing the backend Service");
    	}
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/allflights")
    public List<FlightOffer> getAllFlightOffers() {
    	
    	FlightSearch flightSearch = new FlightSearch();
    	try {
    		SearchResult<FlightOffer> result = flightSearch.execute();
    		return result.getAllItems();
    	} catch(Exception ex) {
    		ServiceErrorReporter.reportServiceError("Failed to execute service request", ex);
    		throw new RuntimeException("An error occured while accessing the backend Service");
    	}
    }
    
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/hotels")
    public List<HotelOffer> getHotelOffers(@QueryParam(value = ServiceConstants.PARAM_NAME_MIN_TRIP_START_DATE_STR) String from,
    		@QueryParam(value = ServiceConstants.PARAM_NAME_MAX_TRIP_END_DATE_STR) String to,
    		@QueryParam(value = ServiceConstants.PARAM_NAME_DESTINATION_NAME_STR) String destination,
    		@Context UriInfo uriInfo, String content) {
    	HotelSearch hotelSearch = new HotelSearch();
    	hotelSearch.addDestinationName(destination);
    	hotelSearch.addMinTripStartDate(from);
    	hotelSearch.addMaxTripEndDate(to);
    	try {
    		SearchResult<HotelOffer> result = hotelSearch.execute();
    		
    		return result.getAllItems();
    	} catch(Exception ex) {
    		ServiceErrorReporter.reportServiceError("Failed to execute service request", ex);
    		throw new RuntimeException("An error occured while accessing the backend Service");
    	}
    }
    
}
