package com.edu.edu_musica_aa2.models.entity;




import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="Canciones")
@Table(name="canciones")
public class Canciones implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id ;

    @Column(name = "titulo" , nullable = false, length = 100)
    @NotBlank(message = "El titulo es obligatorio")
    private String titulo ;

    @Column(name = "duracion" , nullable = false, length = 100)
    @NotBlank(message = "El duracion es obligatorio")
    private String duracion ;

    @ManyToOne
    @JoinColumn(name = "album_id")
    @NotNull(message = "El albumn es obligatorio")
    private Albumnes albumnes;

    @ManyToOne
    @JoinColumn(name = "genero_id")
    @NotNull(message = "El genero es obligatorio")
    private Generos genero ;

    
}
