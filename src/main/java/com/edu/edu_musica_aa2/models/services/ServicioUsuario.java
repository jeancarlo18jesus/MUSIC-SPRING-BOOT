package com.edu.edu_musica_aa2.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.edu.edu_musica_aa2.models.entity.Usuario;
import com.edu.edu_musica_aa2.models.repository.RepositoryUsuario;

@Service
public class ServicioUsuario {
    
    @Autowired
    private RepositoryUsuario usuarioRepository;


     public Usuario findByIdUsuario(Long Id) {
       return usuarioRepository.findById(Id).orElse(null) ;
    }
    public boolean existePorUsername(String username) {
        return usuarioRepository.existsByUsername(username);
    }

    public List<Usuario> findAllUsuarios() {
        return (List<Usuario>) usuarioRepository.findAll();
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario updateUsuario(Usuario usuario) {
        return  usuarioRepository.save(usuario) ;
    }

    public void deleteUsuario(Long Id) {
        usuarioRepository.deleteById(Id);
    }
}
