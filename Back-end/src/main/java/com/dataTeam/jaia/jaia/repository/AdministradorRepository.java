package com.dataTeam.jaia.jaia.repository;

import org.springframework.stereotype.Repository;
import com.dataTeam.jaia.jaia.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long>{

    Administrador findByUsuarioAndSenha(String cpf, String senha);
    
}

