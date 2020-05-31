package com.guidedchoice.vehicle.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Resource;
import javax.ws.rs.core.MediaType;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.guidedchoice.vehicle.entity.Vehicle;
import com.guidedchoice.vehicle.repository.VehicleRepository;
import com.guidedchoice.vehicle.service.VehicleService;

import static org.mockito.BDDMockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@WebMvcTest(VehicleController.class)
public class VehicleControllerIntegrationTest {
	 
    @Autowired
    private MockMvc mvc;
 
    @MockBean
    private VehicleRepository vehicleRepository;
 
    @Resource(name="VehicleService")
    private VehicleService vehicleService;
 
    @Test
    public void givenVehicles_whenGetVehicles_thenReturnJsonArray()
      throws Exception {
         
		Vehicle[] vehicles = {
				new Vehicle(2019, "Tesla", "Model 3"),
				new Vehicle(2015, "Nissan", "Leaf"),
				new Vehicle(2018, "Huydai", "Kona"),
				new Vehicle(2020, "Tesla", "Model Y"),
		};

        List<Vehicle> allVehicles = Stream.of(vehicles).collect(Collectors.toList());
     
        given(vehicleService.getAllVehicles()).willReturn(allVehicles);
     
		mvc.perform(get("/vehicles")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(4)))
			.andExpect(jsonPath("$[0].model", is(allVehicles.get(0).getModel())))
			.andExpect(jsonPath("$[1].model", is(allVehicles.get(1).getModel())))
			.andExpect(jsonPath("$[2].model", is(allVehicles.get(2).getModel())))
        	.andExpect(jsonPath("$[3].model", is(allVehicles.get(3).getModel())));
    }
}
