package com.gd.examen.controller;

import java.security.NoSuchAlgorithmException;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gd.examen.controller.swagger.ExamenCtrSwagger;
import com.gd.examen.exceptions.DuplicationException;
import com.gd.examen.request.ExaamenRequestEnt;
import com.gd.examen.response.ExamenGenericResponse;
import com.gd.examen.service.ExamenService;
import com.gd.examen.util.RequestStore;
import com.gd.examen.util.Util;

import jakarta.transaction.Transactional;





@RestController
@RequestMapping("examenes")
@Transactional
public class ExamenController implements ExamenCtrSwagger{
    private final RequestStore requestStore = new RequestStore(60L * 1000L); // TTL de 1 minuto


	ExamenService examenService;
	ExamenController(ExamenService examenService){
		this.examenService=examenService;
	}
	
	@GetMapping("/obtener")
	public ResponseEntity<ExamenGenericResponse> obtener() {
	
		return  ResponseEntity.ok(examenService.obtenerExamenes()); 
	}
	
	@PostMapping("/guarda")
	public ResponseEntity<ExamenGenericResponse>  guarda(@Validated @RequestBody ExaamenRequestEnt entity) throws DuplicationException, NoSuchAlgorithmException {
        /*GENERAR HASH ÚNICO DE LOS DATOS DE LA SOLICITUD, DEBIDO A IMDEPOTENCIA AL SER UN POST DE GUARDADO*/
        String requestHash = Util.generateHash(entity.toString());

        /*VERIFICAR SI YA SE PROCESO*/
        if (requestStore.isDuplicate(requestHash)) {
            throw new DuplicationException("Transacción duplicada detectada");
        }
		return ResponseEntity.ok(examenService.guardaExamen(entity));
	}
	
	@PutMapping("/actualiza/{id}")
	public ResponseEntity<ExamenGenericResponse> actualiza(@Validated @PathVariable("id") int id, @Validated @RequestBody ExaamenRequestEnt entity) {
		return ResponseEntity.ok(examenService.actualizarExamen(id, entity));
	}
	
	@GetMapping("/obtener/{id}")
	public ResponseEntity<ExamenGenericResponse> obtenerXId(@Validated @PathVariable("id") int id) {
		return ResponseEntity.ok(examenService.obtenerExamenPorId(id));
	}
	
	
	
}
