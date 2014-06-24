package com.epam.traveling.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.epam.traveling.entities.Booking;
import com.epam.traveling.entities.Tour;
import com.epam.traveling.entities.User;
import com.epam.traveling.services.BookingService;
import com.epam.traveling.services.TourService;
import com.epam.traveling.services.UserService;

@ManagedBean(name = "cartController")
@SessionScoped
@Component
@Controller
public class CartController {

	@Autowired
	TourService tourService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	BookingService bookingService;
	
	@Autowired
	@ManagedProperty(value="#{authorizationController}")
    private AuthorizationController authorizationController;
	
	private static Logger log = Logger.getLogger(CartController.class.getName());
	
	private Tour selectedTour;

	private List<Tour> tours = new ArrayList<>();
	
	String roomType;
	
	private float totalPrice;
	
	private int numberOfPeople;

	@PostConstruct
	public void init() {

	}

	public Tour getSelectedTour() {
		return selectedTour;
	}

	public void setSelectedTour(Tour selectedTour) {
		this.selectedTour = selectedTour;
	}

	public List<Tour> getTours() {
		return tours;
	}

	public void setTours(List<Tour> tours) {
		this.tours = tours;
	}

	public AuthorizationController getAuthorizationController() {
		return authorizationController;
	}

	public void setAuthorizationController(
			AuthorizationController authorizationController) {
		this.authorizationController = authorizationController;
	}

	public synchronized void addTour(String tId) {
		try {
			int tourId = Integer.parseInt(tId);
			Tour tour = tourService.findById(tourId);
			if(!alreadyInCart(tour)) {
				tours.add(tour);
			} else {
				// Already in cart
			}
		} catch (NumberFormatException e) {

		}
	}
	
	private boolean alreadyInCart(Tour checkTour) {
		for (Tour tour : tours) {
			if (tour.getId().equals(checkTour.getId())) {
				return true;
			}
		}
		return false;
	}
	
	public synchronized float getTotalPrice() {
		totalPrice = 0;
		for (Tour tour : tours) {
			totalPrice += tour.getPrice();
		}
		if (tours.size() > 1) {
			totalPrice *= 0.95f;
		}
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public void removeTour(String tId) {
		try {
			int tourId = Integer.parseInt(tId);
			Iterator<Tour> iter = tours.iterator();
			while(iter.hasNext()) {
				if(iter.next().getId() == tourId) {
					iter.remove();
				}
			}
		} catch (NumberFormatException e) {

		}
	}
	
	/*public void change() {
		if(selectedTour != null) {
			tourService.save(selectedTour);
		}
	}
	*/
	public synchronized void makeOrder(String userId) {
		
		if(authorizationController.getIsLogedIn()) {
			Booking booking = new Booking();
			
			User user = userService.find(authorizationController.getPerson().getPersonId());
			booking.setOrderedBy(user);
			booking.setTours(tours);
			booking.setTotalPrice(getTotalPrice());
			try {
				bookingService.makeOrder(booking);
				tours.clear();
			} catch (RuntimeException ex) {
				
				UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
				UIComponent component = viewRoot.findComponent("cartForm");
				String loginFormId = component.getClientId();
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Order Error", ex.getMessage());
	            FacesContext.getCurrentInstance().addMessage(loginFormId, message);
				log.info(ex.getMessage());
			}
		}
	}
	
	public synchronized void setSelectedTourById(String tId) {
		try {
			int tourId = Integer.parseInt(tId);
			selectedTour = tourService.findById(tourId);
		} catch (NumberFormatException e) {

		}
	}

}
