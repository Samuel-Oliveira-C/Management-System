package com.projeto.gerenciador_veiculos.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projeto.gerenciador_veiculos.models.Empresa;
import com.projeto.gerenciador_veiculos.repositories.EmpresaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;


@Service
public class EmpresaService {
    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Empresa empresaRegister(EmpresaRegisterRequest request){
        if (request.getNome() == null || request.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if (request.getSenha() == null || request.getSenha().trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        if (empresaRepository.existsByNome(request.getNome()) != false) {
            throw new RuntimeException("Username already exists");
        }

        Empresa newEmpresa = new Empresa(null, null, null);
        newEmpresa.setId(request.getId());
        newEmpresa.setNome(request.getNome());
        newEmpresa.setSenha(passwordEncoder.encode(request.getSenha()));

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
