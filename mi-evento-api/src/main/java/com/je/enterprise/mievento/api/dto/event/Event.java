package com.je.enterprise.mievento.api.dto.event;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.je.enterprise.mievento.api.dto.event.eventWithplace.EventWithPlaceAndPresent;
import com.je.enterprise.mievento.api.dto.provider.Provider;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "clazz", visible = true)
@JsonSubTypes({ @Type(value = EventWithPlaceAndPresent.class, name = "EventWithPlaceAndPresent")})
public class Event {

	private String name;
	private Date initialDate;
	private Date finalDate;
	private List<Guest> guests;
	private List<Task> tasks;
	private List<Provider> providers;
	private EventType type;
	
	public Event() {
	}
	
	public Event(String name, Date initialDate,Date finalDate,
			List<Guest> guests,List<Task> tasks, List<Provider> providers,EventType type) {
		this.name = name;
		this.initialDate = initialDate;
		this.finalDate = finalDate;
		this.guests = guests;
		this.tasks = tasks;
		this.providers = providers;
		this.type = type;
	}


	public List<Guest> getGuests() {
		return guests;
	}

	public void setGuests(List<Guest> guests) {
		this.guests = guests;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	
	public List<Provider> getProviders() {
		return providers;
	}

	public void setProviders(List<Provider> providers) {
		this.providers = providers;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	public Date getInitialDate() {
		return initialDate;
	}

	public void setInitialDate(Date initialDate) {
		this.initialDate = initialDate;
	}

	public Date getFinalDate() {
		return finalDate;
	}

	public void setFinalDate(Date finalDate) {
		this.finalDate = finalDate;
	}

}
