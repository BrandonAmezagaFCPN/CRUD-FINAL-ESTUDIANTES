package com.universidad.service.impl;

import com.universidad.dto.EstudianteDTO;
import com.universidad.model.Estudiante;
import com.universidad.repository.EstudianteRepository;
import com.universidad.service.IEstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstudianteServiceImpl implements IEstudianteService {

    private final EstudianteRepository estudianteRepository;

    @Autowired
    public EstudianteServiceImpl(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    @Override
    public List<EstudianteDTO> obtenerTodosLosEstudiantes() {
        return estudianteRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EstudianteDTO obtenerPorId(Long id) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        return convertToDTO(estudiante);
    }

    @Override
    public EstudianteDTO guardar(EstudianteDTO estudianteDTO) {
        Estudiante estudiante = convertToEntity(estudianteDTO);
        Estudiante estudianteGuardado = estudianteRepository.save(estudiante);
        return convertToDTO(estudianteGuardado);
    }

    @Override
    public EstudianteDTO actualizar(Long id, EstudianteDTO estudianteDTO) {
        return estudianteRepository.findById(id)
                .map(estudianteExistente -> {
                    Estudiante estudianteActualizado = convertToEntity(estudianteDTO);
                    estudianteActualizado.setId(id);
                    return convertToDTO(estudianteRepository.save(estudianteActualizado));
                })
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
    }

    @Override
    public void eliminar(Long id) {
        estudianteRepository.deleteById(id);
    }

    private EstudianteDTO convertToDTO(Estudiante estudiante) {
        return EstudianteDTO.builder()
                .id(estudiante.getId())
                .nombre(estudiante.getNombre())
                .apellido(estudiante.getApellido())
                .email(estudiante.getEmail())
                .fechaNacimiento(estudiante.getFechaNacimiento())
                .numeroInscripcion(estudiante.getNumeroInscripcion())
                .build();
    }

    private Estudiante convertToEntity(EstudianteDTO estudianteDTO) {
        return Estudiante.builder()
                .id(estudianteDTO.getId())
                .nombre(estudianteDTO.getNombre())
                .apellido(estudianteDTO.getApellido())
                .email(estudianteDTO.getEmail())
                .fechaNacimiento(estudianteDTO.getFechaNacimiento())
                .numeroInscripcion(estudianteDTO.getNumeroInscripcion())
                .build();
    }
}