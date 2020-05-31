package com.guidedchoice.vehicle;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.guidedchoice.vehicle.entity.Vehicle;
import com.guidedchoice.vehicle.repository.VehicleRepository;


@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages="com.guidedchoice.vehicle")
public class VehicleApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehicleApplication.class, args);
	}
}

/**
 * After the persistence context is loaded this class gets loaded and executes the run the method.
 * We use this class to populate the in-memory H2 database.
 * 
 *  @see application.propeties
 *  
 * @author Dovan Nguyen
 *
 */
@Component
class VehicleInitializer implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(VehicleInitializer.class);

	private final VehicleRepository vehicleRepositiory;
	
	VehicleInitializer(VehicleRepository vehicleRepositiory) {
		this.vehicleRepositiory = vehicleRepositiory;
	}
	
	@Override
	public void run(String... args) throws Exception {
		Vehicle[] vehicles = {
				new Vehicle(2019, "Tesla", "Model 3"),
				new Vehicle(2015, "Nissan", "Leaf"),
				new Vehicle(2018, "Huydai", "Kona"),
				new Vehicle(2020, "Tesla", "Model Y"),
		};
		
		Arrays.stream(vehicles)
		.forEach(vehicle -> { 
			vehicleRepositiory.save(vehicle);
			logger.info("Saved vehicle " + vehicle.toString());
		});
	}
	
}