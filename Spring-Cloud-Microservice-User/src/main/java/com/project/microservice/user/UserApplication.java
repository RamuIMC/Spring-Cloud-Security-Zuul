package com.project.microservice.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.microservice.user.dto.UserDto;

@SpringBootApplication
@RestController
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);

	}
	
	@RequestMapping(value="/getName/{2}",produces=MediaType.APPLICATION_JSON_VALUE)
	public UserDto getName(){
		UserDto userDto= new UserDto();
		
		userDto.setUserId(2);
		userDto.setUserName("Ramakrishna");
		
		return userDto;
		
	}

}
