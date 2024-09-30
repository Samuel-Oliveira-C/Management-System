package com.projeto.gerenciador_veiculos.repositories;

import com.projeto.gerenciador_veiculos.models.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa,String>{
    //TODOAQUI DEVE fazer o crud 
}
