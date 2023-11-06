package com.dataTeam.jaia.jaia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.dataTeam.jaia.jaia.model.Cliente;
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

    @PostMapping("/send")
    public ResponseEntity<String> sendEmailWithAttachment(
        @RequestParam("recipient") String recipient,
        @RequestParam("subject") String subject,
        @RequestParam("body") String body,
        @RequestParam("pdfData") MultipartFile pdfData
    ) {
        try {
            byte[] pdfBytes = pdfData.getBytes();
            emailService.sendEmailWithAttachment(recipient, subject, body, pdfBytes, pdfData.getOriginalFilename());
            return ResponseEntity.ok("E-mail enviado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erro ao enviar o e-mail: " + e.getMessage());
        }
    }
}


