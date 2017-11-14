package com.expedia.excercise.hotelsearch.search.client;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.log4j.Logger;

import com.expedia.excercise.hotelsearch.Utilities;
import com.expedia.excercise.hotelsearch.search.ServiceException;
import javax.ws.rs.client.Client;

/**
 * This is a wrapper class to JersyClient designed specifically to access Expedia
 * Exercise Restful API
 * @author Anas
 *
 */
public class SearchClient {
	
	/**
	 * Constructor with package access
	 * to prevent having objects created from parties other than {@link SearchManager}
	 */
	SearchClient() {
		
	}
	
	private static final Logger LOG = Logger.getLogger(SearchClient.class);

	private Client client = ClientBuilder.newClient();
	
	
	/*
	 * MAP containing the default parameters which should be supplied for the API to function 
	 */
	private static final Map<String, String> DEFAULT_PARAMETERS = new HashMap<String, String>() {
		
		private static final long serialVersionUID = -8898507360282120963L;

		{
			put("scenario", "deal-finder");
			put("page", "foo");
			put("uid", "foo");
			
		}
	};

	/*
	 * URL Used to access the rest webservice
	 */
	private static final String SERVICE_URL = loadURL();
	/*
	 * Default value of the URL in case it was not provided
	 */
	private static final String DEFAULT_URL = "https://offersvc.expedia.com/offers/v2/getOffers";


	/**
	 * Loads the Service URL and return it
	 * @return the URL of the remote service
	 */
	private static String loadURL() {
		Pair<String, String> propertiesFileAndKey = new ImmutablePair<String, String>("/config.properties", "expedia_url");
		return Utilities.loadConfig("EXPEDIA_URL", propertiesFileAndKey, DEFAULT_URL);
	}
	
	String executeSearch(Map<String, String> parameters) throws ServiceException {
		parameters.putAll(DEFAULT_PARAMETERS);

		
		WebTarget webTarget = client.target(SERVICE_URL);
			
		for(Iterator<String> parametersIterator = parameters.keySet().iterator(); parametersIterator.hasNext();) {
			String paramName = parametersIterator.next();
			webTarget = webTarget.queryParam(paramName, parameters.get(paramName));
		}
		
		LOG.trace("before executing search request");
		String output = webTarget.request(MediaType.APPLICATION_JSON).get(String.class);
		LOG.trace("Finished executing search request");
		
		
		return output;
			
	}
	
	@Override
	public void finalize() {
		SearchManager.getInstance().decreaseCounter();
	}
}