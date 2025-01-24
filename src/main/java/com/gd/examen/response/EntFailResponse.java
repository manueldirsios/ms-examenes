package com.gd.examen.response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

public class EntFailResponse {

	 	@Schema(description = "Codigo de la respuesta.", example = "CodigoEstadoHTTP")
	    private String codigo;
	    @Schema(description = "Descripci�n de la respuesta..", example = "Mensaje")
	    private String mensaje;
	    @Schema(description = "Numero de folio de la operacion.", example = "001-29782-590772-1")
	    private String folio;
	    @Schema(description = "Rutas de ayuda para atender la respuesta",example = "htts:1.1.1/help")
	    private String info;
	    @Schema(description = "Detalles de la operacion", example = "[]")
	    private List<Object> detalles = new ArrayList<>();
	    
	    public EntFailResponse() {
	        this.folio = LocalDateTime.now().getNano()+"";

	    }

		public String getCodigo() {
			return codigo;
		}

		public void setCodigo(String codigo) {
			this.codigo = codigo;
		}

		public String getMensaje() {
			return mensaje;
		}

		public void setMensaje(String mensaje) {
			this.mensaje = mensaje;
		}

		public String getFolio() {
			return folio;
		}

		public void setFolio(String folio) {
			this.folio = folio;
		}

		public String getInfo() {
			return info;
		}

		public void setInfo(String info) {
			this.info = info;
		}

		public List<Object> getDetalles() {
			return detalles;
		}

		public void setDetalles(List<Object> detalles) {
			this.detalles = detalles;
		}
	    
	    
}
