package com.expedia.excercise.hotelsearch.json;

import java.util.List;

public interface JsonDeserializer<T> {

	public List<T> deserialize(String json) throws Exception;
}
