package com.edu.edu_musica_aa2.models.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.edu_musica_aa2.models.entity.Rol;

@Repository
public interface RepositoryRole extends JpaRepository<Rol, Long> {
    
}
