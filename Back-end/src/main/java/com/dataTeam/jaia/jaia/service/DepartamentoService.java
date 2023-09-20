package com.dataTeam.jaia.jaia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dataTeam.jaia.jaia.model.Departamento;
import com.dataTeam.jaia.jaia.repository.DepartamentoRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class DepartamentoService implements IDepartamentoService {
    
    @Autowired
    private DepartamentoRepository departRepo;

    //add
    @Transactional
    public ResponseEntity<Departamento> novoDepartamento(Departamento departamento) {
        try {
            if (departamento == null ||
                departamento.getCod_depart() == null ||
                departamento.getNome_depart() == null ||
                departamento.getNome_depart().isBlank()) {
                throw new IllegalArgumentException("Departamento com atributos inválidos");
            }

            Departamento novoDepartamento = departRepo.save(departamento);
    
            return ResponseEntity.ok(novoDepartamento);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    //delete
    @Override
    public  ResponseEntity<String> deleteDepartamento(Long cod_depart) {
        Optional<Departamento> departamentoOptional = departRepo.findByCod_depart(cod_depart);
        
        if (departamentoOptional.isPresent()) {
            // O departamento foi encontrado, podemos excluí-lo
            departRepo.deleteByCod_depart(cod_depart);
            return ResponseEntity.ok("Departamento com ID " + cod_depart + " foi excluído com sucesso.");
        } else {
            // O departamento não foi encontrado
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Departamento com ID " + cod_depart + " não encontrado.");
        }
    }



        //list
        public List<Departamento> listarDepartamento(){
            return departRepo.findAll();
        }



        //atualizar
        @Override
        @Transactional
        public Departamento atualizarDepartamento(Long id, Departamento atualizarDepartamento) {
            Optional<Departamento> existingDepartmentOptional = departRepo.findByCod_depart(id);

            if (existingDepartmentOptional.isPresent()) {
                Departamento existingDepartment = existingDepartmentOptional.get();
                existingDepartment.setNome_depart(atualizarDepartamento.getNome_depart());

                return departRepo.save(existingDepartment);
            } else {
                throw new EntityNotFoundException("O departamento encontrado não foi encontrado: " + id);
            }
        }
    }
    
    

