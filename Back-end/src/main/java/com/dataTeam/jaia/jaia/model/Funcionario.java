package com.dataTeam.jaia.jaia.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "funcionario")
@Getter
@Setter
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_func")
    private Long id;

    @Column(name = "cpf_func")
    private Integer cpf;

    @Column(name = "nome_func")
    private String nome;

    @Column(name = "email_func")
    private String email;

    @Column(name = "senha_func")
    private String senha;

    // @ManyToOne(fetch = FetchType.EAGER)
    // @JoinColumn(name = "fk_func_id_depart")
    // @JsonBackReference
    // private Departamento departamento;

}
