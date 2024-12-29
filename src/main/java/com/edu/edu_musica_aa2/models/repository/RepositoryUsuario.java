package com.edu.edu_musica_aa2.models.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.edu_musica_aa2.models.entity.Usuario;


@Repository
public interface RepositoryUsuario extends JpaRepository<Usuario, Long>  {
    Usuario findByUsername (String username) ;
    boolean existsByUsername(String username);
}
