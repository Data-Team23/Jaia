package com.dataTeam.jaia.jaia.controller;


import com.dataTeam.jaia.jaia.model.Funcionario;
import com.dataTeam.jaia.jaia.service.Funcionario.IFuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/funcionario")
@CrossOrigin
public class FuncionarioController {


    @Autowired
    private IFuncionarioService service;

    @GetMapping
    public List<Funcionario> buscartodos(){
        return service.buscarTodosFuncionario();
    }

    @PostMapping
    public Funcionario novoFuncionario(@RequestBody Funcionario funcionario){
        return service.novoFuncionario(funcionario);
    }

    @GetMapping(value = "/{id}")
    public Funcionario buscarPorId(@PathVariable("id") Long id){
        return service.buscarPorId(id);
    }



}
