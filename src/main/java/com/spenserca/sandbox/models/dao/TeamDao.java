package com.spenserca.sandbox.models.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "public", name = "Team")
public class TeamDao {
    @Id
    @NotNull
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(name = "Name")
    private String name;

    @Column(name = "Description")
    private String description;

    @UpdateTimestamp
    @Column(name = "ModifiedDate")
    private Timestamp modifiedDate;
}
