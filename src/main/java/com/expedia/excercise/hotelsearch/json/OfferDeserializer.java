package com.expedia.excercise.hotelsearch.json;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.deser.std.StdDeserializer;

import com.expedia.excercise.hotelsearch.model.Offer;
import com.expedia.excercise.hotelsearch.model.OfferDateRange;

public abstract class OfferDeserializer<T extends Offer> extends StdDeserializer<T> implements JsonDeserializer<T> {

	protected OfferDeserializer(Class<?> vc) {
		super(vc);
	}
	
	
	static final ObjectMapper MAPPER = new ObjectMapper();
	@Override
	public List<T> deserialize(String json) throws Exception {
		JsonNode jsonNode = MAPPER.readTree(json);
		jsonNode = jsonNode.get("offers");
		
		if(jsonNode != null) {
			jsonNode = jsonNode.get(getOfferTypeNodeName());
		}
		List<T> offers = new LinkedList<>();
		if(jsonNode == null) {
			return offers;
		}
		for(JsonNode node : jsonNode) {
			offers.add(MAPPER.readValue(node, getType()));
		}
		
		return offers;
	}

	protected abstract Class<T> getType();

	protected abstract String getOfferTypeNodeName();
	@Override
	public T deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		
		JsonNode node = jp.getCodec().readTree(jp);
		
		ObjectMapper mapper = new ObjectMapper();
		
		T offer = createObject();
		OfferDateRange dateRange = mapper.readValue(node.get("offerDateRange"), OfferDateRange.class);
		
		
		offer.withDestination(node.get("destination").get("city").asText())
		.withOfferDateRange(dateRange);
		
		JsonNode imageUrl = applyPath(getImagePath(), node);
		if(imageUrl != null && imageUrl.isTextual()) {
			offer.setImage(imageUrl.asText());
		}
		
		JsonNode price = applyPath(getPricePath(), node);
		if(price != null && price.isDouble()) {
			offer.setPrice(price.asDouble());
		}
		
		offer = populatOfferInfo(node, offer);
		
		return offer;
	}

	protected final JsonNode applyPath(String[] jsonPath, JsonNode node) {
		if(jsonPath == null) {
			return null;
		}
		JsonNode n = node;
		for(String nodeName : jsonPath) {
			if(n == null) {
				break;
			}
			n = n.get(nodeName);
		}
		return n;
	}

	protected abstract T populatOfferInfo(JsonNode node, T offer);

	protected abstract T createObject();
	
	protected abstract String[] getImagePath();
	
	protected abstract String[] getPricePath();

}
