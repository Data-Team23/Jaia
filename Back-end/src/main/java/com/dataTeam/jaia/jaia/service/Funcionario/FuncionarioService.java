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
    private FuncionarioRepository funcrepo;

    @Transactional
    public Funcionario novoFuncionario(Funcionario funcionario) {
        return funcrepo.save(funcionario);
    }

    public List<Funcionario> buscarTodosFuncionario() {
        List<Funcionario> funcionarios = funcrepo.findAll();
        return funcionarios;
    }

    public Funcionario buscarPorId(Long id) {
        Optional<Funcionario> funcionarioOp = funcrepo.findByIdWithDepartamento(id);
        if (funcionarioOp.isEmpty()) {
            throw new IllegalArgumentException("Usuario nao encontrado!");
        }
        Funcionario funcionario = funcionarioOp.get();
        return funcionario;
    }

    public Funcionario deletarPorId(Long id) {
        Optional<Funcionario> funcionarioOp = funcrepo.findById(id);
        if (funcionarioOp.isEmpty()) {
            throw new IllegalArgumentException("Usuario nao encontrado!");
        }
        Funcionario funcionarioToDelete = funcionarioOp.get();
        
        funcrepo.delete(funcionarioToDelete);

        return funcionarioToDelete;
    }
}
