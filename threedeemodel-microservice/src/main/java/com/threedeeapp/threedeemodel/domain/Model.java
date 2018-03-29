package com.threedeeapp.threedeemodel.domain;


import com.threedeeapp.threedeemodel.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Model {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private  Long id;

    @Column(unique=true, nullable=false)
    private String fileName;

    private Long createdAt;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "model_tag",
            joinColumns = @JoinColumn(name = "model_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    private Set<Tag> tags;

    protected Model() { }

    public Model(String fileName) {
        this.fileName = fileName;
        this.tags = new HashSet<>();
        createdAt = new Date().getTime();
    }

    public Model(String fileName, Set<Tag> tags) {
        this.fileName = fileName;
        this.tags = tags;
        createdAt = new Date().getTime();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

}
