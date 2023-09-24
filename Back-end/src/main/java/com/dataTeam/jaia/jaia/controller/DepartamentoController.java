package com.dataTeam.jaia.jaia.controller;


import com.dataTeam.jaia.jaia.model.Departamento;
import com.dataTeam.jaia.jaia.service.Departamento.IDepartamentoSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/departamentos")
@CrossOrigin
public class DepartamentoController {


    @Autowired
    private IDepartamentoSerivce service;


    // Adicionar departamento   //Foi
    @PostMapping(value = "/novo")
    public Departamento novoDepartamento(@RequestBody Departamento departamento){
        return service.novoDepartamento(departamento);
    }

    // Deletar departamento por ID
    @GetMapping("/pegar/{id}")
    public Departamento deleteDepartamento(@PathVariable("id") Long Id){
        return service.deleteDepartamento(Id);
    }

    // Listar todos os departamentos  //foi
    @GetMapping(value = "/todos")
    public List<Departamento> buscartodos(){
        return service.buscarTodosDepartamentos();
    }


    // Atualizar departamento por ID
    @PutMapping("/{id}")
    public Departamento atualizarDepartamento(@PathVariable("id") Long Id, @RequestBody Departamento atualizarDepartamento) {
        return service.atualizarDepartamento(Id, atualizarDepartamento);
    }
}
