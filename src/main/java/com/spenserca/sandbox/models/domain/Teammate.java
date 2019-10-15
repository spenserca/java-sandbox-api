package com.spenserca.sandbox.models.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@AllArgsConstructor
public class Teammate {
    @Getter
    private int id;

    @Getter
    private String firstName;

    @Getter
    private String lastName;

    @Getter
    private String nickName;

    @Getter
    private Timestamp modifiedDate;
}
