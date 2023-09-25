package com.dataTeam.jaia.jaia.service.Departamento;


import com.dataTeam.jaia.jaia.model.Departamento;

import java.util.List;


public interface IDepartamentoSerivce {



    public Departamento novoDepartamento (Departamento departamento );

    public Departamento deleteDepartamento(Long Cod_depart);

    public List<Departamento> listarDepartamento();

    public Departamento atualizarDepartamento(Long Cod_depart, Departamento atualizarDepartamento);

    List<Departamento> buscarTodosDepartamentos();
}
