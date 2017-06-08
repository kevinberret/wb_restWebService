package ch.hevs.wiggerberret;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/*
 * MAIN Webservice Application
 */
@SpringBootApplication
public class Group17Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Group17Application.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Group17Application.class, args);
	}
}
