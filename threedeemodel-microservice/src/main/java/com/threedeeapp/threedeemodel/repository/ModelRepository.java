package com.threedeeapp.threedeemodel.repository;

import com.threedeeapp.threedeemodel.domain.Model;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ModelRepository extends PagingAndSortingRepository<Model, Long> {

    Model findByFileName(String fileName);

    Model findById(Long id);

    List<Model> findByTags(String tag);

}
