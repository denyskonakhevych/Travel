package com.epam.traveling.web;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.traveling.entities.Analitic;
import com.epam.traveling.entities.Booking;
import com.epam.traveling.entities.Tour;
import com.epam.traveling.entities.User;
import com.epam.traveling.repositories.UserRepository;
import com.epam.traveling.services.AnaliticService;
import com.epam.traveling.services.BookingService;
import com.epam.traveling.services.UserService;

@ManagedBean(name = "tasksController")
@ViewScoped
@Component
public class TasksController {

	@Autowired
	@ManagedProperty(value = "#{authorizationController}")
	private AuthorizationController authorizationController;

	@Autowired
	BookingService bookingService;

	@Autowired
	AnaliticService analiticService;

	@Autowired
	UserService userService;

	private List<Booking> bookings;

	public List<Booking> getBookings() {
		bookings = bookingService.findAll();
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	@PostConstruct
	public void init() {
	}

	public List<Booking> getBookingsForUser(Integer id) {
		User user = userService.find(id);
		return bookingService.findByUser(user);
	}

	public void complete(Integer bookingId) {
		if (authorizationController.getIsAdmin()) {
			Booking booking = bookingService.find(bookingId);
			Analitic analitic = analiticService.find(authorizationController
					.getPerson().getPersonId());
			booking.setStatus("completed");
			for (Tour tour : booking.getTours()) {
				tour.setStatus("taken");
			}
			booking.setManagedBy(analitic);
			bookingService.save(booking);
		}

	}

	public void reject(Integer bookingId) {
		if (authorizationController.getIsAdmin()) {
			Booking booking = bookingService.find(bookingId);
			Analitic analitic = analiticService.find(authorizationController
					.getPerson().getPersonId());
			booking.setStatus("rejected");
			for (Tour tour : booking.getTours()) {
				tour.setStatus("avaliable");
			}
			booking.setManagedBy(analitic);
			bookingService.save(booking);
		}
	}

}
