package com.edu.edu_musica_aa2.models.repository;





import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.edu.edu_musica_aa2.models.entity.Albumnes;


@Repository
public interface RepositoryAlbumnes extends CrudRepository<Albumnes, Long> {
    
}
