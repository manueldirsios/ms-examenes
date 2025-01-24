package com.examen.examen.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ComponentScan("com.gd.examen")
class JWTTest {
	@Autowired
	private MockMvc mockMvc;
	
	@Test 
	void addConsuumirServTokenExpirado()  throws Exception{
		String token="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNzMzMDI0OTI3LCJleHAiOjE3MzMwMjQ5ODd9.rNwwtFjfVbRTv-iHTkImL5DbimrQeWFfY4jbJp54ZcQ";
		
		String response = mockMvc.perform(get("/examenes/obtener/").header("Authorization", "Bearer " + token))
				.andExpect(status().is(401)).andExpect(MockMvcResultMatchers.jsonPath("$.detalles").exists()).andReturn()
				.getResponse().getContentAsString();

		System.out.println("response : " + response);
	}
	
	@Test
	void addConsuumirServTokenMalformado()  throws Exception{
		String token="eyJhbGciOiJIUzI1Ni.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNzMzMDI0OTI3LCJleHAiOjE3MzMwMjQ5ODd9.rNwwtFjfVbRTv-iHTkImL5DbimrQeWFfY4jbJp54ZcQ";
		
		String response = mockMvc.perform(get("/examenes/obtener/").header("Authorization", "Bearer " + token))
				.andExpect(status().is(401)).andExpect(MockMvcResultMatchers.jsonPath("$.detalles").exists()).andReturn()
				.getResponse().getContentAsString();

		System.out.println("response : " + response);
	}
	
	@Test
	void addConsuumirServTokenFirmadoInvalido()  throws Exception{
		String token="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNzMzMDI0OTI3LCJleHAiOjE3MzMwMjQ5ODd9.rNwwtFjfVbRTv-iHTkImL5DbimrQeWF4jbJp54ZcQ";
		
		String response = mockMvc.perform(get("/examenes/obtener/").header("Authorization", "Bearer " + token))
				.andExpect(status().is(401)).andExpect(MockMvcResultMatchers.jsonPath("$.detalles").exists()).andReturn()
				.getResponse().getContentAsString();

		System.out.println("response : " + response);
	}
}
