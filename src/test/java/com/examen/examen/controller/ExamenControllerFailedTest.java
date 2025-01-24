package com.examen.examen.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gd.examen.request.ExaamenRequestEnt;
import com.gd.examen.request.LoginRequest;
import com.gd.examen.response.AuthResponse;

@SpringBootTest
@AutoConfigureMockMvc
@ComponentScan("com.gd.examen")
 class ExamenControllerFailedTest {
	ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private MockMvc mockMvc;
	private static String token = "";
	
	
	@BeforeEach
	void init() throws Exception {
		addObtenerToken();
	}

	void addObtenerToken() throws Exception {

		LoginRequest peticion = new LoginRequest();
		peticion.setPassword("password");
		peticion.setUsername("user");

		String response = mockMvc
				.perform(post("/auth/token").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsBytes(peticion)))
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.token").exists()).andReturn()
				.getResponse().getContentAsString();
		System.out.println("response addObtenerToken " + response);
		AuthResponse respToken = mapper.readValue(response, AuthResponse.class);
		token = respToken.getToken();
	}
	
	@Test
	@Order(1) 
	void addGuardaTestFaileArgs() throws  Exception {
		ExaamenRequestEnt peticion = new ExaamenRequestEnt();
		peticion.setNombre(RandomStringUtils.random(2, "Nombre"));
		
		String response = mockMvc
				.perform(post("/examenes/guarda").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsBytes(peticion)).header("Authorization", "Bearer " + token))
				.andExpect(status().is(400)).andExpect(MockMvcResultMatchers.jsonPath("$.detalles").exists()).andReturn()
				.getResponse().getContentAsString();
		
		System.out.println("response addGuardaTest " + response);

	}
	
	
	@Test
	@Order(2) 
	void addGuardaTestFailedGeneric() throws  Exception {
		String response = mockMvc
				.perform(post("/examenes/guarda").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsBytes(null)).header("Authorization", "Bearer " + token))
				.andExpect(status().is(400)).andExpect(MockMvcResultMatchers.jsonPath("$.detalles").exists()).andReturn()
				.getResponse().getContentAsString();
		
		System.out.println("response addGuardaTest " + response);

	}
}
