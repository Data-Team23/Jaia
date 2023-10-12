package com.dataTeam.jaia.jaia.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "departamento")
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "codDepart")
public class Departamento {

    @Id
    @Column(name = "id_Depart")
    private Long codDepart;

    @Column(name = "nome_Depart")
    private String nome;

    @OneToMany(mappedBy = "departamento")
    @JsonManagedReference
    private List<Funcionario> funcionarios;

}
