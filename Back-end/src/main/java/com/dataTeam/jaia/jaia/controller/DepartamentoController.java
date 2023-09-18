package com.dataTeam.jaia.jaia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dataTeam.jaia.jaia.model.Departamento;
import com.dataTeam.jaia.jaia.service.IDepartamentoService;

@RestController
@RequestMapping(value = "/departamentos")
@CrossOrigin
public class DepartamentoController {

    @Autowired
    private IDepartamentoService service;

    //add departamento
    @PostMapping
    public Departamento novoDepartamento(@RequestBody Departamento departamento) {
        return service.novoDepartamento(departamento);
    }


    //delete departamento
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartamento(@PathVariable Long id) {
    // Chama o serviço para excluir o departamento pelo ID
    boolean delete = service.deleteDepartamento(id);
    
    if (delete) {
        return ResponseEntity.ok("Departamento com ID " + id + " foi excluído com sucesso.");
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Departamento com ID " + id + " não encontrado.");
    }
}


}
