package com.epam.traveling.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.traveling.entities.Booking;
import com.epam.traveling.entities.User;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

	public void complete(Booking booking);
	
	public List<Booking> findByUser(User user);
}
