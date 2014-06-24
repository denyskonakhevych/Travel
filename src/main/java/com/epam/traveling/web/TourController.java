package com.epam.traveling.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;
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

@ManagedBean(name = "mainPageController")
@ViewScoped
@Component
public class TourController {

	@Autowired
	TourService tourService;

	@Autowired
	CityService cityService;

	@Autowired
	HotelService hotelService;

	@Autowired
	CountryService countryService;
	
	CartController cartController;
	
	private LazyDataModel<Tour> lazyTours;

	private List<Tour> tours = new ArrayList<>();

	private List<City> cities = new ArrayList<>();

	private SelectItem[] foodOptions = { new SelectItem("", "Select"),
			new SelectItem("ALL"), new SelectItem("NB") };

	private SelectItem[] roomOptions = { new SelectItem("", "Select"),
			new SelectItem("STD"), new SelectItem("SV") };

	private String selectedTourId;

	private Tour selectedTour;

	List<Country> countries = new ArrayList<Country>();

	List<Hotel> hotels = new ArrayList<Hotel>();

	private SelectItem[] countryOptions;

	private SelectItem[] cityOptions;

	private SelectItem[] hotelOptions;

	private SelectItem[] starOptions = { new SelectItem("", "Select"),
			new SelectItem("1", "1"), new SelectItem("2", "2"),
			new SelectItem("3", "3"), new SelectItem("4", "4"),
			new SelectItem("5", "5") };

	List<Hotel> filteredHotels = new ArrayList<Hotel>();

	@PostConstruct
	public void init() {
		tours = tourService.getAvaliable();//tourService.findAll();
		cities = cityService.findAll();
		
		//lazyTours = new LazyTourDataModel();
		lazyTours = new LazyDataModel<Tour>() {
			
			private static final long serialVersionUID = -6993244105441825658L;

			@Override
			public List<Tour> load(int first, int pageSize,
					List<SortMeta> multiSortMeta, Map<String, String> filters) {
				this.setRowCount((int)tourService.count());
				return tourService.findInBounds(first / pageSize, pageSize);
			}

			@Override
			public List<Tour> load(int first, int pageSize, String sortField,
					SortOrder sortOrder, Map<String, String> filters) {
				this.setRowCount((int)tourService.count());
				return tourService.findInBounds(first / pageSize, pageSize);
			}
		};
		
		hotels = hotelService.findAll();
		countries = countryService.findAll();
		filteredHotels = hotelService.findAll();
		countryOptions = createFilterOptions(getCountryNames(countries));
		hotelOptions = createFilterOptions(getHotelNames(hotels));
		cityOptions = createFilterOptions(getCityNames(cities));
	}

	public LazyDataModel<Tour> getLazyTours() {
		return lazyTours;
	}

	public void setLazyTours(LazyDataModel<Tour> lazyTours) {
		this.lazyTours = lazyTours;
	}

	public CartController getCartController() {
		return cartController;
	}

	public void setCartController(CartController cartController) {
		this.cartController = cartController;
	}

	public List<Tour> getTours() {
		//tours = tourService.getAvaliable();
		return tours;
	}

	public void setTours(List<Tour> tours) {
		this.tours = tours;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	private SelectItem[] createFilterOptions(String[] data) {
		SelectItem[] options = new SelectItem[data.length + 1];

		options[0] = new SelectItem("", "Select");
		for (int i = 0; i < data.length; i++) {
			options[i + 1] = new SelectItem(data[i], data[i]);
		}

		return options;
	}

	private String[] getCountryNames(List<Country> data) {
		String[] hotelNames = new String[data.size()];
		for (int i = 0; i < data.size(); i++) {
			hotelNames[i] = data.get(i).getName();
		}
		return hotelNames;
	}

	private String[] getHotelNames(List<Hotel> data) {
		String[] hotelNames = new String[data.size()];
		for (int i = 0; i < data.size(); i++) {
			hotelNames[i] = data.get(i).getName();
		}
		return hotelNames;
	}

	private String[] getCityNames(List<City> data) {
		String[] cityNames = new String[data.size()];
		for (int i = 0; i < data.size(); i++) {
			cityNames[i] = data.get(i).getName();
		}
		return cityNames;
	}

	public SelectItem[] getStarOptions() {
		return starOptions;
	}

	public SelectItem[] getCountryOptions() {
		return countryOptions;
	}

	public SelectItem[] getHotelOptions() {
		/*
		 * if (countryOptions != null && countryOptions.length != 0) { String
		 * countryName = countryOptions[0].getLabel(); Country country =
		 * countryService.findByName(countryName); hotelOptions =
		 * createFilterOptions
		 * (getHotelNames(hotelService.findByCountry(country))); }
		 */
		return hotelOptions;
	}

	public SelectItem[] getCityOptions() {
		return cityOptions;
	}

	public void setCityOptions(SelectItem[] cityOptions) {
		this.cityOptions = cityOptions;
	}

	public SelectItem[] getFoodOptions() {
		return foodOptions;
	}

	public void setFoodOptions(SelectItem[] foodOptions) {
		this.foodOptions = foodOptions;
	}

	public SelectItem[] getRoomOptions() {
		return roomOptions;
	}

	public void setRoomOptions(SelectItem[] roomOptions) {
		this.roomOptions = roomOptions;
	}

	public String getSelectedTourId() {
		return selectedTourId;
	}

	public void setSelectedTourId(String selectedTourId) {
		this.selectedTourId = selectedTourId;
	}

	public Tour getSelectedTour() {
		return selectedTour;
	}

	public void setSelectedTour(Tour selectedTour) {
		this.selectedTour = selectedTour;
	}

	public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}

	public List<Hotel> getHotels() {
		return hotels;
	}

	public void setHotels(List<Hotel> hotels) {
		this.hotels = hotels;
	}

	public void setHotelOptions(SelectItem[] hotelOptions) {
		this.hotelOptions = hotelOptions;
	}

	public List<Hotel> getFilteredHotels() {
		return filteredHotels;
	}

	public void setFilteredHotels(List<Hotel> filteredHotels) {
		this.filteredHotels = filteredHotels;
	}

	private String destinationUrl;

	public TourController() {
	}

	public String getDestinationUrl() {
		return destinationUrl;
	}

	public void setDestinationUrl(String destinationUrl) {
		this.destinationUrl = destinationUrl;
	}

	public void resetDestinationUrl() {
		destinationUrl = null;
	}

	public void setTourById() {
		if (selectedTourId != null && !selectedTourId.equals("")) {
			int id = Integer.parseInt(selectedTourId);
			selectedTour = tourService.findById(id);
			selectedTourId = null;
		}
	}

	public void order() {

		if (selectedTour != null) {
			tourService.save(selectedTour);
			init();

		}
	}
}
