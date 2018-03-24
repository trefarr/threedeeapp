package com.threedeeapp.threedeemodel.repository.repositories;

import com.threedeeapp.threedeemodel.repository.domain.rels.On;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "on", path = "on")
public interface OnRepository extends PagingAndSortingRepository<On, Long> {

}
