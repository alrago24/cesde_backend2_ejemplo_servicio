package com.example.demo_service.model.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidad que representa a un Usuario en el sistema.
 * Se utilizan anotaciones de JPA para la persistencia y Lombok para reducir el código repetitivo (Boilerplate).
 */
@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private Integer edad;

    // Los estudiantes deben notar que gracias a Lombok (@Getter @Setter),
    // no necesitamos escribir explícitamente los métodos getNombre(), setNombre(), etc.
}
