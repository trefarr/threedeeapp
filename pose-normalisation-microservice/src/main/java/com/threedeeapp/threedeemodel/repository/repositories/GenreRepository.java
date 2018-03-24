package com.threedeeapp.threedeemodel.repository.repositories;

import com.threedeeapp.threedeemodel.repository.domain.nodes.Genre;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "genres", path = "genres")
public interface GenreRepository extends PagingAndSortingRepository<Genre, Long> {
    List<Genre> findByName(@Param("0") String name);
}
