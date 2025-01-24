package com.examen.examen.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gd.examen.request.ExaamenRequestEnt;
import com.gd.examen.request.LoginRequest;
import com.gd.examen.response.AuthResponse;
import com.gd.examen.response.ExamenGenericResponse;
import com.gd.examen.response.ExamenResponse;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // Orden definido por @Order
@ComponentScan("com.gd.examen")
 class ExamenControllerTest {

	ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private MockMvc mockMvc;
	private static String token = "";
	private static int idTransac = 0;
	
	
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
	void addGuardaTest() throws  Exception {
		ExaamenRequestEnt peticion = new ExaamenRequestEnt();
		peticion.setNombre(RandomStringUtils.random(2, "Nombre"));
		peticion.setTipo(RandomStringUtils.random(2, "Tipo"));
		
		String response = mockMvc
				.perform(post("/examenes/guarda").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsBytes(peticion)).header("Authorization", "Bearer " + token))
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.codigoRespuesta").exists()).andReturn()
				.getResponse().getContentAsString();
		
		System.out.println("response addGuardaTest " + response);

	}


	@Test
	@Order(2) 
	void addObtenerTest() throws Exception {

		String response = mockMvc.perform(get("/examenes/obtener").header("Authorization", "Bearer " + token))
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.mensaje").exists()).andReturn()
				.getResponse().getContentAsString();
		
		System.out.println("response : " + response);
		ExamenGenericResponse respResult = mapper.readValue(response, ExamenGenericResponse.class);
		List<ExamenResponse> lisExamen = mapper.convertValue(respResult.getTransaccion(), new TypeReference<List<ExamenResponse>>() {});

		idTransac=lisExamen.get(0).getId();
		System.out.println("Id a transaccionar : " + idTransac);
		

	}
	
	@Test
	@Order(3) 
	void addObtenerXidTest()  throws Exception{

		String response = mockMvc.perform(get("/examenes/obtener/"+idTransac).header("Authorization", "Bearer " + token))
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.codigoRespuesta").exists()).andReturn()
				.getResponse().getContentAsString();

		System.out.println("response : " + response);
	}


	@Test
	@Order(3) 
	void addActualizaTest() throws Exception {
		ExaamenRequestEnt peticion = new ExaamenRequestEnt();
		peticion.setNombre(RandomStringUtils.random(2, "Nombre"));
		peticion.setTipo(RandomStringUtils.random(2, "Tipo"));
		
		String response = mockMvc
				.perform(put("/examenes/actualiza/"+idTransac).contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsBytes(peticion)).header("Authorization", "Bearer " + token))
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.codigoRespuesta").exists()).andReturn()
				.getResponse().getContentAsString();
		
		System.out.println("response addActualizaTest" + response);
	}

}
