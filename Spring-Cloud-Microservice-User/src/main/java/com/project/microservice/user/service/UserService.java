/**
 * 
 */
package com.project.microservice.user.service;

import java.util.List;

import com.project.microservice.user.dto.UserDto;

/**
 * @author Ramakrishna
 *
 */
public interface UserService {
	
	public List<UserDto> getNames();

}
