package com.edu.edu_musica_aa2.models.services.interfaces;

import java.util.List;

import com.edu.edu_musica_aa2.models.entity.Generos;

public interface IServiciosGeneros {
    List<Generos> findAllGeneros();

    Generos saveGenero(Generos genero);

    Generos findByIdGenero(Long Id);

    Generos updateGenero(Generos genero);

    void deleteGenero(Long Id);
}
