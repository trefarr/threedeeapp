package com.threedeeapp.threedeemodel.domain;


import org.springframework.data.annotation.CreatedDate;

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

    @NotNull
    private String fileName;

    private Long createdAt;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "model_tag",
            joinColumns = @JoinColumn(name = "model_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    private Set<Classification> classifications;

    protected Model() { }

    public Model(String fileName) {
        this.fileName = fileName;
        this.classifications = new HashSet<>();
        createdAt = new Date().getTime();
    }

    public Model(String fileName, Set<Classification> classifications) {
        this.fileName = fileName;
        this.classifications = classifications;
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

       public void setClassifications(Set<Classification> classifications) {
        this.classifications = classifications;
    }

}
