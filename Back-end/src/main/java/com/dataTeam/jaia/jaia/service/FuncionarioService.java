package com.dataTeam.jaia.jaia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.dataTeam.jaia.jaia.model.Funcionario;
import com.dataTeam.jaia.jaia.repository.FuncionarioRepository;

public class FuncionarioService implements IFuncionario{

    @Autowired
    private FuncionarioRepository funcionarioRepo;

    public Funcionario novoFuncionario(Funcionario funcionario){

        if(funcionario == null ||
                funcionario.getCpf_func() == null ||
                funcionario.getNome_func().isBlank() || 
                funcionario.getEmail_func() == null ||
                funcionario.getEmail_func().isBlank()) {
            throw new IllegalArgumentException("Funcionário com atributos inválidos!");
        }

        funcionario = funcionarioRepo.save(funcionario);

        return funcionario;
        
    }

    public List<Funcionario> buscarTodosFuncionarios() {
        return funcionarioRepo.findAll();
    }

    public Funcionario buscarPorCpf(Integer cpf) {
        Optional<Funcionario> funcionarioOp = funcionarioRepo.findById(cpf);
        
        if(funcionarioOp.isEmpty()) {
            throw new IllegalArgumentException("Funcionário nao encontrado!");
        }
        return funcionarioOp.get();    
    }

    
    public void deletarFuncionario(Integer cpf) {
        funcionarioRepo.deleteById(cpf);
    }

    
}
