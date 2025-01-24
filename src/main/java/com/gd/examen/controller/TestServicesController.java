package com.gd.examen.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gd.examen.service.ExamenTestApiImpl;

@RestController
@RequestMapping("/services")
public class TestServicesController {

	private ExamenTestApiImpl test;
	TestServicesController(ExamenTestApiImpl test){
		this.test=test;
	}
	@GetMapping("/test")
	public ResponseEntity<String> testServices() throws  JsonProcessingException{
		test.guarda("{ \"nombre\": \"pruebanombre\", \"tipo\": \"preubastipo\" }");
		test.actualizar("{ \"nombre\": \"pruebaactualizanombre\", \"tipo\": \"preubaactualizatipo\" }");

		return test.obtenerExamen();
	}

}
