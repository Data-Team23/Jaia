package com.dataTeam.jaia.jaia.service.Email;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.dataTeam.jaia.jaia.model.Cliente;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

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
    }

    public String getContentMailCertificate(Cliente cliente, String senhaGerada) {
        String nome = cliente.getNome();
        String cnpj = cliente.getCnpj();
        return "<p>Olá, " + nome + "! Bem-vindo(a) ao Predial!</p>" +
                "<p>&nbsp;</p>" +
                "<p>Seu Login com suas credenciais está disponível abaixo:<br /></p>" +
                "<p>CPF: " + cnpj + "</p>" +
                "<p>Senha: " + senhaGerada + "</p>";
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
}
