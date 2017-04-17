/**
 * 
 */
package com.project.microservice.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.microservice.user.dto.UserDto;
import com.project.microservice.user.service.UserService;

/**
 * @author Ramakrishna
 *
 */
@RestController
public class UserController {
	
	@Autowired
	private UserService UserService;
	
	@RequestMapping(value="/getUser/getNames")
	public List<UserDto> getNames(){
		return UserService.getNames();		
	}
	@RequestMapping(value="/testUser/getNames")
	public List<UserDto> getTestNames(){
		return UserService.getNames();		
	}

}
