package com.packt.CarBE;

import com.packt.CarBE.domain.Entity.Car;
import com.packt.CarBE.domain.Entity.Owner;
import com.packt.CarBE.domain.Repository.CarRepository;
import com.packt.CarBE.domain.Repository.OwnerRepository;
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

	public static void main(String[] args) {
		SpringApplication.run(CarBeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Owner owner1 = new Owner("John", "Johnson");
		Owner owner2 = new Owner("Ahn", "Johnson");
		ownerRepository.saveAll(Arrays.asList(owner1, owner2));

		carRepository.save(new Car("Ford", "Mustang", "Red", "ADF-1121", 2021, 59000, owner1));
		carRepository.save(new Car("Ford", "Mustang", "Red", "ADF-1121", 2021, 59000, owner2));
		carRepository.save(new Car("Ford", "Mustang", "Red", "ADF-1121", 2021, 59000, owner2));

		for(Car car : carRepository.findAll()){
			logger.info(car.getBrand() + " " + car.getModel());
		}
	}
}
