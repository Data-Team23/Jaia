package com.dataTeam.jaia.jaia.service.Departamento;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dataTeam.jaia.jaia.model.Cliente;
import com.dataTeam.jaia.jaia.model.Departamento;
import com.dataTeam.jaia.jaia.repository.DepartamentoRepository;

import jakarta.transaction.Transactional;

@Service
public class DepartamentoService implements IDepartamentoService {

    @Autowired
    private DepartamentoRepository departrepo;

    public List<Departamento> buscarTodosDepartamentos() {
        List<Departamento> departamentos = departrepo.findAll();
        return departamentos;
    }

    public Departamento criarDepartamento(Departamento departamento) {
        return departrepo.save(departamento);
    }

    public Departamento buscarPorCodDepart(Long codDepart) {
        Optional<Departamento> departamentoExistente = departrepo.findByIdDepart(codDepart);
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

    @Transactional
    public Departamento buscarPorIdDepart(Long idDepart) {
        try {
            Optional<Departamento> departamentoExistente = departrepo.findById(idDepart);
            if (departamentoExistente.isEmpty()) {
                throw new Exception("Departamento com idDepart " + idDepart + " não encontrado.");
            }
            Departamento departamentoEncontrado = departamentoExistente.get();
            return departamentoEncontrado;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void excluirDepartamentoPorIdDepart(Long idDepart) {
        Optional<Departamento> departamentoOptional = departrepo.findById(idDepart);
        departamentoOptional.ifPresent(departamento -> departrepo.delete(departamento));
    }

    @Transactional
    public Departamento atualizarDepartamentoPorIdDepart(Long idDepart, Departamento departamentoAtualizado) {
        Optional<Departamento> departamentoExistente = departrepo.findByIdDepart(idDepart);

        if (departamentoExistente.isEmpty()) {
            try {
                throw new Exception("Departamento não encontrado.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Departamento departamento = departamentoExistente.get();

        departamento.setNome(departamentoAtualizado.getNome());
        departamento.setIdDepart(departamento.getIdDepart());

        return departrepo.save(departamento);
    }
    
}
