package com.threedeeapp.threedeemodel.service;

import com.threedeeapp.threedeemodel.domain.Mesh;
import com.threedeeapp.threedeemodel.domain.Model;
import com.threedeeapp.threedeemodel.domain.Tag;
import com.threedeeapp.threedeemodel.parser.OffFileParser;
import com.threedeeapp.threedeemodel.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public Tag findByTag(String tag) {
       return tagRepository.findByTag(tag);
    }

    @Override
    public void create(String tag) {
        tagRepository.save(new Tag(tag));
    }
}
