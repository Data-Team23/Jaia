package com.dataTeam.jaia.jaia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

        // Listar todos os departamentos
    @GetMapping()
    public List<Departamento> buscarTodos(){
        return service.buscarTodosDepartamentos();
    }


    // Adicionar departamento
    @PostMapping
    public Departamento novoDepartamento(@RequestBody Departamento departamento){
        return service.novoDepartamento(departamento);
    }

    // Deletar departamento por ID
    @GetMapping("/{cod_depart}")
    public Departamento deleteDepartamento(@PathVariable("cod_depart") Long cod_depart){
        return service.deleteDepartamento(cod_depart);
    }

    // Atualizar departamento por ID
    @PutMapping("/{cod_depart}")
    public Departamento atualizarDepartamento(@PathVariable("cod_depart") Long cod_depart, @RequestBody Departamento atualizarDepartamento) {
        return service.atualizarDepartamento(cod_depart, atualizarDepartamento);
    }
}
