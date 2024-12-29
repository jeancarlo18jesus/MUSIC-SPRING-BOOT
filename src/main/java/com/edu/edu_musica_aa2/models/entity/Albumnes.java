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
@Entity(name="Albumnes")
@Table(name="albumnes")
public class Albumnes implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id ;

    @Column(name = "titulo" , nullable = false, length = 100)
    @NotBlank(message = "El titulo es obligatorio")
    private String titulo ;

    @ManyToOne
    @JoinColumn(name = "artista_id")
    @NotNull(message = "El artista es obligatorio")
    private Artista artista ;

    @Column(name = "fecha_lanzamiento" , nullable = false, length = 100)
    @NotBlank(message = "El fecha es obligatorio")
    private String fecha ;

}
