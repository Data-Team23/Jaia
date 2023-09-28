package com.dataTeam.jaia.jaia.service.Departamento;

import com.dataTeam.jaia.jaia.model.Departamento;
import com.dataTeam.jaia.jaia.model.Funcionario;
import com.dataTeam.jaia.jaia.repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartamentoService implements IDepartamentoService {

    @Autowired
    private DepartamentoRepository departrepo;

    public List<Departamento> buscarTodosDepartamentos() {
        List<Departamento> departamentos = departrepo.findAll();
        for (Departamento d : departamentos) {
            for (Funcionario f : d.getFuncionarios()) {
                f.setDepartamento(null);
            }
        }
        return departamentos;
    }

    public Departamento criarDepartamento(Departamento departamento) {
        return departrepo.save(departamento);
    }

    public Departamento buscarPorCodDepart(Long codDepart) {
        Optional<Departamento> departamentoExistente = departrepo.findByCodDepart(codDepart);
        if (departamentoExistente.isEmpty()) {
            try {
                throw new Exception("Departamento com cod " + codDepart + " não encontrado.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Departamento departamentoEncontrado = departamentoExistente.get();
        return departamentoEncontrado;
    }

}
