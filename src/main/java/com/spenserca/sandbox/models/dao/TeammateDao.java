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
@Table(schema = "spenserca", name = "Teammate")
public class TeammateDao {
    @Id
    @NotNull
    @Column(name = "Id")
    private int id;

    @NotNull
    @Column(name = "FirstName")
    private String firstName;

    @NotNull
    @Column(name = "LastName")
    private String lastName;

    @Column(name = "NickName")
    private String nickName;

    @Column(name = "ModifiedDate")
    private Timestamp modifiedDate;
}
