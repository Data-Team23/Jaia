package com.dataTeam.jaia.jaia.service.Funcionario;


import com.dataTeam.jaia.jaia.model.Funcionario;
import com.dataTeam.jaia.jaia.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService implements IFuncionarioService {


    @Autowired
    private  FuncionarioRepository funcrepo;

    @Transactional
    public Funcionario novoFuncionario(Funcionario funcionario) {
        if(funcionario == null ||
                funcionario.getCpf() == null ||
                funcionario.getCpf().isNaN() ||
                funcionario.getNome() == null ||
                funcionario.getNome().isBlank() ||
                funcionario.getEmail() == null ||
                funcionario.getEmail().isBlank() ||
                funcionario.getSupervisor() == null ||
                funcionario.getSupervisor().isBlank()){
            throw new IllegalArgumentException("Funcionario com atributos inválidos!");
        }
        return funcionario;
    }
    public List<Funcionario> buscarTodosFuncionario() {
        return funcrepo.findAll();
    }

    public Funcionario buscarPorId(Long id) {
        Optional<Funcionario> funcionarioOp = funcrepo.findById(id);
        if(funcionarioOp.isEmpty()) {
            throw new IllegalArgumentException("Usuario nao encontrado!");
        }
        return funcionarioOp.get();
    }
}
