package com.mitocode.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 3)
    private String siglas;

    @Column(nullable = false)
    private boolean estado;

    public Curso(String nombre, String siglas, boolean estado) {
        this.nombre = nombre;
        this.siglas = siglas;
        this.estado = estado;
    }
}
