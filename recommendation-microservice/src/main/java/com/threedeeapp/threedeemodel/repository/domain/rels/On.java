package com.threedeeapp.threedeemodel.repository.domain.rels;

import com.threedeeapp.threedeemodel.repository.domain.nodes.Job;
import com.threedeeapp.threedeemodel.repository.domain.nodes.Event;
import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

@RelationshipEntity(type="ON")
public class On {
    @GraphId
    Long id;
    @StartNode
    Event event;
    @EndNode
    Job job;

    public On() {
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
}
