package com.guidedchoice.vehicle.service;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.guidedchoice.vehicle.entity.Vehicle;
import com.guidedchoice.vehicle.repository.VehicleRepository;

@RunWith(SpringRunner.class)
public class VehicleServiceIntegrationTest {
	
    @TestConfiguration
    static class VehicleServiceTestContextConfiguration {
  
        @Bean(name="VehicleService")
        public VehicleService vehicleService() {
            return new VehicleService();
        }
    }
 
    //@Autowired
    @Resource(name="VehicleService")
    private VehicleService vehicleService;
 
    @MockBean
    private VehicleRepository vehicleRepository;

    @Before
    public void setUp() {
        Vehicle vehicle = new Vehicle(1, 2020, "Chevy", "Bold");
     
        Mockito.when(vehicleRepository.findById(vehicle.getId()))
          .thenReturn(Optional.of(vehicle));
    }
    
	@Test
	public void whenValidId_thenVehicleShouldBeFound() {
	    int id = 1;
	    Vehicle found = vehicleService.getVehicleById(id);
	  
	    assertTrue(found.getId() == id);
	    assertTrue(found.getYear() == 2020);
	    assertTrue(found.getMake() == "Chevy");
	    assertTrue(found.getModel() == "Bold");
	}
}
