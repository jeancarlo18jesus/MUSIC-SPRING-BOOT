package com.edu.edu_musica_aa2.models.repository;




import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.edu.edu_musica_aa2.models.entity.Generos;




@Repository
public interface RepositoryGeneros  extends CrudRepository<Generos , Long>{
    
}
