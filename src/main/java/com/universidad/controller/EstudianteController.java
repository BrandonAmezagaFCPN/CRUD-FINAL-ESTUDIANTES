package com.universidad.controller;

import com.universidad.dto.EstudianteDTO;
import com.universidad.service.IEstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    private final IEstudianteService estudianteService;

    @Autowired
    public EstudianteController(IEstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    // a) Obtener estudiante por ID (GET)
    @GetMapping("/{id}")
    public ResponseEntity<EstudianteDTO> obtenerEstudiantePorId(@PathVariable Long id) {
        EstudianteDTO estudiante = estudianteService.obtenerPorId(id);
        return ResponseEntity.ok(estudiante);
    }

    // b) Crear nuevo estudiante (POST)
    @PostMapping
    public ResponseEntity<EstudianteDTO> crearEstudiante(@RequestBody EstudianteDTO estudianteDTO) {
        EstudianteDTO nuevoEstudiante = estudianteService.guardar(estudianteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEstudiante);
    }

    // a) Actualizar estudiante (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<EstudianteDTO> actualizarEstudiante(
            @PathVariable Long id,
            @RequestBody EstudianteDTO estudianteDTO) {
        EstudianteDTO estudianteActualizado = estudianteService.actualizar(id, estudianteDTO);
        return ResponseEntity.ok(estudianteActualizado);
    }

    // c) Eliminar estudiante (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEstudiante(@PathVariable Long id) {
        estudianteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // Listar todos los estudiantes (GET)
    @GetMapping
    public ResponseEntity<List<EstudianteDTO>> listarTodosLosEstudiantes() {
        List<EstudianteDTO> estudiantes = estudianteService.obtenerTodosLosEstudiantes();
        return ResponseEntity.ok(estudiantes);
    }
}