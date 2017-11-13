package com.expedia.excercise.hotelsearch.json;

import java.io.IOException;
import java.util.Calendar;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

import com.expedia.excercise.hotelsearch.Utilities;
import com.expedia.excercise.hotelsearch.model.OfferDateRange;

public class DateDeserializer extends JsonDeserializer<OfferDateRange>{

	private static final String[] DATE_RANGE_FIELD_NAMES = new String[] {"travelStartDate", "travelEndDate"};
	
	@Override
	public OfferDateRange deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		

		JsonNode node = jp.getCodec().readTree(jp);
		OfferDateRange dateRange = new OfferDateRange();
		for(String s : DATE_RANGE_FIELD_NAMES) {
			JsonNode datefieldNode = node.get(s);
			
			int year = datefieldNode.get(0).asInt();
			int month = datefieldNode.get(1).asInt();
			int day = datefieldNode.get(2).asInt();
			
			Calendar c = Calendar.getInstance();
			
			c.set(year, month, day);
			
			String setterMethodName = Utilities.generateSetterMathodName(s);
			Utilities.invokePublicVoidMethod(dateRange, setterMethodName, new Object[] {c.getTime()});
			
		}
		
		return dateRange;
	}

}
