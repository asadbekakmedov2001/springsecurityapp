package uz.smartup.academy.springsecurityapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
public class SpringsecurityappApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringsecurityappApplication.class, args);
	}

}
