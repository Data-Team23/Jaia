package com.dataTeam.jaia.jaia.service.Email;

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


    public void enviarEmailCliente(String assunto, String email, String conteudo) throws MessagingException{

        MimeMessage mail = mailSender.createMimeMessage();

        MimeMessageHelper mensagems = new MimeMessageHelper(mail);
        mensagems.setAssunto(assunto); //teste
        mensagems.setEmail(email);
        mensagems.setFrom(supportMail);
        mensagems.setTo(email);

        mailSender.send(mail);

    }


    public String getContentMailCertificate(Cliente String nome, String senha, String cnpj){
        return "<p>Ol&aacute;," + nome + "! bem vindo(a) ao Predial!</p>" +
                "<p>&nbsp;</p>" +
                "<p>Seu Login est&aacute; dispon&iacute;vel abaixo:<br /><br /></p>" +
                "<p>Login:</p>" +
                "<p>CPF: </p>" + cnpj +
                "<p>Senha: </p>" + senha;
    }
    
    
}
