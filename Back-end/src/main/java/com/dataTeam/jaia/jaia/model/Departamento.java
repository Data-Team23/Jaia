package com.dataTeam.jaia.jaia.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "departamento")
@Getter
@Setter
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_depart")
    private Long codDepart;

    @Column(name = "nome_depart")
    private String nome;

    // @OneToMany(mappedBy = "departamento")
    // @JsonManagedReference
    // // @JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="id")
    // private List<Funcionario> funcionarios;

}
