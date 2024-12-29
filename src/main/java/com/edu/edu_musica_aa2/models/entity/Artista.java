package com.edu.edu_musica_aa2.models.entity;


import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="Artistas")
@Table(name="artistas")
public class Artista implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id ;

    @Column(name = "nombre" , nullable = false, length = 100)
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre ;

    @Column(name = "fecha_nacimiento" , nullable = false, length = 100)
    @NotBlank(message = "El fecha es obligatorio")
    private String fecha ;

    @Column(name = "nacionalidad" , nullable = false, length = 100)
    @NotBlank(message = "El nacionalidad es obligatorio")
    private String nacionalidad ;

}