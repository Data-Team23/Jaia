package com.dataTeam.jaia.jaia.controller;

import com.dataTeam.jaia.jaia.model.Cliente;
import com.dataTeam.jaia.jaia.service.Cliente.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public List<Cliente> buscartodos() {
        return service.buscarTodosClientes();
    }

    @PostMapping
    public Cliente novoCliente(@RequestBody Cliente cliente) {
        return service.novoCliente(cliente);
    }

    @DeleteMapping("/excluir/{cnpj}")
    public ResponseEntity<String> excluirClientePorCnpj(@PathVariable Integer cnpj) {
        try {
            service.excluirClientePorCnpj(cnpj);
            return ResponseEntity.ok("Cliente excluído com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao excluir o cliente");
        }
    }

    @PutMapping("/atualizar/{cnpj}")
    public ResponseEntity<?> atualizarClientePorCnpj(@PathVariable Integer cnpj,
            @RequestBody Cliente clienteAtualizado) {
        try {
            Cliente clienteAtualizadoResult = service.atualizarClientePorCnpj(cnpj, clienteAtualizado);
            return ResponseEntity.ok(clienteAtualizadoResult);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao atualizar o cliente");
        }
    }

    @GetMapping("/{cnpj}")
    public ResponseEntity<?> buscarClientePorCnpj(@PathVariable Integer cnpj) {
        try {
            Cliente clienteEncontrado = service.buscarClientePorCnpj(cnpj);
            return ResponseEntity.ok(clienteEncontrado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao buscar o cliente");
        }
    }

}
