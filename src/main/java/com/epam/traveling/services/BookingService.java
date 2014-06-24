package com.epam.traveling.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.traveling.entities.Analitic;
import com.epam.traveling.entities.Booking;
import com.epam.traveling.entities.Country;
import com.epam.traveling.entities.Hotel;
import com.epam.traveling.entities.Tour;
import com.epam.traveling.entities.User;
import com.epam.traveling.repositories.BookingRepository;
import com.epam.traveling.repositories.CountryRepository;
import com.epam.traveling.repositories.HotelRepository;
import com.epam.traveling.repositories.TourRepository;
import com.epam.traveling.repositories.UserRepository;

@Controller
@Service
@Transactional
public class BookingService {

	@Autowired
	HotelRepository hotelRepository;

	@Autowired
	CountryRepository countryRepository;

	@Autowired
	BookingRepository bookingRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	TourRepository tourRepository;

	@Autowired
	AnaliticService analiticService;

	@Transactional
	public void save(Booking booking) {
		// Country country =
		// countryRepository.findOne(hotel.getCountry().getId());
		// hotel.setCountry(country);
		User user = userRepository.findOne(booking.getOrderedBy().getPersonId());
		booking.setOrderedBy(user);

		if (booking.getManagedBy() != null) {
			Analitic analitic = analiticService.find(booking.getManagedBy().getPersonId());
			booking.setManagedBy(analitic);
		}

		List<Tour> tours = new ArrayList<>();
		for (Tour tour : booking.getTours()) {
			Tour loadedTour = tourRepository.findOne(tour.getId());
			loadedTour.setStatus(tour.getStatus());
			tours.add(loadedTour);
		}
		booking.setTours(tours);
		bookingRepository.save(booking);
	}
	
	@Transactional
	public void makeOrder(Booking booking) {
		
		for(Tour tour : booking.getTours()) {
			if(!tourRepository.findOne(tour.getId()).getStatus().equals("avaliable")) {
				throw new RuntimeException("Some tours are already taken");
			}
		}
		for (Tour tour : booking.getTours()) {
			tour.setStatus("submited");
		}
		booking.setStatus("submited");
		save(booking);
	}

	public Booking find(Integer id) {
		return bookingRepository.findOne(id);
	}

	public List<Booking> findAll() {
		return bookingRepository.findAll();
	}
	
	public List<Booking> findByUser(User user) {
		return bookingRepository.findByUser(user);
	}

	@Transactional
	public void complete(Booking booking) {
		bookingRepository.complete(booking);
	}

}
