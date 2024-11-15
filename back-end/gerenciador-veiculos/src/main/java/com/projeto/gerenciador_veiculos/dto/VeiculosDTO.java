package com.projeto.gerenciador_veiculos.dto;

public record VeiculosDTO(String id,String nome,String marca,
    boolean manutencao,boolean multado, float valorMulta) {
    
}
