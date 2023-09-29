package com.dataTeam.jaia.jaia.service.Funcionario;


import com.dataTeam.jaia.jaia.model.Funcionario;

import java.util.List;

public interface IFuncionarioService {

    public Funcionario novoFuncionario (Funcionario funcionario );

    public List<Funcionario> buscarTodosFuncionario();

    public Funcionario buscarPorId(Long Id);

    public Funcionario deletarPorId(Long Id);

}
