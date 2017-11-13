package com.expedia.excercise.hotelsearch.model;

import org.codehaus.jackson.map.annotate.JsonDeserialize;

import com.expedia.excercise.hotelsearch.json.FlightDeserializer;

@JsonDeserialize(using = FlightDeserializer.class)
public class FlightOffer extends Offer {
	private static final String DEAL_TYPE_HOTEL = "Flight";

	public FlightOffer() {
		setDealType(DEAL_TYPE_HOTEL);
	}
	
	private String airline;

	public String getHotelName() {
		return airline;
	}

	public void setHotelName(String airline) {
		this.airline = airline;
	}
}
