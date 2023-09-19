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

    // Adicionar departamento
    @PostMapping("")
    public ResponseEntity<Departamento> novoDepartamento(@RequestBody Departamento departamento) {
        try {
            // Chame o serviço para criar um novo departamento
            ResponseEntity<Departamento> resultado = service.novoDepartamento(departamento);

            // Verifique se o serviço retornou com sucesso
            if (resultado.getStatusCode() == HttpStatus.CREATED) {
                // Retorne um ResponseEntity com o departamento criado e status HTTP 201 CREATED
                return ResponseEntity.status(HttpStatus.CREATED).body(resultado.getBody());
            } else {
                // Se o serviço não retornou um status CREATED, retorne um ResponseEntity com status HTTP 500 INTERNAL_SERVER_ERROR
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } catch (IllegalArgumentException e) {
            // Se os dados do departamento forem inválidos, retorne um ResponseEntity com status HTTP 400 BAD_REQUEST
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            // Se ocorrer outro erro, retorne um ResponseEntity com status HTTP 500 INTERNAL_SERVER_ERROR
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartamento(@PathVariable Long id) {
        ResponseEntity<String> response;
        try {
            ResponseEntity<String> delete = service.deleteDepartamento(id);

        if (delete.getStatusCode() == HttpStatus.OK) {
            response = ResponseEntity.ok("Departamento com ID " + id + " foi excluído com sucesso.");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Departamento com ID " + id + " não encontrado.");
        }
    } catch (IllegalArgumentException e) {
        // Se os dados do departamento forem inválidos, retorne um ResponseEntity com status HTTP 400 BAD_REQUEST
        response = ResponseEntity.badRequest().build();
    } catch (Exception e) {
        // Se ocorrer outro erro, retorne um ResponseEntity com status HTTP 500 INTERNAL_SERVER_ERROR
        response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    return response;
}



    
}




