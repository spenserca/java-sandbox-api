package com.spenserca.sandbox.models;

import java.sql.Timestamp;

public class Team {
    private int id;
    private String name;
    private String description;
    private Timestamp createdDate;

    public Team(
            int id,
            String name,
            String description,
            Timestamp createdDate
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdDate = createdDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }
}
