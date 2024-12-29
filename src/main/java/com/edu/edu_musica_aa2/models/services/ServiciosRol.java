package com.edu.edu_musica_aa2.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.edu.edu_musica_aa2.models.entity.Rol;
import com.edu.edu_musica_aa2.models.repository.RepositoryRole;


@Service
public class ServiciosRol {
    @Autowired
    private RepositoryRole rolRepository;

    public Rol findByIdRol(Long Id) {
       return rolRepository.findById(Id).orElse(null) ;
    }

    public List<Rol> findAllRoles() {
        return (List<Rol>) rolRepository.findAll();
    }

    public Rol save(Rol usuario) {
        return rolRepository.save(usuario);
    }

    public Rol updateRol(Rol usuario) {
        return  rolRepository.save(usuario) ;
    }

    public void deleteRol(Long Id) {
        rolRepository.deleteById(Id);
    }
}
