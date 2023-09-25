package com.dataTeam.jaia.jaia.repository;


import com.dataTeam.jaia.jaia.model.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

    public Optional<Departamento> findByNome(String nome);

    @Query("select u from Departamento u where u.nome = ?1")
    public Optional<Departamento> buscarPeloNome(String nome);

    public List<Departamento> findByNomeContainsIgnoreCase(String nome);

    @Query("select u from Departamento u where u.nome like %?1%")
    public List<Departamento> buscarPeloNomeParecido(String nome);


    List<Departamento> findAll();

    Optional<Departamento> findById(Long Id);

    Departamento save(Departamento existingDepartment);
}
