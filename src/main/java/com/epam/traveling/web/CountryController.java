package com.epam.traveling.web;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.epam.traveling.entities.City;
import com.epam.traveling.entities.Country;
import com.epam.traveling.repositories.CityRepository;
import com.epam.traveling.services.CityService;
import com.epam.traveling.services.CountryService;

@ManagedBean(name = "countryController")
@ViewScoped
@Component
@Controller
public class CountryController {
	
	@Autowired
	private CountryService countryService;

	private List<Country> countries = new ArrayList<>();
	
	private Country selectedCountry;
	
	@PostConstruct
	public void init() {
		countries = countryService.findAll();
		selectedCountry = new Country();
	}

	public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}

	public Country getSelectedCountry() {
		return selectedCountry;
	}

	public void setSelectedCountry(Country selectedCountry) {
		this.selectedCountry = selectedCountry;
	}
	
	public void setCountryById(){
		if (selectedCountry.getId() != null) {
			selectedCountry = countryService.findById(selectedCountry.getId());
		}
	}
}
