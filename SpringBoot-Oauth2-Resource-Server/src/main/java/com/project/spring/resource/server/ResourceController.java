package com.project.spring.resource.server;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.spring.resource.server.dto.UserDto;

@RestController
public class ResourceController {
	
	
	@RequestMapping(value="/api/user/{name}",method=RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('DEVELOPER')")
	public UserDto getUser(@PathVariable String name){
		
		UserDto userDto = new UserDto();
		
		userDto.setUserId(1);
		userDto.setName(name);
		return userDto;
		
		
	}
	@RequestMapping(value="/test/user/{name}",method=RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('DEVELOPER2')")
	public UserDto getUser2(@PathVariable String name){
		
		UserDto userDto = new UserDto();
		
		userDto.setUserId(1);
		userDto.setName(name);
		return userDto;
		
		
	}
}
