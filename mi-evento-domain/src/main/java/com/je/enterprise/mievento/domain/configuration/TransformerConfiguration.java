package com.je.enterprise.mievento.domain.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.je.enterprise.mievento.api.dto.country.City;
import com.je.enterprise.mievento.api.dto.country.Country;
import com.je.enterprise.mievento.api.dto.country.State;
import com.je.enterprise.mievento.api.dto.event.Event;
import com.je.enterprise.mievento.api.dto.event.Guest;
import com.je.enterprise.mievento.api.dto.event.Task;
import com.je.enterprise.mievento.api.dto.event.eventWithplace.Presents;
import com.je.enterprise.mievento.api.dto.place.ControlContextTable;
import com.je.enterprise.mievento.api.dto.provider.Provider;
import com.je.enterprise.mievento.api.dto.provider.Review;
import com.je.enterprise.mievento.domain.entity.common.event.EventEntity;
import com.je.enterprise.mievento.domain.entity.common.event.GuestEntity;
import com.je.enterprise.mievento.domain.entity.common.event.ProviderEntity;
import com.je.enterprise.mievento.domain.entity.common.event.ProviderReviewEntity;
import com.je.enterprise.mievento.domain.entity.common.event.TaskEntity;
import com.je.enterprise.mievento.domain.entity.geo.CityEntity;
import com.je.enterprise.mievento.domain.entity.geo.CountryEntity;
import com.je.enterprise.mievento.domain.entity.geo.StateEntity;
import com.je.enterprise.mievento.domain.entity.place.ControlContextTableEntity;
import com.je.enterprise.mievento.domain.entity.wedding.PresentsEntity;
import com.je.enterprise.mievento.domain.external.apiPlaces.entities.DetailPlace;
import com.je.enterprise.mievento.domain.external.apiPlaces.transformer.ProviderPlacesTransformer;
import com.je.enterprise.mievento.domain.transformer.TransformerList;
import com.je.enterprise.mievento.domain.transformer.impl.CityGeoTransformer;
import com.je.enterprise.mievento.domain.transformer.impl.CommercialLocationTransformer;
import com.je.enterprise.mievento.domain.transformer.impl.ContextPlaceTransformer;
import com.je.enterprise.mievento.domain.transformer.impl.ContextTableTransformer;
import com.je.enterprise.mievento.domain.transformer.impl.CountryGeoTransformer;
import com.je.enterprise.mievento.domain.transformer.impl.EventTransformer;
import com.je.enterprise.mievento.domain.transformer.impl.GuestTransformer;
import com.je.enterprise.mievento.domain.transformer.impl.LocationTransformer;
import com.je.enterprise.mievento.domain.transformer.impl.PlaceTransformer;
import com.je.enterprise.mievento.domain.transformer.impl.PresentTransformer;
import com.je.enterprise.mievento.domain.transformer.impl.ProviderTransformer;
import com.je.enterprise.mievento.domain.transformer.impl.ReviewTransformer;
import com.je.enterprise.mievento.domain.transformer.impl.StateGeoTransformer;
import com.je.enterprise.mievento.domain.transformer.impl.TaskTransformer;

@Configuration
public class TransformerConfiguration {

	@Autowired
	private GuestTransformer guestTransformer;
	@Autowired
	private TaskTransformer taskTransformer;
	@Autowired
	private ReviewTransformer reviewTransformer;
	@Autowired
	private LocationTransformer locationTransformer;
	@Autowired
	private PresentTransformer presentTransformer;
	@Autowired
	private CommercialLocationTransformer commercialLocationTransformer;
	@Autowired
	private ProviderPlacesTransformer providerPlacesTransformer;
	@Autowired
	private CityGeoTransformer cityGeoTransformer;
	

	
	@Bean(name = "guestTransformerList")
	public TransformerList<GuestEntity, Guest> guestTransformerList(){
		return new TransformerList<GuestEntity, Guest>(this.guestTransformer);
	}
	
	@Bean(name = "taskTransformerList")
	public TransformerList<TaskEntity, Task> taskTransformerList(){
		return new TransformerList<TaskEntity, Task>(this.taskTransformer);
	}
	
	@Bean(name = "providerTransformerList")
	public TransformerList<ProviderEntity, Provider> providerTransformerList(){
		return new TransformerList<ProviderEntity, Provider>(this.providerTransformer());
	}
	
	@Bean(name = "presentTransformerList")
	public TransformerList<PresentsEntity, Presents> presentTransformerList(){
		return new TransformerList<PresentsEntity, Presents>(this.presentTransformer);
	}

	@Bean(name = "eventTransformer")
	public EventTransformer eventTransformer(){
		return new EventTransformer(this.guestTransformerList(),this.taskTransformerList(),this.providerTransformerList());
	}
	
	@Bean(name = "eventTransformerList")
	public TransformerList<EventEntity, Event> eventTransformerList(){
		return new TransformerList<EventEntity, Event>(this.eventTransformer());
	}
	
	
	@Bean(name = "providerPlacesTransformerList")
	public TransformerList<ProviderEntity, DetailPlace> providerPlacesTransformerList(){
		return new TransformerList<ProviderEntity, DetailPlace>(this.providerPlacesTransformer);
	}
	
	@Bean(name = "reviewTransformerList")
	public TransformerList<ProviderReviewEntity, Review> reviewTransformerList(){
		return new TransformerList<ProviderReviewEntity, Review>(this.reviewTransformer);
	}
	
	@Bean(name = "contextTableTransformer")
	public ContextTableTransformer contextTableTransformer(){
		return new ContextTableTransformer(this.guestTransformerList());
	}
	
	@Bean(name = "contextTableTransformerList")
	public TransformerList<ControlContextTableEntity, ControlContextTable> contextTableTransformerList(){
		return new TransformerList<ControlContextTableEntity, ControlContextTable>(this.contextTableTransformer());
	}
	
	@Bean(name = "contextPlaceTransformer")
	public ContextPlaceTransformer contextPlaceTransformer(){
		return new ContextPlaceTransformer(this.contextTableTransformerList());
	}
	
	
	@Bean(name = "providerTransformer")
	public ProviderTransformer providerTransformer(){
		return new ProviderTransformer(this.locationTransformer,this.reviewTransformerList());
	}
	
	@Bean(name = "placeTransformer")
	public PlaceTransformer placeTransformer(){
		return new PlaceTransformer(this.locationTransformer,this.reviewTransformerList(), this.contextPlaceTransformer());
	}
	
	@Bean(name = "cityTransformerList")
	public TransformerList<CityEntity, City> cityTransformerList(){
		return new TransformerList<CityEntity, City>(this.cityGeoTransformer);
	}
	
	@Bean(name = "stateTransformer")
	public StateGeoTransformer stateTransformer(){
		return new StateGeoTransformer(this.cityTransformerList());
	}

	@Bean(name = "stateTransformerList")
	public TransformerList<StateEntity, State> stateTransformerList(){
		return new TransformerList<StateEntity, State>(this.stateTransformer());
	}
	
	@Bean(name = "countryTransformer")
	public CountryGeoTransformer countryTransformer(){
		return new CountryGeoTransformer(this.stateTransformerList());
	}
	
	@Bean(name = "countryTransformerList")
	public TransformerList<CountryEntity, Country> countryTransformerList(){
		return new TransformerList<CountryEntity, Country>(this.countryTransformer());
	}
	
	

}
