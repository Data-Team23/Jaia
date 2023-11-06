package com.dataTeam.jaia.jaia.service.Email;

import java.io.IOException;
import java.security.SecureRandom;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.dataTeam.jaia.jaia.model.Checklist;
import com.dataTeam.jaia.jaia.model.Cliente;
import com.dataTeam.jaia.jaia.model.Funcionario;
import com.dataTeam.jaia.jaia.model.OrdemServico;
import com.dataTeam.jaia.jaia.model.Requisicao;
import com.dataTeam.jaia.jaia.repository.ClienteRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private ClienteRepository cliRepo;

    @Value("${support.mail}")
    private String supportMail;

    public void enviarEmailCliente(Cliente cliente, String assunto) throws MessagingException {

        String senhaGerada = generateRandomPassword(5);


        cliente.setSenha(senhaGerada);

        MimeMessage mail = mailSender.createMimeMessage();

        MimeMessageHelper mensagem = new MimeMessageHelper(mail, true);
        mensagem.setSubject(assunto);
        mensagem.setTo(cliente.getEmail());
        mensagem.setFrom(supportMail);

        String conteudoDoEmail = getContentMailCertificate(cliente, senhaGerada);

        mensagem.setText(conteudoDoEmail, true);

        mailSender.send(mail);

        cliRepo.save(cliente);

    }

    public String getContentMailCertificate(Cliente cliente, String senhaGerada) {
        String nome = cliente.getNome();
        String cnpj = cliente.getCnpj();
        return "<p>Olá, " + nome + "! Bem-vindo(a) ao Predial!</p>" +
                "<p>&nbsp;</p>" +
                "<p>Seu Login com suas credenciais está disponível abaixo:<br /></p>" +
                "<p>CPF: " + cnpj + "</p>" +
                "<p>Senha: " + senhaGerada + "</p>" +
                "<p>** Não responda este E-mail</p>";
    }

    private String generateRandomPassword(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_+=<>?";
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            password.append(characters.charAt(index));
        }

        return password.toString();
    }

    public void sendEmailWithAttachment(String recipient, String subject, String body, byte[] pdfData, String pdfFileName) throws MessagingException, IOException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(recipient);
        helper.setSubject(subject);
        helper.setText(body, true);

        InputStreamSource source = new ByteArrayResource(pdfData);
        helper.addAttachment(pdfFileName, source);

        mailSender.send(message);
    }

}