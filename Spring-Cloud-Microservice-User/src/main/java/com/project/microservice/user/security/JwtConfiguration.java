package com.project.microservice.user.security;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import org.springframework.stereotype.Component;

import com.project.microservice.user.dto.UserDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtConfiguration {
	
	public String createToken(UserDto userDto){
		Date expiration = Date.from(LocalDateTime.now().plusMinutes(200).atZone(ZoneId.systemDefault()).toInstant());
		return Jwts.builder()
		.setSubject(userDto.getUserName())
		.setExpiration(expiration)
		.signWith(SignatureAlgorithm.HS512, "secretkey")
		.compact();
		
	}
	
	public UserDto verify(String token) throws IOException, URISyntaxException {
		
		Jws<Claims> claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token);
		//if(claims.getBody().getExpiration().after(new Date()))
		//throw new InvalidTokenException("Invalid Token");
		UserDto userDto = new UserDto();
		userDto.setUserName(claims.getBody().getSubject().toString());
		return userDto;
		
	}

}
