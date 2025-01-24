package com.gd.examen.response;

import io.swagger.v3.oas.annotations.media.Schema;

public class ExamenResponse {
 	@Schema(description = "Id de la respuesta.", example = "1")
	private int id;
 	
 	@Schema(description = "Nombre de la respuesta.", example = "Fulanito")
	private String nombre;
 	
 	@Schema(description = "Tipo de la respuesta.", example = "Vacante")
	private String tipo;
 	
 	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	
	
}
