package com.expedia.excercise.hotelsearch.search.client;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

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
	private static final Map<String, String> defaultParameters = new HashMap<String, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = -8898507360282120963L;

		{
			put("scenario", "deal-finder");
			put("page", "foo");
			put("uid", "foo");
			
		}
	};
	
	private static final String URL = "https://offersvc.expedia.com/offers/v2/getOffers";
	
	String executeSearch(Map<String, String> parameters) throws ServiceException {
		parameters.putAll(defaultParameters);

		
		WebTarget webTarget = client.target(URL);
			
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