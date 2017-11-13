package com.expedia.excercise.hotelsearch.model;



/**
 * An Offer represents the common attributes and behavior for all the Deal types
 * @author Anas
 *
 */
public abstract class Offer {
	
	private double price;
	private OfferDateRange offerDateRange;
	private String image;
	private String destination;
	private String dealType;
	
	public double getPrice() {
		return price;
	}
	
	public OfferDateRange getOfferDateRange() {
		return offerDateRange;
	}
	
	public String getDestination() {
		return destination;
	}
	
	public String getImage() {
		return image;
	}
	
	public String getDealType() {
		return dealType;
	}

	public void setDealType(String dealType) {
		this.dealType = dealType;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public void setOfferDateRange(OfferDateRange offerDateRange) {
		this.offerDateRange = offerDateRange;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	public Offer withDestination(String destination) {
		setDestination(destination);
		return this;
	}
	
	public Offer withPrice(double price) {
		this.price = price;
		return this;
	}

	public Offer withOfferDateRange(OfferDateRange offerDateRange) {
		this.offerDateRange = offerDateRange;
		return this;
	}

	public Offer withImage(String image) {
		this.image = image;
		return this;
	}
	
	
}
