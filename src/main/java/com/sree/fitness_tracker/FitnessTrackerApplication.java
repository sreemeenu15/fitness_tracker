package com.sree.fitness_tracker;

import com.sree.fitness_tracker.entity.User;
import com.sree.fitness_tracker.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;

@SpringBootApplication
public class FitnessTrackerApplication {

	public static void main(String[] args) {

		SpringApplication.run(FitnessTrackerApplication.class, args);
//		User u = User.builder().firstName("Test").build();
	}



}
