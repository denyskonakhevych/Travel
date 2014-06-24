package com.epam.traveling.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.traveling.entities.City;
import com.epam.traveling.entities.Country;
import com.epam.traveling.entities.Hotel;
import com.epam.traveling.entities.Tour;
import com.epam.traveling.services.CityService;
import com.epam.traveling.services.CountryService;
import com.epam.traveling.services.HotelService;
import com.epam.traveling.services.TourService;

@ManagedBean(name = "tourAdminController")
@ViewScoped
@Component
public class TourAdminController {

	@Autowired
	TourService tourService;

	@Autowired
	HotelService hotelService;

	@Autowired
	CityService cityService;

	@Autowired
	@ManagedProperty(value = "#{authorizationController}")
	private AuthorizationController authorizationController;

	private List<Tour> tours = new ArrayList<>();

	private Tour selectedTour;

	private Tour tour;

	private List<Hotel> hotels = new ArrayList<>();

	@PostConstruct
	public void init() {
		// selectedTour = new Tour();
		// tour = new Tour();
		initTour();
		tours = tourService.getAvaliable();
		hotels = hotelService.findAll();
	}

	public Tour getTour() {
		return tour;
	}

	public void setTour(Tour tour) {
		this.tour = tour;
	}

	public List<Tour> getTours() {
		// tours = tourService.findAll();
		return tours;
	}

	public void setTours(List<Tour> tours) {
		this.tours = tours;
	}

	public Tour getSelectedTour() {
		return selectedTour;
	}

	public void setSelectedTour(Tour selectedTour) {
		this.selectedTour = selectedTour;
	}

	public List<Hotel> getHotels() {
		return hotels;
	}

	public void setHotels(List<Hotel> hotels) {
		this.hotels = hotels;
	}

	public void selectTour() {
		tour = selectedTour;
	}

	public void saveTour() {
		if (authorizationController.getIsAdmin()) {
			tour.setHotel(hotelService.findByName(tour.getHotel().getName()));
			City city = cityService
					.findByName(tour.getDepartueFrom().getName());
			tour.setDepartueFrom(city);

			tour.setStatus("avaliable");
			tourService.save(tour);
			init();
		}
	}

	public void removeTour() {
		if (authorizationController.getIsAdmin()) {
			tourService.delete(tour);
			init();
		}
	}

	public void initTour() {
		City city = new City();
		city.setCountry(new Country());
		Hotel hotel = new Hotel();
		hotel.setCity(city);
		Date date = new Date();
		date.setDate(date.getDate() + 1);

		tour = new Tour();
		tour.setDepartueFrom(city);
		tour.setDepartueDate(new Date());
		tour.setMaxNumberOfAdults(1);
		tour.setMaxNumberOfChildren(0);
		tour.setNumberOfNights(7);
		tour.setPrice(0.01f);
		tour.setHotel(hotel);

		selectedTour = null;
	}

}
