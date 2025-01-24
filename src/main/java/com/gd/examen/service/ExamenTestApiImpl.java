package com.gd.examen.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gd.examen.response.AuthResponse;

@Service
public class ExamenTestApiImpl {


    private RestTemplate restTemplate=new RestTemplate() ;
    private ObjectMapper om=new ObjectMapper();
    private static final String API_URL = "http://localhost:8081";
	private static final String HEADER_AUTH_BEARER="Bearer ";
    public  ResponseEntity<String> obtenerToken() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
    	HttpEntity<String> entity = new HttpEntity<>("{ \"username\": \"user\", \"password\": \"password\" }", headers);
    	return restTemplate.exchange(API_URL+"/auth/token", HttpMethod.POST, entity, String.class);
    }


	public ResponseEntity<String>  obtenerExamen() throws  JsonProcessingException {
    	HttpHeaders headers = new HttpHeaders();
    	headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    	AuthResponse respToken =om.readValue(obtenerToken().getBody(), AuthResponse.class);
    	
    	headers.add(HttpHeaders.AUTHORIZATION, HEADER_AUTH_BEARER+respToken.getToken());
    	HttpEntity<String> entity = new HttpEntity<>("", headers);
    	return restTemplate.exchange(API_URL+"/examenes/obtener", HttpMethod.GET, entity, String.class);
    }


	public void actualizar(String  request) throws JsonProcessingException {
    	HttpHeaders headers = new HttpHeaders();
    	headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    	AuthResponse respToken =om.readValue(obtenerToken().getBody(), AuthResponse.class);
    	
    	headers.add(HttpHeaders.AUTHORIZATION, HEADER_AUTH_BEARER+respToken.getToken());
    	HttpEntity<String> entity = new HttpEntity<>(request, headers);
    	restTemplate.exchange(API_URL+"/examenes/actualiza/1", HttpMethod.PUT, entity, String.class);
    
    }

	public void guarda(String  request) throws JsonProcessingException {
    	HttpHeaders headers = new HttpHeaders();
    	headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    	AuthResponse respToken =om.readValue(obtenerToken().getBody(), AuthResponse.class);
    	
    	headers.add(HttpHeaders.AUTHORIZATION, HEADER_AUTH_BEARER+respToken.getToken());
    	HttpEntity<String> entity = new HttpEntity<>(request, headers);
    	restTemplate.exchange(API_URL+"/examenes/guarda", HttpMethod.POST, entity, String.class);
    }
}
