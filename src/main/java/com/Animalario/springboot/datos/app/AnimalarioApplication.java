package com.Animalario.springboot.datos.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class AnimalarioApplication implements CommandLineRunner{

	@Autowired
	private BCryptPasswordEncoder PasswordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(AnimalarioApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}



}
