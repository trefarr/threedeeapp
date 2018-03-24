package com.threedeeapp.threedeemodel.repository.domain.rels;

import com.threedeeapp.threedeemodel.repository.domain.nodes.Event;
import com.threedeeapp.threedeemodel.repository.domain.nodes.User;
import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

@RelationshipEntity(type = "CAUSED")
public class Caused {
    @GraphId
    Long id;
    @StartNode
    User user;
    @EndNode
    Event event;
}
