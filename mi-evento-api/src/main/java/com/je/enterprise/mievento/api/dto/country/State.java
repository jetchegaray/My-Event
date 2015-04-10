package com.je.enterprise.mievento.api.dto.country;

import java.util.List;

public class State {

	private String name;
	private List<City> cities;

	public State() {
	}
	
	public State(String name, List<City> cities) {
		this.name = name;
		this.cities = cities;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}
	
	
}
