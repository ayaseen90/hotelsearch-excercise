package com.expedia.excercise.hotelsearch.model;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonDeserialize;

import com.expedia.excercise.hotelsearch.json.DateDeserializer;

/**
 * {@link OfferDateRange} represents a common attribute among all the Offers.
 * It contains the start date and the end date of the {@link Offer}
 * @author Anas
 *
 */

@JsonDeserialize(using = DateDeserializer.class)
public class OfferDateRange {
	
	private Date travelStartDate;
	private Date travelEndDate;
	public Date getTravelStrartDate() {
		return travelStartDate;
	}
	public void setTravelStartDate(Date travelStrartDate) {
		this.travelStartDate = travelStrartDate;
	}
	public Date getTravelEndDate() {
		return travelEndDate;
	}
	public void setTravelEndDate(Date travelEndDate) {
		this.travelEndDate = travelEndDate;
	}
}
