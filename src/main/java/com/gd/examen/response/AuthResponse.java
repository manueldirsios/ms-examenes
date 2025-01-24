package com.gd.examen.response;

import io.swagger.v3.oas.annotations.media.Schema;

public class AuthResponse {
	
 	@Schema(description = "Token de la respuesta.", example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMyIsImlhdCI6MTczMjg5Njc2NCwiZXhwIjoxNzMyODk2ODI0fQ.guc3Nr805x4pTy2R7N2Tjxu19VNKVYCjdC4GQhDNhZ0")
    private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	public AuthResponse(String token){
		this.token=token;
	}
	
	 // Constructor vacío necesario para Jackson
    public AuthResponse() {}
  
}
