package com.epam.traveling.services;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.traveling.entities.City;
import com.epam.traveling.entities.Hotel;
import com.epam.traveling.entities.Tour;
import com.epam.traveling.repositories.CityRepository;
import com.epam.traveling.repositories.HotelRepository;
import com.epam.traveling.repositories.TourRepository;

@Service
@Transactional
public class TourService {

	@Autowired
	TourRepository tourRepository;
	
	@Autowired
	HotelRepository hotelRepository;
	
	@Autowired
	CityRepository cityRepository;
	
	public List<Tour> findInBounds(int min, int count) {
		Pageable window = new PageRequest(min, count);
		Page<Tour> pageTours = tourRepository.findAll(window);
		return pageTours.getContent();
	}
	
	public List<Tour> findAll() {
		return tourRepository.findAll();
	}
	
	public List<Tour> getAvaliable() {
		return tourRepository.getAvaliable();
	}
	
	public long count() {
		return tourRepository.count();
	}

	@Transactional
	public void save(Tour tour) {
		Hotel hotel = hotelRepository.findOne(tour.getHotel().getId());
		hotel.setCity(cityRepository.findOne(hotel.getCity().getId()));
		tour.setHotel(hotel);
		
		tour.setDepartueFrom(cityRepository.findOne(tour.getDepartueFrom().getId()));
		tourRepository.save(tour);
	}
	
	@Transactional
	public void saveAll(Collection<Tour> tours) {
		for (Tour tour : tours) {
			tourRepository.save(tour);
		}
	}
	
	@Transactional
	public void delete(Tour tour) {
		tourRepository.delete(tour);
	}
	
	public Tour findById(Integer id) {
		if(id == null || id < 1) {
			throw new RuntimeException("Wrong id: " + id);
		}
		return tourRepository.findOne(id);
	}
}
