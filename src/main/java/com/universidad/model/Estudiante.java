package com.universidad.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "estudiantes")
public class Estudiante extends Persona {
    @Column(unique = true)
    private String numeroInscripcion;
}