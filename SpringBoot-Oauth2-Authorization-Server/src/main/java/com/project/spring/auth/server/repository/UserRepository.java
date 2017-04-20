/**
 * 
 */
package com.project.spring.auth.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.spring.auth.server.model.User;

/**
 * @author Ramakrishna
 *
 */
public interface UserRepository extends JpaRepository<User, Integer>{

	public User getUserByUserName(String userName);
}
