package com.projeto.gerenciador_veiculos.repositories;

import com.projeto.gerenciador_veiculos.models.Veiculos;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculosRepository extends JpaRepository<Veiculos,String>{
    List<Veiculos> findVeiculosById (String empresaId);
}

//TODOConfigurar esse Repositorio e fazer teste e o controller