/**
 * 
 */
package com.project.spring.resource.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;


/**
 * @author Ramakrishna
 *
 */
@SpringBootApplication
public class ResourceApplication extends SpringBootServletInitializer{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(ResourceApplication.class, args);

	}
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ResourceApplication.class);
    }

}
