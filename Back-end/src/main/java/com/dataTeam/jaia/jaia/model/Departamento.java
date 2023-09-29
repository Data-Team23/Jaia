package com.dataTeam.jaia.jaia.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "departamento")
@Getter
@Setter
public class Departamento {

    @Id
    @Column(name = "cod_dep")
    private Long codDepart;

    @Column(name = "nome")
    private String nome;

    @OneToMany(mappedBy = "departamento")
    @JsonManagedReference
    // @JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="id")
    private List<Funcionario> funcionarios;

}
