package com.threedeeapp.threedeemodel.controller;

import com.threedeeapp.threedeemodel.domain.Mesh;
import com.threedeeapp.threedeemodel.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;

@RestController
//to add swagger tag
public class ModelController {

    @Autowired
    private ModelService modelService;


    @RequestMapping(path = "/{modelName}", method = RequestMethod.GET)
    public Mesh getModel(@PathVariable String modelName) {
        return modelService.findByName(modelName);
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public Mesh createNewModel(@RequestParam("file")  MultipartFile file) {
        return modelService.create(file);
    }

    @RequestMapping(path = "/test", method = RequestMethod.GET, name = "getTest")
    public String getTest() {
        return "testing 3dmodel com.threedeeapp.threedeemodel.service: test passed";
    }

    @RequestMapping(path ="/greeting", method = RequestMethod.GET, name = "greeting")
    public String greeting() {
        return "Hello from 3DModelEurekaClient!";
    }

}
