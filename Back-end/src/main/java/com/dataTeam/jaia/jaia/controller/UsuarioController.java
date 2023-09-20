//package com.dataTeam.jaia.jaia.controller;
//
//import com.dataTeam.jaia.jaia.model.Usuario;
//import com.dataTeam.jaia.jaia.repository.UsuarioRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping(value = "/user")
//@CrossOrigin
//public class UsuarioController {
//
//    @GetMapping
//    public ResponseEntity<Usuario> getAll() {
//        Usuario u = new Usuario();
//        u.setEmail("teste@email.com");
//        u.setNome("Tobias Teste");
//        u.setId(123L);
//        u.setAcessos("Acesso 1");
//        return new ResponseEntity<>(u, HttpStatus.OK);
//    }
//}
