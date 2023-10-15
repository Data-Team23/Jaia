package com.dataTeam.jaia.jaia.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "departamento")
@Getter
@Setter
public class Departamento {

    @Id
    @Column(name = "id_depart")
    private Long codDepart;

    @Column(name = "nome_depart")
    private String nome;

    @OneToMany(mappedBy = "departamento")
    @JsonIgnore
    private List<Funcionario> funcionarios;

}
