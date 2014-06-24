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

@ManagedBean(name = "cityController")
@ViewScoped
@Component
@Controller
public class CityController {

	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private CountryService countryService;
	
	/*@ManagedProperty(value = "countryController")
	private CountryController countryController;*/

	private List<City> cities = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		cities = cityRepository.findAll();
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public List<City> findByCountryName(String countryName) {
		Country country = countryService.findByName(countryName);
		return cityRepository.findByCountry(country);
	}
	
}
