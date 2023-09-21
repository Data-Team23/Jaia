package com.dataTeam.jaia.jaia.service;

import java.util.List;

import com.dataTeam.jaia.jaia.model.Funcionario;

public interface IFuncionario {
    
    public Funcionario novoFuncionario(Funcionario funcionario);

    public List<Funcionario> buscarTodosFuncionarios();

    public Funcionario buscarPorCpf(Integer cpf);

    public void deletarFuncionario(Integer cpf);
    

}
