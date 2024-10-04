package com.projeto.gerenciador_veiculos;

import org.junit.jupiter.api.Test;
import com.projeto.gerenciador_veiculos.models.Empresa;
import com.projeto.gerenciador_veiculos.models.Veiculos;
import com.projeto.gerenciador_veiculos.repositories.EmpresaRepository;
import com.projeto.gerenciador_veiculos.repositories.VeiculosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) 
public class VeiculosRepositoryTest{
    @Autowired
    private VeiculosRepository veiculosRepository;
    @Autowired
    private EmpresaRepository empresaRepository;

    @Test
    public void testSalvarVeiculo(){
        // Criar nova empresa
        Empresa empresa = new Empresa("1", "Empresa Teste", "12345678901234");
        // Salvar empresa no repositório
        empresaRepository.save(empresa);

        //Criando o objeto Veiculo
        Veiculos veiculo = new Veiculos("ddas", "Seila", "Uni", false, false, 10.23f, empresa);
        //Salvando o objeto Veiculo
        veiculosRepository.save(veiculo);

        assertThat(veiculo).isNotNull();
        assertThat(veiculo.getId()).isEqualTo("ddas");
        assertThat(veiculo.getMarca()).isEqualTo("Seila");
        assertThat(veiculo.getEmpresa().getId()).isEqualTo("1");
        assertThat(veiculo.getValorMulta()).isEqualTo(10.23f);
        assertThat(veiculo.isManutencao()).isFalse();
        assertThat(veiculo.isMultado()).isFalse();
        assertThat(veiculo.getNome()).isEqualTo("Uni");
    }
}
