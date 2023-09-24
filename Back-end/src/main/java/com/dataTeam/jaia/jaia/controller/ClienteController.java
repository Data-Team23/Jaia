package com.dataTeam.jaia.jaia.controller;


import com.dataTeam.jaia.jaia.model.Cliente;
import com.dataTeam.jaia.jaia.service.Cliente.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/email/{id}")
    public ResponseEntity<String> buscarEmailPorId(@PathVariable("id") Long id) {
        Cliente cliente = service.buscarPorId(id);

        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }

        String email = cliente.getEmail();
        return ResponseEntity.ok(email);
    }

}
