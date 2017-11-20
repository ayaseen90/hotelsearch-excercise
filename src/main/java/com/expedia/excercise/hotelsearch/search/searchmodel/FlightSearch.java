package com.expedia.excercise.hotelsearch.search.searchmodel;

import com.expedia.excercise.hotelsearch.model.FlightOffer;
import com.expedia.excercise.hotelsearch.search.result.FlightSearchResult;
import com.expedia.excercise.hotelsearch.search.result.SearchResult;

public class FlightSearch extends OfferSearch<FlightOffer>{


	private static final String PRODUCT_TYPE = "Flight";
	@Override
	protected SearchResult<FlightOffer> deserialize(String searchResult) {
		SearchResult<FlightOffer> result = new FlightSearchResult(searchResult);
		return result;
	}

	@Override
	public String getProductType() {
		return PRODUCT_TYPE;
	}

}
