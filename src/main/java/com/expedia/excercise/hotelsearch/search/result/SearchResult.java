package com.expedia.excercise.hotelsearch.search.result;

import java.util.List;

import com.expedia.excercise.hotelsearch.model.Offer;

/**
 * An Interface representing the SearchResult Returned from the target service.
 * This Interface extends <code>Iterable</code> to simplify iterating over the returned results
 * @author Anas
 *
 */
public interface SearchResult<T extends Offer> extends Iterable<T>{
	
	int count();
	
	List<T> getAllItems();
	T getItem(int order);
	
}
