package com.projeto.gerenciador_veiculos.repositories;

import com.projeto.gerenciador_veiculos.models.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa,String>{
    @Query("SELECT COUNT(e) > 0 FROM Empresa e WHERE e.nome = :nomeEmpresa")
    boolean existsByNome(String nomeEmpresa);
}
