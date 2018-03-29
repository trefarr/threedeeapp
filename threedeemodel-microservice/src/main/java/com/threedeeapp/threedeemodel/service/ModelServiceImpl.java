package com.threedeeapp.threedeemodel.service;

import com.threedeeapp.threedeemodel.domain.Mesh;
import com.threedeeapp.threedeemodel.domain.Model;
import com.threedeeapp.threedeemodel.domain.Tag;
import com.threedeeapp.threedeemodel.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.threedeeapp.threedeemodel.parser.OffFileParser;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.service.Tags;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

@Service
public class ModelServiceImpl implements ModelService {

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private TagService tagService;

    @Override
    public Mesh findByName(String name) {
        return null;
    }

    @Override
    public Mesh create(MultipartFile file) {
        modelRepository.save(new Model(file.getOriginalFilename()));
       return OffFileParser.buildMesh(file);
    }

    @Override
    public Mesh create(MultipartFile file, Set<String> tags) {
        Set<Tag> modelTags = new HashSet<>();
        tags.stream().forEach(tag -> {
            final Tag existingTag = tagService.findByTag(tag);
            if (existingTag != null){
                modelTags.add(existingTag);
            } else modelTags .add(new Tag(tag));
        });
        modelRepository.save(new Model(file.getOriginalFilename(), modelTags));
        return OffFileParser.buildMesh(file);
    }
}
