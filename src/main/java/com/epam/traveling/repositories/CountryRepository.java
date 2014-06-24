package com.epam.traveling.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.traveling.entities.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {

	public Country findByName(String countryName);
}
