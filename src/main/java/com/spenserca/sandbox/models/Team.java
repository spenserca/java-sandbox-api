package com.spenserca.sandbox.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
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
