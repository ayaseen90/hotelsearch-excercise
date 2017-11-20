package com.expedia.excercise.hotelsearch.model;

import org.codehaus.jackson.map.annotate.JsonDeserialize;

import com.expedia.excercise.hotelsearch.json.HoteDeserializer;

/**
 * This class is the business class for the Hotel Offer, it stores the information retrieved
 * from the API by deserializing the JSon according to the provided Deserializer
 * 
 * @author Anas
 *
 */
@JsonDeserialize(using = HoteDeserializer.class)
public class HotelOffer extends Offer {
	private static final String DEAL_TYPE_HOTEL = "Hotel";
	

	public HotelOffer() {
		setDealType(DEAL_TYPE_HOTEL);
	}
	
	private String hotelName;
	
	private String hotelURL;
	
	private Double starRating;

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getHotelURL() {
		return hotelURL;
	}

	public void setHotelURL(String hotelURL) {
		this.hotelURL = hotelURL;
	}

	public static String getDealTypeHotel() {
		return DEAL_TYPE_HOTEL;
	}

	public Double getStarRating() {
		return starRating;
	}

	public void setStarRating(Double starRating) {
		this.starRating = starRating;
	}
}
