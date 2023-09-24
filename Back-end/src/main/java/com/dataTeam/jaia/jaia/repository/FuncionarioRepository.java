package com.dataTeam.jaia.jaia.repository;

import com.dataTeam.jaia.jaia.model.Cliente;
import com.dataTeam.jaia.jaia.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    public Optional<Funcionario> findByNome(String nome);

    @Query("select u from Funcionario u where u.nome = ?1")
    public Optional<Funcionario> buscarPeloNome(String nome);

    public List<Funcionario> findByNomeContainsIgnoreCase(String nome);

    @Query("select u from Funcionario u where u.nome like %?1%")
    public List<Funcionario> buscarPeloNomeParecido(String nome);

}
