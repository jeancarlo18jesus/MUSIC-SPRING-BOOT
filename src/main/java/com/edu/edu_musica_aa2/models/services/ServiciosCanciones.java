package com.edu.edu_musica_aa2.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.edu_musica_aa2.models.entity.Canciones;
import com.edu.edu_musica_aa2.models.repository.RepositoryCanciones;
import com.edu.edu_musica_aa2.models.services.interfaces.IServiciosCanciones;

@Service
public class ServiciosCanciones implements IServiciosCanciones {

     @Autowired
    private RepositoryCanciones repository ;

    @Override
    public List<Canciones> findAllCanciones() {
       return  (List<Canciones>) repository.findAll() ;
    }

    @Override
    public Canciones saveCancion(Canciones cancion) {
        return repository.save(cancion);
    }

    @Override
    public Canciones findByIdCancion(Long Id) {
       return repository.findById(Id).orElse(null) ;
    }

    @Override
    public Canciones updateCancion(Canciones cancion) {
        return repository.save(cancion);
    }

    @Override
    public void deleteCancion(Long Id) {
        repository.deleteById(Id);
    }
    
}
