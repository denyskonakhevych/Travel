package com.epam.traveling.services;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.traveling.entities.Country;
import com.epam.traveling.repositories.CountryRepository;

@Service
@Component("countryService")
@Transactional
public class CountryService {

	@Autowired
	CountryRepository countryRepository;
	
	public void remove(Country country) {
		countryRepository.delete(country);
	}
	
	public List<Country> findAll() {
		return countryRepository.findAll();
	}

	public void save(Country country) {
		countryRepository.save(country);
	}
	
	public void saveAll(Collection<Country> countries) {
		for (Country country : countries) {
			countryRepository.save(country);
		}
	}
	
	public Country findById(Integer id) {
		if(id == null || id < 1) {
			throw new RuntimeException("Wrong id: " + id);
		}
		return countryRepository.findOne(id);
	}
	
	public Country findByName(String countryName) {
		return countryRepository.findByName(countryName);
	}
}
