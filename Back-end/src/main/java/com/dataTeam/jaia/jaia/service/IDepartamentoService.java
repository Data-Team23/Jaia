package com.dataTeam.jaia.jaia.service;

import java.util.List;

import com.dataTeam.jaia.jaia.model.Departamento;

public interface IDepartamentoService {

    //add departamento
    public Departamento novoDepartamento(Departamento departamento);

    //delete departamento
    boolean deleteDepartamento(Long id);

    //listar departamento
    List<Departamento> listarDepartamento();

}
