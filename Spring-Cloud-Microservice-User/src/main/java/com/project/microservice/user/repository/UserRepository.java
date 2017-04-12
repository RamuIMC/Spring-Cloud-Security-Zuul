/**
 * 
 */
package com.project.microservice.user.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.microservice.user.model.User;

/**
 * @author Ramakrishna
 *
 */
public interface UserRepository extends JpaRepository<User, Serializable>{

}
