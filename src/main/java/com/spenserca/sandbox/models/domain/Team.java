package com.spenserca.sandbox.models.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@AllArgsConstructor
public class Team {
    @Getter
    private int id;

    @Getter
    private String name;

    @Getter
    private String description;

    @Getter
    private Timestamp createdDate;
}
