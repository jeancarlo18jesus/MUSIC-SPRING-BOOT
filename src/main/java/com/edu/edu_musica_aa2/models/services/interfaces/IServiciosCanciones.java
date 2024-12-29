package com.edu.edu_musica_aa2.models.services.interfaces;

import java.util.List;

import com.edu.edu_musica_aa2.models.entity.Canciones;



public interface IServiciosCanciones {
     List<Canciones> findAllCanciones();

     Canciones saveCancion(Canciones cancion);

     Canciones findByIdCancion(Long Id);

     Canciones updateCancion(Canciones cancion);
    
    void deleteCancion(Long Id);
}
