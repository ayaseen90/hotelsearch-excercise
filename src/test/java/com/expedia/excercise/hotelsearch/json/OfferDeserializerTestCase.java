package com.expedia.excercise.hotelsearch.json;

import org.junit.Test;

import com.expedia.excercise.hotelsearch.model.Offer;

public class OfferDeserializerTestCase {
	
	String testCase = "{\r\n" + 
			"    \"offerInfo\": {\r\n" + 
			"        \"siteID\": \"1\",\r\n" + 
			"        \"language\": \"en_US\",\r\n" + 
			"        \"currency\": \"USD\"\r\n" + 
			"    },\r\n" + 
			"    \"userInfo\": {\r\n" + 
			"        \"persona\": {\r\n" + 
			"            \"personaType\": \"OTHERS\"\r\n" + 
			"        },\r\n" + 
			"        \"userId\": \"anas\"\r\n" + 
			"    },\r\n" + 
			"    \"offers\": {\r\n" + 
			"        \"Hotel\": [\r\n" + 
			"            {\r\n" + 
			"                \"offerDateRange\": {\r\n" + 
			"                    \"travelStartDate\": [\r\n" + 
			"                        2017,\r\n" + 
			"                        11,\r\n" + 
			"                        10\r\n" + 
			"                    ],\r\n" + 
			"                    \"travelEndDate\": [\r\n" + 
			"                        2017,\r\n" + 
			"                        11,\r\n" + 
			"                        11\r\n" + 
			"                    ],\r\n" + 
			"                    \"lengthOfStay\": 1\r\n" + 
			"                },\r\n" + 
			"                \"destination\": {\r\n" + 
			"                    \"regionID\": \"375\",\r\n" + 
			"                    \"associatedMultiCityRegionId\": \"375\",\r\n" + 
			"                    \"longName\": \"Amman (and vicinity), Jordan\",\r\n" + 
			"                    \"shortName\": \"Amman\",\r\n" + 
			"                    \"country\": \"Jordan\",\r\n" + 
			"                    \"city\": \"Amman\",\r\n" + 
			"                    \"tla\": \"AMM\",\r\n" + 
			"                    \"nonLocalizedCity\": \"Amman\"\r\n" + 
			"                },\r\n" + 
			"                \"hotelInfo\": {\r\n" + 
			"                    \"hotelId\": \"17707061\",\r\n" + 
			"                    \"hotelName\": \"Panda Hotel Apartments\",\r\n" + 
			"                    \"localizedHotelName\": \"Panda Hotel Apartments\",\r\n" + 
			"                    \"hotelDestination\": \"Amman\",\r\n" + 
			"                    \"hotelDestinationRegionID\": \"6269089\",\r\n" + 
			"                    \"hotelLongDestination\": \"Amman,JOR\",\r\n" + 
			"                    \"hotelStreetAddress\": \"Prince Tagreed St. Sweifieh\",\r\n" + 
			"                    \"hotelCity\": \"Amman\",\r\n" + 
			"                    \"hotelProvince\": \"\",\r\n" + 
			"                    \"hotelCountryCode\": \"JOR\",\r\n" + 
			"                    \"hotelLatitude\": 31.955609,\r\n" + 
			"                    \"hotelLongitude\": 35.863257,\r\n" + 
			"                    \"hotelStarRating\": \"3.0\",\r\n" + 
			"                    \"hotelGuestReviewRating\": 4.57,\r\n" + 
			"                    \"hotelReviewTotal\": 7,\r\n" + 
			"                    \"hotelImageUrl\": \"https://images.trvl-media.com/hotels/18000000/17710000/17707100/17707061/266e8e52_t.jpg\",\r\n" + 
			"                    \"isOfficialRating\": false\r\n" + 
			"                },\r\n" + 
			"                \"hotelPricingInfo\": {\r\n" + 
			"                    \"averagePriceValue\": 37.58,\r\n" + 
			"                    \"totalPriceValue\": 37.58,\r\n" + 
			"                    \"crossOutPriceValue\": 44.21,\r\n" + 
			"                    \"originalPricePerNight\": 44.21,\r\n" + 
			"                    \"percentSavings\": 15,\r\n" + 
			"                    \"drr\": false\r\n" + 
			"                },\r\n" + 
			"                \"hotelUrls\": {\r\n" + 
			"                    \"hotelInfositeUrl\": \"https%3A%2F%2Fwww.expedia.com%2Fgo%2Fhotel%2Finfo%2F17707061%2F2017-11-10%2F2017-11-11\",\r\n" + 
			"                    \"hotelSearchResultUrl\": \"https%3A%2F%2Fwww.expedia.com%2Fgo%2Fhotel%2Fsearch%2FDestination%2F2017-11-10%2F2017-11-11%3FSearchType%3DDestination%26CityName%3DAmman%26RegionId%3D375%26Selected%3D17707061\"\r\n" + 
			"                }\r\n" + 
			"            }\r\n" + 
			"        ]\r\n" + 
			"    }\r\n" + 
			"}";
	
	@Test
	public void testDeserializeOffer() throws Exception {
		HoteDeserializer deserializer = new HoteDeserializer();
		Offer offer = deserializer.deserialize(testCase).get(0);
		
		System.out.println(offer);
	}

}
