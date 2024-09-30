package main.java.com.projeto.gerenciador_veiculos.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

/**
 * Essa Classe representa o Login das Empresas
 */
@Entity
@Table(name = "Empresa")
public class Empresa {
    @Id
    @Column(nullable = false, unique = true)
    private String id;  // Usando UUIDv7 como identificador

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private String senha;

    // Construtor padrão (necessário para JPA)
    protected Empresa() {}

    // Construtor
    public Empresa(String id, String nome, String senha) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
    }
    
    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}