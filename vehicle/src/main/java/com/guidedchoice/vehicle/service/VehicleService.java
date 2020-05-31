package com.guidedchoice.vehicle.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guidedchoice.vehicle.entity.Vehicle;
import com.guidedchoice.vehicle.exception.RecordNotFoundException;
import com.guidedchoice.vehicle.repository.VehicleRepository;

@Service
public class VehicleService {
	
    @Autowired
    VehicleRepository repository;
    
    public VehicleService() {}
    
    public List<Vehicle> getAllVehicles()
    {
        List<Vehicle> vehicleList = repository.findAll();

        if(vehicleList.size() > 0) {
            return vehicleList;
        } else {
            return new ArrayList<Vehicle>();
        }
    }
     
    public Vehicle getVehicleById(Integer id) throws RecordNotFoundException
    {
        Optional<Vehicle> vehicle = repository.findById(id);
         
        if(vehicle.isPresent()) {
            return vehicle.get();
        } else {
            throw new RecordNotFoundException("No vehicle record exist for given id", id);
        }
    }

	public Collection<Vehicle> getVehiclesByMake(String make)
	{
	    return repository.findByMake(make);
	}

	public Vehicle createVehicle(Vehicle vehicle)
	{
		return repository.save(vehicle);
	}
	 
	public Vehicle updateVehicle(Vehicle vehicle) throws RecordNotFoundException
	{
		Optional<Vehicle> found = repository.findById(vehicle.getId());
		if(! found.isPresent()) {
			throw new RecordNotFoundException("No vehicle record exist for given id", vehicle.getId());
		}
		
		return repository.save(vehicle);
	}

	public Vehicle deleteVehicleById(Integer id) throws RecordNotFoundException
	{
		Optional<Vehicle> vehicle = repository.findById(id);
		  
		if(!vehicle.isPresent()) {
			throw new RecordNotFoundException("No vehicle record exist for given id ", id);
		}
		
		repository.deleteById(id);
		return vehicle.get();
	}
}
