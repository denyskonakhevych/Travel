package com.epam.traveling.services;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.traveling.entities.Country;
import com.epam.traveling.entities.Hotel;
import com.epam.traveling.repositories.CountryRepository;
import com.epam.traveling.repositories.HotelRepository;

@Controller
@Service
@Transactional
public class HotelService {

	@Autowired
	HotelRepository hotelRepository;
	
	@Autowired
	CountryRepository countryRepository;
	
	/*@Transactional
	public void saveAll(Collection<Hotel> hotels) {
		for (Hotel hotel : hotels) {
			Country country = countryRepository.findOne(hotel.getCountry().getId());
			assert(country != null);
			hotel.setCountry(country);
			hotelRepository.saveAndFlush(hotel);
		}
	}
	
	@Transactional
	public void save(Hotel hotel) {
		Country country = countryRepository.findOne(hotel.getCountry().getId());
		hotel.setCountry(country);
		hotelRepository.save(hotel);
	}*/
	
	@Transactional
	public void remove(Hotel hotel) {
		hotelRepository.delete(hotel);
	}
	
	/*public List<Hotel> findByCountryId (Integer countryId) {
		if (countryId == null || countryId < 1) {
			throw new RuntimeException("Wrong countryId: " + countryId);
		}
		Country country = countryRepository.findOne(countryId);
		return hotelRepository.findByCountry(country);
	}*/
	
	/*public List<Hotel> findByCountryName (String countryName) {
		if (countryName == null) {
			throw new RuntimeException("Wrong countryName: " + countryName);
		}
		//return hotelRepository.findByCountryName(countryName);
		Country country = countryRepository.findByName(countryName);
		return hotelRepository.findByCountry(country);
	}
	
	public List<Hotel> findByCountry (Country country) {
		if (country == null) {
			throw new RuntimeException("Wrong country: " + country);
		}
		Country targetCountry = countryRepository.findOne(country.getId());
		return hotelRepository.findByCountry(targetCountry);
	}*/
	
	public Hotel findById (Integer hotelId) {
		if (hotelId == null || hotelId < 1) {
			throw new RuntimeException("Wrong countryId: " + hotelId);
		}
		Hotel hotel = hotelRepository.findOne(hotelId); // TODO: delete method
		return hotel;
	}
	
	public Hotel findByName (String hotelName) {
		if (hotelName == null || hotelName.isEmpty()) {
			throw new RuntimeException("Wrong hotelName: " + hotelName);
		}
		Hotel hotel = hotelRepository.findByName(hotelName); // TODO: delete method
		return hotel;
	}
	
	@Transactional
	public List<Hotel> findAll () {
		return hotelRepository.findAll();
	}
}
