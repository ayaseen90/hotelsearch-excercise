package com.expedia.excercise.hotelsearch.json;

import org.codehaus.jackson.JsonNode;

import com.expedia.excercise.hotelsearch.model.FlightOffer;
import com.expedia.excercise.hotelsearch.model.HotelOffer;

public class FlightDeserializer extends OfferDeserializer<FlightOffer> {

	public FlightDeserializer() {
		super(HotelOffer.class);
	}
	private static final String[] PRICE_PATH = {"flightPricingInfo", "flightPerPsgrTotalPrice"};

	@Override
	protected FlightOffer createObject() {
		return new FlightOffer();
	}
	
	@Override
	protected Class<FlightOffer> getType() {
		return FlightOffer.class;
	}

	@Override
	protected FlightOffer populatOfferInfo(JsonNode node, FlightOffer offer) {
		
		JsonNode hotelNameNode = applyPath(new String[] {"hotelInfo", "hotelName"}, node);
		if(hotelNameNode != null) {
			offer.setHotelName(hotelNameNode.asText());
		}
		return offer;
	}

	@Override
	protected String[] getImagePath() {
		return null;
	}

	@Override
	protected String[] getPricePath() {
		return PRICE_PATH;
	}

	@Override
	protected String getOfferTypeNodeName() {
		return "Flight";
	}
	
	
}
