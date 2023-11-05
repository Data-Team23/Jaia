package com.dataTeam.jaia.jaia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dataTeam.jaia.jaia.model.Checklist;
import com.dataTeam.jaia.jaia.model.Cliente;
import com.dataTeam.jaia.jaia.model.Funcionario;
import com.dataTeam.jaia.jaia.model.OrdemServico;
import com.dataTeam.jaia.jaia.model.Requisicao;
import com.dataTeam.jaia.jaia.service.Email.EmailService;

import jakarta.mail.MessagingException;

@RestController
@CrossOrigin
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/enviar")
    public ResponseEntity<String> enviarEmail(@RequestBody Cliente cliente) {
        try {
            String assunto = "Predial - Seja bem vindo(a) | Acesse suas Credenciais ";
            emailService.enviarEmailCliente(cliente, assunto);
            return ResponseEntity.ok("Email enviado com sucesso.");
        } catch (MessagingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Não foi possível enviar o email, tente novamente.");
        }
    }

    @PostMapping("/criar")
    public ResponseEntity<String> enviarOrdemServico(@RequestBody OrdemServico ordemServico, Requisicao requisicao, Cliente cliente, Funcionario funcionario, Checklist checklist){
            try{
                String assunto = "Predial - Ordem de Serviço Criada";
                emailService.enviarOrdemServico(ordemServico , requisicao, cliente, funcionario, checklist, assunto);
                return ResponseEntity.ok("Email enviado com sucesso.");
            } catch (MessagingException e){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Não foi possível enviar o email, tente novamente.");
            }
    }




}