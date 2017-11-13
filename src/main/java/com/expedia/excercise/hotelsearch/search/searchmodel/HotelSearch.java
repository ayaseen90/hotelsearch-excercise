package com.expedia.excercise.hotelsearch.search.searchmodel;


import com.expedia.excercise.hotelsearch.model.HotelOffer;
import com.expedia.excercise.hotelsearch.search.result.HotelSearchResult;
import com.expedia.excercise.hotelsearch.search.result.SearchResult;

public class HotelSearch extends OfferSearch<HotelOffer> {

	private static final String PRODUCT_TYPE = "Hotel";
	
	

	@Override
	public String getProductType() {
		return HotelSearch.PRODUCT_TYPE;
	}

	@Override
	protected SearchResult<HotelOffer> deserialize(String searchResult) {
		HotelSearchResult result = new HotelSearchResult(searchResult);
		
		
		return result;
	}
	


}
