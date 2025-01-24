package com.gd.examen.response;

import io.swagger.v3.oas.annotations.media.Schema;

public class ExamenGenericResponse {

	@Schema(description = "Objecto de la respuesta.", example = "{}")
	private Object transaccion;
	@Schema(description = "Mensaje de la respuesta.", example = "Operacion Exitosa")
	private String mensaje;
	@Schema(description = "Codigo de la respuesta.", example = "200")
	private int codigoRespuesta;
	
	public Object getTransaccion() {
		return transaccion;
	}
	public void setTransaccion(Object transaccion) {
		this.transaccion = transaccion;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public int getCodigoRespuesta() {
		return codigoRespuesta;
	}
	public void setCodigoRespuesta(int codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}
	
	
}
