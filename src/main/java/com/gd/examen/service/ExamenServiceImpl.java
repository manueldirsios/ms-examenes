package com.gd.examen.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gd.examen.entity.ExamenEnt;
import com.gd.examen.repository.ExamenRepository;
import com.gd.examen.request.ExaamenRequestEnt;
import com.gd.examen.response.ExamenGenericResponse;
import com.gd.examen.response.ExamenResponse;



@Service
@Transactional
public class ExamenServiceImpl implements ExamenService {
	private ModelMapper mapper = new ModelMapper();
	private static final String MSG_EXITO="Exito";
	private  ExamenRepository examRepo;
	
	ExamenServiceImpl(ExamenRepository examRepo){
		this.examRepo=examRepo;
	}
	
	@Override
	public ExamenGenericResponse guardaExamen(ExaamenRequestEnt examenReq) {
		ExamenEnt entExam=new ExamenEnt();
		ExamenGenericResponse reponse=new ExamenGenericResponse();

		
		entExam.setFecha(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy") ));
		entExam.setTipo(examenReq.getTipo());
		entExam.setNombre(examenReq.getNombre());
		examRepo.save(entExam);
		
		reponse.setCodigoRespuesta(0);
		reponse.setTransaccion(examenReq);
		reponse.setMensaje(MSG_EXITO);
		return reponse;
	}

	@Override
	public ExamenGenericResponse actualizarExamen(int id, ExaamenRequestEnt examenReq) {
		ExamenGenericResponse reponse=new ExamenGenericResponse();

		ExamenEnt entExam=examRepo.findById(id).orElseThrow();
		entExam.setNombre(examenReq.getNombre());
		entExam.setFecha(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy") ));
		entExam.setTipo(examenReq.getTipo());
		examRepo.save(entExam);
		reponse.setCodigoRespuesta(0);
		reponse.setTransaccion(examenReq);
		reponse.setMensaje(MSG_EXITO);
		
		return reponse;
	}

	@Override
	public ExamenGenericResponse obtenerExamenes() {
		
		ExamenGenericResponse reponse=new ExamenGenericResponse();
		
		List<ExamenResponse> listResponseExam = mapper.map(examRepo.findAll(), new TypeToken<List<ExamenResponse>>(){}.getType());
		reponse.setCodigoRespuesta(0);
		reponse.setMensaje(MSG_EXITO);
		reponse.setTransaccion(listResponseExam);
		
		return reponse;
	}

	@Override
	public ExamenGenericResponse obtenerExamenPorId(int id) {
		ExamenGenericResponse reponse=new ExamenGenericResponse();

		List<ExamenResponse> listResponseExam = mapper.map(examRepo.findAll(), new TypeToken<List<ExamenResponse>>(){}.getType());
		reponse.setCodigoRespuesta(0);
		reponse.setMensaje(MSG_EXITO);
		reponse.setTransaccion(listResponseExam);
		
		return reponse;
	}

}
