package com.spenserca.sandbox.models.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "spenserca", name = "Team")
public class TeamDao {
    @Id
    @NotNull
    @Column(name = "Id")
    private int id;

    @NotNull
    @Column(name = "Name")
    private String name;

    @Column(name = "Description")
    private String description;

    @NotNull
    @Column(name = "CreatedDate")
    private Timestamp createdDate;
}
