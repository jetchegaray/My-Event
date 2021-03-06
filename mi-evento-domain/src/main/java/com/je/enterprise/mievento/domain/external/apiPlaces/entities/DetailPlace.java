package com.je.enterprise.mievento.domain.external.apiPlaces.entities;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.Validate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Function;
import com.google.common.collect.Lists;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DetailPlace {

	private String id;
	private String reference;
	private String name;
	
	@JsonProperty("address_components")
	private List<AddressComponent> address;
	
	@JsonProperty("formatted_phone_number")
	private String phone;
	
	@JsonProperty("international_phone_number")
	private String internationalPhone;
	
	private List<Photo> photos;
	private List<String> types;	
	//se agregan al convertir las referencias.
	private List<String> photoLocations;	
	
	private Geometry geometry;
	private Double rating;
	private List<DetailPlaceReview> reviews;
	
	public DetailPlace() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AddressComponent> getAddress() {
		return address;
	}

	public void setAddress(List<AddressComponent> address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getInternationalPhone() {
		return internationalPhone;
	}

	public void setInternationalPhone(String internationalPhone) {
		this.internationalPhone = internationalPhone;
	}

	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

	public List<String> getTypes() {
		return types;
	}

	public void setTypes(List<String> types) {
		this.types = types;
	}

	public Geometry getGeometry() {
		return geometry;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public List<DetailPlaceReview> getReviews() {
		return reviews;
	}

	public void setReviews(List<DetailPlaceReview> reviews) {
		this.reviews = reviews;
	}
	
	public List<String> getPhotoReferences(){
		try {
			Validate.notNull(this.photos);
		} catch (Exception e) {
			return Collections.emptyList();
		}
		return Lists.transform(this.photos, new Function<Photo, String>() {
			@Override
			public String apply(Photo input) {
				return input.getReference();
			}
		});
	}

	public List<String> getPhotoLocations() {
		return photoLocations;
	}

	public void setPhotoLocations(List<String> photoLocations) {
		this.photoLocations = photoLocations;
	}
	
	
}
