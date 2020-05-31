package com.guidedchoice.vehicle.repository;

import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.guidedchoice.vehicle.entity.Vehicle;

@RunWith(SpringRunner.class)
@DataJpaTest
public class VehicleRepositoryIntegrationTest {

	@Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private VehicleRepository vehicleRepository;

    @Test
    public void whenFindById_thenReturnVehicle() {

        Vehicle teslaModelS = new Vehicle(2020, "Tesla", "Model S");
        Vehicle saved = entityManager.persist(teslaModelS);
        entityManager.flush();
     
        // Note we override the equals method in Vehicle to compare the internal
        // field values.
        assertTrue(teslaModelS.equals(saved));

        Optional<Vehicle> found = vehicleRepository.findById(saved.getId());
     
        assertTrue(found.isPresent());
        assertTrue(teslaModelS.equals(found.get()));
    }

    @Test
    public void whenFindByMake_thenReturnAllVehiclesWithSameMake() {

    	String searchedMake = "Tesla";
        Collection<Vehicle> allTeslas = vehicleRepository.findByMake(searchedMake);
     
        assertTrue(allTeslas.size() > 0);
        allTeslas.stream().forEach(v -> {
        	assertTrue(v.getMake().equals(searchedMake));	
        });
    }
}
