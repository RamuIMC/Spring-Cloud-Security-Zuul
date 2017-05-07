/**
 * 
 */
package com.project.spring.auth.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class AuthorizationApplication extends SpringBootServletInitializer{

	/**
	 * @param args
	 */
	/*public static void main(String[] args) {
		SpringApplication.run(AuthorizationApplication.class, args);

	}*/
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AuthorizationApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(AuthorizationApplication.class, args);
    }

}
