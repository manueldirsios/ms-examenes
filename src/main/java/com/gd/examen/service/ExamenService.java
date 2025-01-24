package com.gd.examen.service;

import com.gd.examen.request.ExaamenRequestEnt;
import com.gd.examen.response.ExamenGenericResponse;

public interface ExamenService {
	public ExamenGenericResponse guardaExamen(ExaamenRequestEnt examen);
	public ExamenGenericResponse actualizarExamen(int id,ExaamenRequestEnt examen);
	public ExamenGenericResponse obtenerExamenes();
	public ExamenGenericResponse obtenerExamenPorId(int id);

}
