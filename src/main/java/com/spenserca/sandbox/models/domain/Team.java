package com.spenserca.sandbox.models.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@AllArgsConstructor
public class Team {
    @Getter
    private int teamId;

    @Getter
    private String name;

    @Getter
    private String description;

    @Getter
    private Timestamp modifiedDate;
}
