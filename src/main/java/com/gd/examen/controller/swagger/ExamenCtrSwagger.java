package com.gd.examen.controller.swagger;

import java.security.NoSuchAlgorithmException;

import org.springframework.http.ResponseEntity;

import com.gd.examen.exceptions.DuplicationException;
import com.gd.examen.request.ExaamenRequestEnt;
import com.gd.examen.response.EntFailResponse;
import com.gd.examen.response.ExamenGenericResponse;
import com.gd.examen.util.Constants;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Proyecto", description = "API para gestión de algun proyecto") // Documentación para el grupo de rutas
public interface ExamenCtrSwagger {
	@Operation(summary = "Obtiene informacion")
    @SecurityRequirement(name = "BearerAuth") // Requiere autorización
    @ApiResponses(value = { 
            @ApiResponse(responseCode =  Constants.API_CODE_201, description = Constants.API_DESC_201,content = @Content(schema = @Schema(implementation = ExamenGenericResponse.class))), 
            @ApiResponse(responseCode =  Constants.API_CODE_400, description = Constants.API_DESC_400,content = @Content(schema = @Schema(implementation = EntFailResponse.class))), 
            @ApiResponse(responseCode =  Constants.API_CODE_401, description = Constants.API_DESC_401,content = @Content(schema = @Schema(implementation = EntFailResponse.class))),
            @ApiResponse(responseCode =  Constants.API_CODE_500, description = Constants.API_DESC_500,content = @Content(schema = @Schema(implementation = EntFailResponse.class)))}) 
	public ResponseEntity<ExamenGenericResponse> obtener() ;
	

	@Operation(summary = "Guarda Informacion")
    @SecurityRequirement(name = "BearerAuth") // Requiere autorización
    @ApiResponses(value = { 
            @ApiResponse(responseCode =  Constants.API_CODE_201, description = Constants.API_DESC_201,content = @Content(schema = @Schema(implementation = ExamenGenericResponse.class))), 
            @ApiResponse(responseCode =  Constants.API_CODE_400, description = Constants.API_DESC_400,content = @Content(schema = @Schema(implementation = EntFailResponse.class))), 
            @ApiResponse(responseCode =  Constants.API_CODE_401, description = Constants.API_DESC_401,content = @Content(schema = @Schema(implementation = EntFailResponse.class))),
            @ApiResponse(responseCode =  Constants.API_CODE_500, description = Constants.API_DESC_500,content = @Content(schema = @Schema(implementation = EntFailResponse.class)))}) 

	public ResponseEntity<ExamenGenericResponse>  guarda(ExaamenRequestEnt entity) throws DuplicationException,NoSuchAlgorithmException;
	

	@Operation(summary = "Actualiza Informacion")
    @ApiResponses(value = { 
            @ApiResponse(responseCode =  Constants.API_CODE_201, description = Constants.API_DESC_201,content = @Content(schema = @Schema(implementation = ExamenGenericResponse.class))), 
            @ApiResponse(responseCode =  Constants.API_CODE_400, description = Constants.API_DESC_400,content = @Content(schema = @Schema(implementation = EntFailResponse.class))), 
            @ApiResponse(responseCode =  Constants.API_CODE_401, description = Constants.API_DESC_401,content = @Content(schema = @Schema(implementation = EntFailResponse.class))),
            @ApiResponse(responseCode =  Constants.API_CODE_500, description = Constants.API_DESC_500,content = @Content(schema = @Schema(implementation = EntFailResponse.class)))}) 
	public ResponseEntity<ExamenGenericResponse> actualiza(int id,  ExaamenRequestEnt entity) ;

	

	@Operation(summary = "Obtiene Informacion X id")
    @ApiResponses(value = { 
            @ApiResponse(responseCode =  Constants.API_CODE_201, description = Constants.API_DESC_201,content = @Content(schema = @Schema(implementation = ExamenGenericResponse.class))), 
            @ApiResponse(responseCode =  Constants.API_CODE_400, description = Constants.API_DESC_400,content = @Content(schema = @Schema(implementation = EntFailResponse.class))), 
            @ApiResponse(responseCode =  Constants.API_CODE_401, description = Constants.API_DESC_401,content = @Content(schema = @Schema(implementation = EntFailResponse.class))),
            @ApiResponse(responseCode =  Constants.API_CODE_500, description = Constants.API_DESC_500,content = @Content(schema = @Schema(implementation = EntFailResponse.class)))}) 
	public ResponseEntity<ExamenGenericResponse> obtenerXId(int param) ;
}
