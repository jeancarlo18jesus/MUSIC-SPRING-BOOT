package com.edu.edu_musica_aa2.models.services.interfaces;

import java.util.List;


import com.edu.edu_musica_aa2.models.entity.Artista;

public interface IServiciosArtistas {
    List<Artista> findAllArtistas();

    Artista saveArtista(Artista artista);

    Artista findByIdArtista(Long Id);

    Artista updateArtista(Artista artista);

    void deleteArtista(Long Id);
}
