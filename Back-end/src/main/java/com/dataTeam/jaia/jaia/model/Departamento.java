package com.dataTeam.jaia.jaia.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import org.springframework.context.annotation.Primary;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;


@Entity
@Table(name = "departamento")
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Departamento {

    @Id
    @Column(name = "cod_dep")
    private Long codDepart;

    @Column(name = "nome")
    private String nome;
    
    @OneToMany(mappedBy = "departamento", fetch = FetchType.EAGER)
    private List<Funcionario> funcionarios;

}
