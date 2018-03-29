package com.threedeeapp.threedeemodel.domain;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Tag {

    @Column(unique=true, nullable=false)
    private String tag;

    @ManyToMany(mappedBy="tags")
    private Set<Model> models;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    protected Tag() {
    }

    public Tag(String tag) {
        this.tag = tag;
        this.models = new HashSet<Model>();
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Model> getModels() {
        return models;
    }

    public void setModels(Set<Model> models) {
        this.models = models;
    }
}
