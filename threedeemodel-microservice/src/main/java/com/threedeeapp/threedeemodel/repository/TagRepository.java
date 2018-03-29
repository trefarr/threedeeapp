package com.threedeeapp.threedeemodel.repository;

import com.threedeeapp.threedeemodel.domain.Model;
import com.threedeeapp.threedeemodel.domain.Tag;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TagRepository extends PagingAndSortingRepository<Tag, Long> {

    Tag findByTag(String tag);

    Tag findById(Long id);

}
