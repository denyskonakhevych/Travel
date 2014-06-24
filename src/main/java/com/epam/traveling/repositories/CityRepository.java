package com.epam.traveling.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.traveling.entities.City;
import com.epam.traveling.entities.Country;

public interface CityRepository extends JpaRepository<City, Integer> {

	public City findByName(String cityName);
	
	public List<City> findByCountry(Country country);
}
