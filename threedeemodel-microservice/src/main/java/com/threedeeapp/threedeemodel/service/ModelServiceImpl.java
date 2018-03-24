package com.threedeeapp.threedeemodel.service;

import com.threedeeapp.threedeemodel.domain.Mesh;
import com.threedeeapp.threedeemodel.domain.Model;
import com.threedeeapp.threedeemodel.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.threedeeapp.threedeemodel.parser.OffFileParser;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Set;

@Service
public class ModelServiceImpl implements ModelService {

    @Autowired
    private ModelRepository modelRepository;

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
        modelRepository.save(new Model(file.getName()));
        return OffFileParser.buildMesh(file);
    }
}
