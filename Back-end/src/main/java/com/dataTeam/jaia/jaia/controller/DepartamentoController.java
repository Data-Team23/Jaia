package com.dataTeam.jaia.jaia.controller;


import com.dataTeam.jaia.jaia.model.Departamento;
import com.dataTeam.jaia.jaia.model.DepartamentoComSupervisor;
import com.dataTeam.jaia.jaia.model.Funcionario;
import com.dataTeam.jaia.jaia.service.Departamento.IDepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/departamentos")
@CrossOrigin
public class DepartamentoController {

    @Autowired
    private IDepartamentoService service;

    @GetMapping(value = "/todos")
    public List<Departamento> buscartodos(){
        return service.buscarTodosDepartamentos();
    }

     @GetMapping("/comSupervisores")
    public List<DepartamentoComSupervisor> buscarDepartamentosComSupervisores() {
        List<Departamento> departamentos = service.buscarTodosDepartamentos();

        List<Departamento> departamentosComSupervisores = departamentos.stream()
                .filter(departamento -> departamento.getFuncionarios().stream()
                        .anyMatch(funcionario -> funcionario.getSupervisor() == 2))
                .collect(Collectors.toList());

        List<DepartamentoComSupervisor> departamentosComSupervisoresDTO = departamentosComSupervisores.stream()
                .map(departamento -> new DepartamentoComSupervisor(
                        departamento.getCodDepart(),
                        departamento.getNome(),
                        departamento.getFuncionarios().stream()
                                .filter(funcionario -> funcionario.getSupervisor() == 2)
                                .map(Funcionario::getNome)
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());

        return departamentosComSupervisoresDTO;
    }

}
