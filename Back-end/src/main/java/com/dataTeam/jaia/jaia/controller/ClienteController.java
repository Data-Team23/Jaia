package com.dataTeam.jaia.jaia.controller;


import com.dataTeam.jaia.jaia.model.Cliente;
import com.dataTeam.jaia.jaia.service.Cliente.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cliente")
@CrossOrigin(origins = "http://localhost:5173")
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

    @DeleteMapping("/por-cnpj/{cnpj}")
    public ResponseEntity<String> apagarClientePorCnpj(@PathVariable("cnpj") Integer cnpj) {
        try {
            service.apagarClientePorCnpj(cnpj);
            return ResponseEntity.ok("Cliente excluído com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao excluir o cliente");
        }
    }

    @GetMapping("/por-cnpj/{cnpj}")
    public ResponseEntity<List<Cliente>> buscarClientesPorCnpj(@PathVariable("cnpj") Integer cnpj) {
        List<Cliente> clientes = service.buscarPorCnpj(cnpj);
        if (!clientes.isEmpty()) {
            return ResponseEntity.ok(clientes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable("id") Long id, @RequestBody Cliente clienteAtualizado) {
    Cliente clienteExistente = service.buscarPorId(id);
    if (clienteExistente == null) {
        return ResponseEntity.notFound().build();
    }

    clienteExistente.setCnpj(clienteAtualizado.getCnpj());
    clienteExistente.setTelefone(clienteAtualizado.getTelefone());
    clienteExistente.setNome(clienteAtualizado.getNome());
    clienteExistente.setEmail(clienteAtualizado.getEmail());
    clienteExistente.setEndereco(clienteAtualizado.getEndereco());

    Cliente clienteAtualizadoNoBanco = service.novoCliente(clienteExistente);
    return ResponseEntity.ok(clienteAtualizadoNoBanco);
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
