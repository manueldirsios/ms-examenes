package com.gd.examen.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
 	@Schema(description = "Usuario.", example = "user1")
	@NotBlank(message = "El campo  ´´username´´ es obligatorio")
	private String username;

 	@Schema(description = "Password.", example = "pass1")
	@NotBlank(message = "El campo ´´password´´  nombre es obligatorio")
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
