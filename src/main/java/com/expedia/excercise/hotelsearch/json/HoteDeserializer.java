package com.expedia.excercise.hotelsearch.json;

import java.io.UnsupportedEncodingException;

import org.codehaus.jackson.JsonNode;

import com.expedia.excercise.hotelsearch.model.HotelOffer;

public class HoteDeserializer extends OfferDeserializer<HotelOffer> {

	public HoteDeserializer() {
		super(HotelOffer.class);
	}

	private static final String[] IMAGE_PATH = {"hotelInfo", "hotelImageUrl"};
	private static final String[] PRICE_PATH = {"hotelPricingInfo", "totalPriceValue"};
	private static final String[] HOTEL_NAME_PATH = new String[] {"hotelInfo", "hotelName"};
	private static final String[] STAR_RATING_PATH = new String[] {"hotelInfo", "hotelStarRating"};
	
	private static final String[] HOTEL_SITE_URL_PATH = {"hotelUrls", "hotelInfositeUrl"};
	
	@Override
	protected HotelOffer createObject() {
		return new HotelOffer();
	}
	
	@Override
	protected Class<HotelOffer> getType() {
		return HotelOffer.class;
	}

	@Override
	protected HotelOffer populatOfferInfo(JsonNode hotelOfferRootNode, HotelOffer offer) {
		
		JsonNode hotelNameNode = applyPath(HOTEL_NAME_PATH, hotelOfferRootNode);
		if(hotelNameNode != null) {
			offer.setHotelName(hotelNameNode.asText());
		}
		
		extractHotelURL(hotelOfferRootNode, offer);
		extractHotelStarRating(hotelOfferRootNode, offer);
		return offer;
	}

	private void extractHotelStarRating(JsonNode hotelOfferRootNode, HotelOffer offer) {

		JsonNode hotelRatingNode = applyPath(STAR_RATING_PATH, hotelOfferRootNode);
		if(hotelRatingNode != null) {
			offer.setStarRating(hotelRatingNode.asDouble());
		}
	}

	private void extractHotelURL(JsonNode hotelOfferRootNode, HotelOffer offer) {
		JsonNode hotelURLNode = applyPath(HOTEL_SITE_URL_PATH, hotelOfferRootNode);
		if(hotelURLNode != null) {
			String hotelURL = hotelURLNode.asText();
			try {
				hotelURL = java.net.URLDecoder.decode(hotelURL, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				//consider the URL decoded
				e.printStackTrace();
			}
			
			offer.setHotelURL(hotelURL);			
		}
	}

	@Override
	protected String[] getImagePath() {
		return IMAGE_PATH;
	}

	@Override
	protected String[] getPricePath() {
		return PRICE_PATH;
	}

	@Override
	protected String getOfferTypeNodeName() {
		return "Hotel";
	}
	
	
}
