package com.epam.traveling.services;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.traveling.entities.City;
import com.epam.traveling.entities.Country;
import com.epam.traveling.repositories.CityRepository;
import com.epam.traveling.repositories.CountryRepository;

@Controller
@Service()
@Transactional
public class CityService {

	@Autowired
	CityRepository cityRepository;
	
	@Autowired
	CountryRepository countryRepository;
	
	public List<City> findAll() {
		return cityRepository.findAll();
	}
	
	@Transactional
	public void save(City city) {
		Country country = countryRepository.findOne(city.getCountry().getId());
		city.setCountry(country);
		cityRepository.save(city);
	}
	
	public void saveAll(Collection<City> cities) {
		for (City city : cities) {
			cityRepository.save(city);
		}
	}
	
	public City findById(Integer id) {
		if(id == null || id < 1) {
			throw new RuntimeException("Wrong id: " + id);
		}
		return cityRepository.findOne(id);
	}
	
	public City findByName(String cityName) {
		return cityRepository.findByName(cityName);
	}
	
	public List<City> findByCountry(Country country) {
		return cityRepository.findByCountry(country);
	}
}
