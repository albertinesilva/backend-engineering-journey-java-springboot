package com.albertsilva.dev.dscatalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class DscatalogApplication implements CommandLineRunner {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(DscatalogApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String encodedPassword = passwordEncoder.encode("123456");
		System.out.println("ENCODED PASSWORD: " + encodedPassword);
		boolean isPasswordMatch = passwordEncoder.matches("123456", "$2a$10$eDIzRoyjJ4Rw7RbsBBfqVuzxU8lABGMlgKAMqqLtnpu9iN6b1w7ve");
		System.out.println("PASSWORD MATCH: " + isPasswordMatch);
	}

}
