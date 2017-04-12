package com.project.microservice.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.microservice.user.dto.UserDto;
import com.project.microservice.user.model.User;
import com.project.microservice.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository UserRepository;

	@Override
	public List<UserDto> getNames() {
		List<UserDto > list = new ArrayList<UserDto>();
		UserDto userDto;
		List<User> users =  UserRepository.findAll();
		
		for(User user:users){
			userDto = new UserDto();
			userDto.setUserId(user.getId());
			userDto.setUserName(user.getUserName());
			list.add(userDto);
		}
		return list;
	}

}
