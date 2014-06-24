/**
 * 
 */
package com.epam.traveling.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.traveling.entities.User;

/**
 * @author Siva
 *
 */
public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByEmailAndPassword(String email, String password);

	public User findByEmail(String email);
}
