package com.guidedchoice.vehicle.controller;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guidedchoice.vehicle.entity.Vehicle;
import com.guidedchoice.vehicle.exception.RecordNotFoundException;
import com.guidedchoice.vehicle.service.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

	private static final Logger logger = LoggerFactory.getLogger(VehicleController.class);

	@Autowired
	VehicleService vehicleService;

	public VehicleController() {
		logger.info("VehicleController loaded.");
	}
	
    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
    	
        List<Vehicle> list = vehicleService.getAllVehicles();
 
        return new ResponseEntity<List<Vehicle>>(list, new HttpHeaders(), HttpStatus.OK);
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable("id") Integer id)
                                                    throws RecordNotFoundException {
    	Vehicle entity = vehicleService.getVehicleById(id);
 
        return new ResponseEntity<Vehicle>(entity, new HttpHeaders(), HttpStatus.OK);
    }
    
    @GetMapping("/search/{make}")
    public ResponseEntity<Collection<Vehicle>> getVehiclesByMake(@PathVariable("make") String make) {
                                                    
    	Collection<Vehicle> vehicles = vehicleService.getVehiclesByMake(make);
 
        return new ResponseEntity<Collection<Vehicle>>(vehicles, new HttpHeaders(), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<Vehicle> createVehicle(@Valid @RequestBody Vehicle vehicle) {
                                                    //throws RecordNotFoundException {
    	Vehicle created = vehicleService.createVehicle(vehicle);
        return new ResponseEntity<Vehicle>(created, new HttpHeaders(), HttpStatus.OK);
    }
 
    @PutMapping
    public ResponseEntity<Vehicle> updateVehicle(@Valid @RequestBody Vehicle vehicle)
                                                    throws RecordNotFoundException {
    	Vehicle updated = vehicleService.updateVehicle(vehicle);
        return new ResponseEntity<Vehicle>(updated, new HttpHeaders(), HttpStatus.OK);
    }
 
    @DeleteMapping("/{id}")
    public ResponseEntity<Vehicle> deleteVehicleById(@PathVariable("id") Integer id)
                                                    throws RecordNotFoundException {
    	Vehicle deleled = vehicleService.deleteVehicleById(id);
    	return new ResponseEntity<Vehicle>(deleled, new HttpHeaders(), HttpStatus.OK);
    }
}
