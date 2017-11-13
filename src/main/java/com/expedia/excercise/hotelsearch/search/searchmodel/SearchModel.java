package com.expedia.excercise.hotelsearch.search.searchmodel;

import com.expedia.excercise.hotelsearch.model.Offer;
import com.expedia.excercise.hotelsearch.search.ServiceException;
import com.expedia.excercise.hotelsearch.search.result.SearchResult;

public interface SearchModel {
	
	SearchResult<? extends Offer> execute() throws ServiceException;
}
