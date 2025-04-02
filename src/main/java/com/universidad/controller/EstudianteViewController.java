package com.universidad.controller;

import com.universidad.dto.EstudianteDTO;
import com.universidad.service.IEstudianteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/estudiantes")
public class EstudianteViewController {

    private final IEstudianteService estudianteService;

    public EstudianteViewController(IEstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @GetMapping
    public String listarEstudiantes(Model model) {
        model.addAttribute("estudiantes", estudianteService.obtenerTodosLosEstudiantes());
        return "estudiantes/listar";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("estudiante", new EstudianteDTO());
        return "estudiantes/formulario";
    }

    @PostMapping("/guardar")
    public String guardarEstudiante(@ModelAttribute("estudiante") EstudianteDTO estudianteDTO,
                                    RedirectAttributes redirectAttributes) {

        if (estudianteDTO.getId() == null) {
            estudianteService.guardar(estudianteDTO);
            redirectAttributes.addFlashAttribute("statusCode", "201");
            redirectAttributes.addFlashAttribute("message", "✅ Estudiante creado (201 Creado)");
        } else {
            estudianteService.actualizar(estudianteDTO.getId(), estudianteDTO);
            redirectAttributes.addFlashAttribute("statusCode", "200");
            redirectAttributes.addFlashAttribute("message", "🔄 Estudiante actualizado (200 OK)");
        }
        return "redirect:/estudiantes";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        model.addAttribute("estudiante", estudianteService.obtenerPorId(id));
        return "estudiantes/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEstudiante(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        estudianteService.eliminar(id);
        redirectAttributes.addFlashAttribute("statusCode", "204");
        redirectAttributes.addFlashAttribute("message", "🗑️ Estudiante eliminado (204 No Content)");
        return "redirect:/estudiantes";
    }
}