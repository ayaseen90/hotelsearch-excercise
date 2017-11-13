package com.expedia.excercise.hotelsearch.search.result;

import com.expedia.excercise.hotelsearch.json.HoteDeserializer;
import com.expedia.excercise.hotelsearch.json.OfferDeserializer;
import com.expedia.excercise.hotelsearch.model.HotelOffer;

public class HotelSearchResult extends SearchResultBase<HotelOffer>{

	public HotelSearchResult(String message) {
		super(message);
	}
	
	@Override
	protected OfferDeserializer<HotelOffer> createDeserializer() {
		return new HoteDeserializer();
	}
}
