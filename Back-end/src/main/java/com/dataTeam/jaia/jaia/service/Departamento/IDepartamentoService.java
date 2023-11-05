package com.dataTeam.jaia.jaia.service.Departamento;

import com.dataTeam.jaia.jaia.model.Cliente;
import com.dataTeam.jaia.jaia.model.Departamento;

import java.util.List;

public interface IDepartamentoService {

    List<Departamento> buscarTodosDepartamentos();

    Departamento criarDepartamento(Departamento departamento);

    public Departamento buscarPorIdDepart(Long idDepart);

    public void excluirDepartamentoPorIdDepart(Long idDepart);

    Departamento atualizarDepartamentoPorIdDepart(Long idDepart, Departamento departamentoAtualizado);



}
