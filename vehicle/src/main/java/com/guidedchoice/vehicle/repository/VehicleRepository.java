package com.guidedchoice.vehicle.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.guidedchoice.vehicle.entity.Vehicle;

/**
 * JpaRepository provides us the standard "basic" CRUD operations out of the box.
 * Spring will provide us the implementation.
 * 
 * We add a custom filter method to query all vehicles of same Make.
 * 
 * @author Dovan Nguyen
 *
 */
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
	
	/**
	 * This is a custom finder to filter all vehicles by Make.
	 * 
	 * @param make: the Make of the vehicle to filter on, e.g Tesla
	 * @return a collection of Vehicle of the Make criteria.
	 */
	@Query(value="SELECT * FROM Vehicle WHERE Make = ?", nativeQuery=true)
	Collection<Vehicle> findByMake(String make);
}
