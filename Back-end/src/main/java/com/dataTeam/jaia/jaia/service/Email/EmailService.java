package com.dataTeam.jaia.jaia.service.Email;

import java.security.SecureRandom;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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



    public void enviarOrdemServico(OrdemServico ordemServico, Requisicao requisicao, Cliente cliente, Funcionario funcionario, Checklist checklist, String assunto) throws MessagingException {


        MimeMessage mail = mailSender.createMimeMessage();

        MimeMessageHelper mensagem = new MimeMessageHelper(mail, true);
        mensagem.setSubject(assunto);
        mensagem.setTo(cliente.getEmail());
        mensagem.setFrom(supportMail);

        String conteudoDoEmail = getContentMailCertificate(ordemServico , requisicao, cliente, funcionario, checklist );

        mensagem.setText(conteudoDoEmail, true);

        mailSender.send(mail);


    }

    public String getContentMailCertificate(OrdemServico ordemServico, Requisicao requisicao, Cliente cliente, Funcionario funcionario, Checklist checklist) {
    String nomecli = cliente.getNome();    
    String nomeOrdem = ordemServico.getNome_ordem();
    String statusOrdem = ordemServico.getStatus_ordem();
    LocalDateTime dataAbertura = requisicao.getData_abertura();
    String descricao = requisicao.getDescricao();
    String cnpj = cliente.getCnpj();
    String inspecao = ordemServico.getTipo_inspecao();
    Funcionario responsasvel = funcionario.getSupervisor();
    String checklistname = checklist.getNome();

        // Customize the email content based on the data from OrdemServico
        String content = "<p>Olá, " + nomecli + "! Bem-vindo(a) ao Predial!</p>" +
                    "<p>&nbsp;</p>" +
                    "<p>Sua Ordem de Seriço está disponível abaixo:<br /></p>" +
                    "<p>Nome: " + nomeOrdem + "Status da Requisição:" +"</p>" +
                    "<p>Data da Abertura: " + dataAbertura + "Descrição:" + descricao + "</p>" +
                    "<p>CNPJ:" + cnpj + "Status da Ordem de Seriço:" + statusOrdem + "</p>"+
                    "<p>Inspeção:" + inspecao + "Responsável" + responsasvel +  "</p>" +
                    "<p>Data da Prestação do Serviço:"  + "Checklist:" + checklistname + "</p>"+
                    "<p>*Não responda este E-mail*</p>";

        return content;

    } 

}
