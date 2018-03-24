package com.threedeeapp.threedeemodel.service;

import com.threedeeapp.threedeemodel.domain.Mesh;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Set;

public interface ModelService {

    Mesh findByName(String name);

    Mesh create(MultipartFile file);

    Mesh create(MultipartFile file, Set<String> tags);
}
