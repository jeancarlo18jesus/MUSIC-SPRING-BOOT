package com.edu.edu_musica_aa2.models.services.interfaces;

import java.util.List;

import com.edu.edu_musica_aa2.models.entity.Albumnes;



public interface IServiciosAlbumnes {
    List<Albumnes> findAllAlbumnes();

    Albumnes saveAlmbun(Albumnes albumn);

    Albumnes findByIdAlbumn(Long Id);

    Albumnes updateAlbumn(Albumnes albumn);
    
    void deleteAlbumn(Long Id);
}
