package com.dataTeam.jaia.jaia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dataTeam.jaia.jaia.model.Departamento;
import com.dataTeam.jaia.jaia.repository.DepartamentoRepository;

import jakarta.transaction.Transactional;

@Service
public class DepartamentoService implements IDepartamentoService {
    
    @Autowired
    private DepartamentoRepository departRepo;

    @Transactional
    @Override
    public Departamento novoDepartamento(Departamento departamento){
        if(departamento == null||
                departamento.getCod_depart() == null ||
                departamento.getNome_depart() == null ||
                departamento.getNome_depart().isBlank())
            throw new IllegalArgumentException("Departamento com atributos inválidos");

    Departamento departamentoNovo = departRepo.save(departamento);
    return departamentoNovo;
}

    @Override
    public  ResponseEntity<String> deleteDepartamento(Long id) {
        Optional<Departamento> departamentoOptional = departRepo.findById(id);
        
        if (departamentoOptional.isPresent()) {
            // O departamento foi encontrado, podemos excluí-lo
            departRepo.deleteById(id);
            return ResponseEntity.ok("Departamento com ID " + id + " foi excluído com sucesso.");
        } else {
            // O departamento não foi encontrado
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Departamento com ID " + id + " não encontrado.");
        }
    }

    public List<Departamento> listarDepartamento(){
        return departRepo.findAll();
    }
    
}
