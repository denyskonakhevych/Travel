package com.epam.traveling;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.traveling.entities.City;
import com.epam.traveling.entities.Country;
import com.epam.traveling.entities.Hotel;
import com.epam.traveling.entities.User;
import com.epam.traveling.repositories.CityRepository;
import com.epam.traveling.repositories.CountryRepository;
import com.epam.traveling.repositories.HotelRepository;
import com.epam.traveling.repositories.UserRepository;
import com.epam.traveling.services.CityService;
import com.epam.traveling.services.CountryService;
import com.epam.traveling.services.UserService;
import com.epam.traveling.web.SaltedHasher;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations="classpath:/persistenceContextTest.xml")
public class UserTest extends InitialTest {
	
	@Autowired
	@Qualifier("userService")
	UserService userRepository;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testUser() {
		/*Country country = getCountry("Ukraine", "Best country", "Mild");
		City city = getCity(country, "Kyiv", "description");
		Hotel hotel = getHotel(city, "Hotel", 5, "Description");*/
		
		User user = new User();
		String email = "test@t.tt";
		String passwordToHash = "pass";
		user.setAddress(email);
		
		SaltedHasher saltedHasher = new SaltedHasher();
		String salt = saltedHasher.getSalt();
		String securePass = saltedHasher.getSecurePassword(passwordToHash, salt);
		user.setEmail(email);
		user.setPassword(securePass);
		userRepository.save(user);
		
		User saved = userRepository.findByEmailAndPassword(email, securePass);
		assertEquals(user, saved);
	}
	
	/*private Hotel getHotel(City city, String name, int stars, String description) {
		Hotel hotel = new Hotel(null, city, name, stars, description);
		return hotel;
	}
	
	private City getCity(Country country, String name, String description) {
		City city = new City(null, country, name, description);
		return city;
	}

	private Country getCountry(String name, String description, String climate) {
		Country country = new Country(null, name, description, climate);
		return country;
	}*/
}
