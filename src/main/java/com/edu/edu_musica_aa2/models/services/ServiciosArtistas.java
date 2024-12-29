package com.edu.edu_musica_aa2.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.edu_musica_aa2.models.entity.Artista;
import com.edu.edu_musica_aa2.models.repository.RepositoryArtistas;
import com.edu.edu_musica_aa2.models.services.interfaces.IServiciosArtistas;



@Service
public class ServiciosArtistas implements IServiciosArtistas{

    @Autowired
    private RepositoryArtistas repository ;

    @Override
    public List<Artista> findAllArtistas() {
        return  (List<Artista>) repository.findAll() ;
    }

    @Override
    public Artista saveArtista(Artista artista) {
       return repository.save(artista) ;
    }

    @Override
    public Artista findByIdArtista(Long Id) {
        return repository.findById(Id).orElse(null) ;
    }

    @Override
    public Artista updateArtista(Artista artista) {
        return repository.save(artista) ;
    }

    @Override
    public void deleteArtista(Long Id) {
       repository.deleteById(Id);
    }
    
}
