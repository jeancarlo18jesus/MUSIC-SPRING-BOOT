package com.edu.edu_musica_aa2.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.edu_musica_aa2.models.entity.Albumnes;
import com.edu.edu_musica_aa2.models.repository.RepositoryAlbumnes;
import com.edu.edu_musica_aa2.models.services.interfaces.IServiciosAlbumnes;




@Service
public class ServiciosAlbumnes implements IServiciosAlbumnes{

    @Autowired
    private RepositoryAlbumnes repository ;

    @Override
    public List<Albumnes> findAllAlbumnes() {
        return  (List<Albumnes>) repository.findAll() ;
    }

    @Override
    public Albumnes saveAlmbun(Albumnes albumn) {
       return  repository.save(albumn) ;
    }

    @Override
    public Albumnes findByIdAlbumn(Long Id) {
        return repository.findById(Id).orElse(null) ;
    }

    @Override
    public Albumnes updateAlbumn(Albumnes albumn) {
        return  repository.save(albumn) ;
    }

    @Override
    public void deleteAlbumn(Long Id) {
       repository.deleteById(Id);
    }
    
}
