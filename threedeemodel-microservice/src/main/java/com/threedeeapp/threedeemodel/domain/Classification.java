package com.threedeeapp.threedeemodel.domain;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Classification {

    @Id @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToMany(mappedBy="classifications")
    private Set<Model> models;

    private String tag;

    protected Classification() {
    }

    public Classification(String tag) {
        this.tag = tag;
        this.models = new HashSet<Model>();
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
