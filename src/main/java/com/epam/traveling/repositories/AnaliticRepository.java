package com.epam.traveling.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.traveling.entities.Analitic;
import com.epam.traveling.entities.User;

public interface AnaliticRepository extends JpaRepository<Analitic, Integer> {

	public Analitic findByEmailAndPassword(String email, String password);
	
	public Analitic findByEmail(String email);
}
