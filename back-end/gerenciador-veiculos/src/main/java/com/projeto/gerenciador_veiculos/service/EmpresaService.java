package com.projeto.gerenciador_veiculos.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.gerenciador_veiculos.dto.EmpresaDTO;
import com.projeto.gerenciador_veiculos.models.Empresa;
import com.projeto.gerenciador_veiculos.repositories.EmpresaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;


@Service
public class EmpresaService {
    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Empresa empresaRegister(EmpresaDTO request){
        if (request.nome() == null || request.nome().trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if (request.senha() == null || request.senha().trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        if (empresaRepository.existsByNome(request.nome()) != false) {
            throw new RuntimeException("Username already exists");
        }

        Empresa newEmpresa = new Empresa(null, null, null);
        newEmpresa.setId(request.id());
        newEmpresa.setNome(request.nome());
        newEmpresa.setSenha(passwordEncoder.encode(request.senha()));

        return empresaRepository.save(newEmpresa);
    }

    public boolean verificarSenha(String nomeEmpresa, String senhaDoFront) {
        // Verifica se a empresa existe no banco de dados usando existsByNome
        if (!empresaRepository.existsByNome(nomeEmpresa)) {
            throw new RuntimeException("Empresa não encontrada");
        }
    
        // Recupera a empresa a partir do nome
        Empresa empresa = empresaRepository.findByNome(nomeEmpresa);
    
        // Compara a senha fornecida pelo front com a senha criptografada no banco
        return passwordEncoder.matches(senhaDoFront, empresa.getSenha());
    }
    //public boolean verificarNome(String nomeEmpresa){}
    
    
}

/** //TODO 
 * REFATORAR O VERIFICAR SENHA 
 *  CRIAR o metodo de verificar o nome
 *  Refatorar o Teste do service
 *  Fazer o controller
 * */ 
