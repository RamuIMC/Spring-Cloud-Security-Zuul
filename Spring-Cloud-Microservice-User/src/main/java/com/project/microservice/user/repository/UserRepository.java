/**
 * 
 */
package com.project.microservice.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.microservice.user.model.User;

/**
 * @author Ramakrishna
 *
 */
public interface UserRepository extends JpaRepository<User, Integer>{

	public User getUserByUserName(String userName);
}
