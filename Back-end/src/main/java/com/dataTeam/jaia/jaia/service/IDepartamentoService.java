package com.dataTeam.jaia.jaia.service;

import java.util.List;

import com.dataTeam.jaia.jaia.model.Departamento;

public interface IDepartamentoService {

    //add departamento

    public Departamento novoDepartamento( Departamento departamento);

    //delete departamento

    public Departamento deleteDepartamento(Long cod_depart);

    //listar departamento

    public List<Departamento> listarDepartamento();

    //update departamento
    public Departamento atualizarDepartamento(Long cod_depart, Departamento departamento);

}
