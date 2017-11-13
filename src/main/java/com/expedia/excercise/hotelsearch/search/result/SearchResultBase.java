package com.expedia.excercise.hotelsearch.search.result;

import java.util.Iterator;
import java.util.List;

import com.expedia.excercise.hotelsearch.json.OfferDeserializer;
import com.expedia.excercise.hotelsearch.model.Offer;
import com.expedia.excercise.hotelsearch.search.result.SearchResult;

public abstract class SearchResultBase<T extends Offer> implements SearchResult<T>{

	private String message;
	private List<T> results;
	
	public SearchResultBase(String message) {
		this.message = message;
	}
	
	@Override
	public Iterator<T> iterator() {
		return getAllItems().iterator();
	}

	@Override
	public int count() {
		return getAllItems().size();
	}

	@Override
	public List<T> getAllItems() {
		if(results != null) {
			return results;
		}
		synchronized(this) {
			if(results == null) {
				OfferDeserializer<T> deserializer = createDeserializer();
				
				try {
					results = deserializer.deserialize(message);
				} catch (Exception e) {
					e.printStackTrace();
					// should throw an exception here
				}
			}
		}
		
		return results;
	}

	protected abstract OfferDeserializer<T> createDeserializer();

	@Override
	public T getItem(int index) {
		return null;
	}

}
