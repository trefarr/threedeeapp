package com.threedeeapp.threedeemodel.service;

import com.threedeeapp.threedeemodel.domain.Mesh;
import com.threedeeapp.threedeemodel.domain.Tag;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public interface TagService {

    Tag findByTag(String tag);

    void create(String tag);

}
