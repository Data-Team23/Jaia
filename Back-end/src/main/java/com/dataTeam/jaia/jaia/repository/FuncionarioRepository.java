package com.dataTeam.jaia.jaia.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

import com.dataTeam.jaia.jaia.model.Cliente;
import com.dataTeam.jaia.jaia.model.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    public Optional<Funcionario> findByNome(String nome);

    @Query("select u from Funcionario u where u.nome = ?1")
    public Optional<Funcionario> buscarPeloNome(String nome);

    public List<Funcionario> findByNomeContainsIgnoreCase(String nome);

    @Query("select u from Funcionario u where u.nome like %?1%")
    public List<Funcionario> buscarPeloNomeParecido(String nome);

    @Query("SELECT f FROM Funcionario f LEFT JOIN FETCH f.departamento WHERE f.id = :id")
    Optional<Funcionario> findByIdWithDepartamento(Long id);

    @Query("SELECT f FROM Funcionario f LEFT JOIN FETCH f.departamento")
    public List<Funcionario> findAllWithDepartamento();

    @Modifying
    @Transactional
    @Query("DELETE FROM Funcionario f WHERE f.id = ?1")
    void deleteByIdWithCascade(Long id);

    Funcionario findByCpfAndSenha(String cpf, String senha);

}
