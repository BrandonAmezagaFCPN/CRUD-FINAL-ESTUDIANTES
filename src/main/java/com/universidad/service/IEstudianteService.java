package com.universidad.service;

import com.universidad.dto.EstudianteDTO;
import java.util.List;

public interface IEstudianteService {
    List<EstudianteDTO> obtenerTodosLosEstudiantes();
    EstudianteDTO obtenerPorId(Long id);
    EstudianteDTO guardar(EstudianteDTO estudianteDTO);
    EstudianteDTO actualizar(Long id, EstudianteDTO estudianteDTO);
    void eliminar(Long id);
}