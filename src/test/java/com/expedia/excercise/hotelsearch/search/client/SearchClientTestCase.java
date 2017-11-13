package com.expedia.excercise.hotelsearch.search.client;

import java.util.HashMap;

import org.junit.Test;

import com.expedia.excercise.hotelsearch.search.ServiceException;
import com.expedia.excercise.hotelsearch.search.client.SearchClient;

public class SearchClientTestCase {
	
	SearchClient client = new SearchClient();
	
	@Test
	public void testSearchAll() throws ServiceException {
		client.executeSearch(new HashMap<String, String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1252477968406029130L;

			{
				//put("productType","LXActivities");	
				put("destinationName","New Orleans");
				
			}
		});
	}

}
