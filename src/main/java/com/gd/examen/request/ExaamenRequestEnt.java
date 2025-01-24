package com.gd.examen.request;

import jakarta.validation.constraints.NotBlank;

public class ExaamenRequestEnt {
	@NotBlank(message = "El campo  ´´nombre´´ es obligatorio")
	private String nombre;
	@NotBlank(message = "El campo ´´tipo´´  nombre es obligatorio")
	private String tipo;
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	@Override
	public String toString() {
		return "ExaamenRequestEnt [nombre=" + nombre + ", tipo=" + tipo + "]";
	}


	
}
