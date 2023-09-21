package com.dataTeam.jaia.jaia.controller;


import com.dataTeam.jaia.jaia.model.Cliente;
import com.dataTeam.jaia.jaia.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/cliente")
@CrossOrigin
public class ClienteController {

    @Autowired
    private IClienteService service;

    @GetMapping
    public List<Cliente> buscartodos(){
        return service.buscarTodosClientes();
    }

    @PostMapping
    public Cliente novoCliente(@RequestBody Cliente cliente){
        return service.novoCliente(cliente);
    }

    @GetMapping(value = "/{id}")
    public Cliente buscarPorId(@PathVariable("id") Long id){
        return service.buscarPorId(id);
    }

    @GetMapping("/emails")
    public List<String> buscarTodosEmails() {
        List<Cliente> clientes = service.buscarTodosClientes();

        // Use a função stream e map para extrair apenas os emails
        List<String> emails = clientes.stream()
                .map(Cliente::getEmail)
                .collect(Collectors.toList());

        return emails;
    }

}
