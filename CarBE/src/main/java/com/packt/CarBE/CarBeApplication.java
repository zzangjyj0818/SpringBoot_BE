package com.packt.CarBE;

import com.packt.CarBE.domain.Entity.Car;
import com.packt.CarBE.domain.Entity.Owner;
import com.packt.CarBE.domain.Entity.User;
import com.packt.CarBE.domain.Repository.CarRepository;
import com.packt.CarBE.domain.Repository.OwnerRepository;
import com.packt.CarBE.domain.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.PrintWriter;
import java.util.Arrays;

@SpringBootApplication
public class CarBeApplication implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(CarBeApplication.class);

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private OwnerRepository ownerRepository;

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(CarBeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Owner owner1 = new Owner("John", "Johnson");
		Owner owner2 = new Owner("Ahn", "Johnson");
		ownerRepository.saveAll(Arrays.asList(owner1, owner2));

		Car car1 = new Car("Ford", "Mustang", "Red", "ADF-1121", 2021, 59000, owner1);
		Car car2 = new Car("Ford", "Mustang", "Red", "ADF-1121", 2021, 59000, owner2);
		Car car3 = new Car("Ford", "Mustang", "Red", "ADF-1121", 2021, 59000, owner2);

		carRepository.saveAll(Arrays.asList(car1, car2, car3));

		userRepository.save(new User("user", "$2a$10$NVM0n8ElaRgg7zWO1CxUdei7vWoPg91Lz2aYavh9.f9q0e4bRadue", "USER"));
		userRepository.save(new User("admin", "$2a$10$8cjz47bjbR4Mn8GMg9IZx.vyjhLXR/SKKMSZ9.mP9vpMu0ssKi8GW", "ADMIN"));
	}
}
