package com.dataTeam.jaia.jaia.service.Departamento;

import com.dataTeam.jaia.jaia.model.Departamento;

import java.util.List;

public interface IDepartamentoService {

    List<Departamento> buscarTodosDepartamentos();

    Departamento criarDepartamento(Departamento departamento);

    public Departamento buscarPorCodDepart(Long codDepart);

}