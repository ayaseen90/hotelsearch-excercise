package com.expedia.excercise.hotelsearch.search.result;

import com.expedia.excercise.hotelsearch.json.FlightDeserializer;
import com.expedia.excercise.hotelsearch.json.OfferDeserializer;
import com.expedia.excercise.hotelsearch.model.FlightOffer;

public class FlightSearchResult extends SearchResultBase<FlightOffer> {

	public FlightSearchResult(String message) {
		super(message);
	}

	@Override
	protected OfferDeserializer<FlightOffer> createDeserializer() {
		return new FlightDeserializer();
	}
}
