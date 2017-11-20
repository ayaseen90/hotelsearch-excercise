package com.expedia.excercise.hotelsearch.search.searchmodel;


import com.expedia.excercise.hotelsearch.model.HotelOffer;
import com.expedia.excercise.hotelsearch.search.result.HotelSearchResult;
import com.expedia.excercise.hotelsearch.search.result.SearchResult;
import com.expedia.excercise.hotelsearch.search.searchmodel.ServiceConstants.SearchParameters;

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

	public HotelSearch addMinStarRating(Double minStarRating) {
		
		if(minStarRating != null) {
			addParameter(SearchParameters.PARAM_NAME_HOTEL_MIN_STAR_RATING, String.valueOf(minStarRating));			
		}
		return this;
		
	}
	


}
