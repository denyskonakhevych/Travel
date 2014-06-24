package com.epam.traveling.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.traveling.entities.Country;
import com.epam.traveling.entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {

	/*public List<Hotel> findByCountryId(int countryId);
	
	public List<Hotel> findByCountryName(String countryName);
	
	public List<Hotel> findByCountry(Country country);*/
	
	public Hotel findByName(String hotelName);
}
