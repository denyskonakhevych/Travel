package com.epam.traveling;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.traveling.entities.Country;
import com.epam.traveling.services.CountryService;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations="classpath:/persistenceContextTest.xml")
public class CountryTest extends InitialTest {
	
	@Autowired
	@Qualifier("countryService")
	CountryService countryService;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCountry() {
		Country country = getCountry("Ukraine", "Best country", "Mild");
		countryService.save(country);
		Country actual = countryService.findById(country.getId());
		assertEquals(country, actual);
	}

	private Country getCountry(String name, String description, String climate) {
		Country country = new Country(null, name, description, climate);
		return country;
	}
}
