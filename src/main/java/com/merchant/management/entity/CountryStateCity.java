package com.merchant.management.entity;

public class CountryStateCity {
	
	private String countryNames;
	private String stateNames;
	private String cityNames;
	
	
	public CountryStateCity(String countryNames, String stateNames, String cityNames) {
		super();
		this.countryNames = countryNames;
		this.stateNames = stateNames;
		this.cityNames = cityNames;
	}
	public String getCountryNames() {
		return countryNames;
	}
	public void setCountryNames(String countryNames) {
		this.countryNames = countryNames;
	}
	public String getStateNames() {
		return stateNames;
	}
	public void setStateNames(String stateNames) {
		this.stateNames = stateNames;
	}
	public String getCityNames() {
		return cityNames;
	}
	public void setCityNames(String cityNames) {
		this.cityNames = cityNames;
	}
	

}
