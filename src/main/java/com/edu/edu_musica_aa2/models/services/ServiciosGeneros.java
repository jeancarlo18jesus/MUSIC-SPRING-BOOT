package com.edu.edu_musica_aa2.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.edu_musica_aa2.models.entity.Generos;
import com.edu.edu_musica_aa2.models.repository.RepositoryGeneros;
import com.edu.edu_musica_aa2.models.services.interfaces.IServiciosGeneros;


@Service
public class ServiciosGeneros implements IServiciosGeneros {

    @Autowired
    private RepositoryGeneros repository ;

    @Override
    public List<Generos> findAllGeneros() {
        return (List<Generos>) repository.findAll();
    }

    @Override
    public Generos saveGenero(Generos genero) {
        return repository.save(genero) ;
    }

    @Override
    public Generos findByIdGenero(Long Id) {
       return repository.findById(Id).orElse(null);
    }

    @Override
    public Generos updateGenero(Generos genero) {
        return  repository.save(genero) ;
    }

    @Override
    public void deleteGenero(Long Id) {
        repository.deleteById(Id);
    }
    
}
