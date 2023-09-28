package com.dataTeam.jaia.jaia.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DepartamentoComSupervisor {

    @JsonProperty("codDepart")
    private Long codDepart;

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("supervisores")
    private List<String> supervisores;


    public DepartamentoComSupervisor(Long codDepart, String nome, List<String> supervisores) {
        this.codDepart = codDepart;
        this.nome = nome;
        this.supervisores = supervisores;
    }

}