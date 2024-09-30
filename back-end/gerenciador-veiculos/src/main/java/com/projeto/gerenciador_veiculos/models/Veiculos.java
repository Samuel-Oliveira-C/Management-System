package com.projeto.gerenciador_veiculos.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

/** 
 * Essa Classe é Para Representar os veículos que a Empresa Gerencia
 */
@Table(name = "Veiculos")
@Entity
public class Veiculos {
    @Id
    @Column(nullable = false, unique = true)
    private String id;
    
    @Column(nullable = false)
    private String marca;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false)
    private boolean manutencao;
    
    @Column(nullable = false)
    private boolean multado;
    
    @Column(nullable = true)
    private Float valorMulta;
    
    @ManyToOne
    private Empresa empresa;
    
    // Construtor padrão para o JPA
    public Veiculos() {}

    // Construtor com argumentos
    public Veiculos(String id, String marca, String nome, boolean manutencao, boolean multado, Float valorMulta, Empresa empresa) {
        this.id = id;
        this.marca = marca;
        this.nome = nome;
        this.manutencao = manutencao;
        this.multado = multado;
        this.valorMulta = valorMulta;
        this.empresa = empresa;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isManutencao() {
        return manutencao;
    }

    public void setManutencao(boolean manutencao) {
        this.manutencao = manutencao;
    }

    public boolean isMultado() {
        return multado;
    }

    public void setMultado(boolean multado) {
        this.multado = multado;
    }

    public Float getValorMulta() {
        return valorMulta;
    }

    public void setValorMulta(Float valorMulta) {
        this.valorMulta = valorMulta;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
