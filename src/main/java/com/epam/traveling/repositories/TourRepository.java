package com.epam.traveling.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.traveling.entities.Tour;

public interface TourRepository extends JpaRepository<Tour, Integer> {

	public List<Tour> getAvaliable();
}
