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

/*** Esse Teste Foi feito Para Testar a Entidade Veiculo e o Repositorio dela.
 * @Funcionalidades Testadas:
 * @1 Salvar
 * @2 Deletar
 * @3 Alterar
 */
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

    @Test
    public void deletarVeiculo(){
        Empresa empresa = new Empresa("1", "Empresa Teste", "12345678901234");
        Veiculos veiculo = new Veiculos("ddas", "Seila", "Uni", false, false, 10.23f, empresa);

        veiculosRepository.deleteById(veiculo.getId());

        assertThat(veiculosRepository.findById(veiculo.getId())).isEmpty();
    }
    
    @Test
    public void alterarCampos(){
        Empresa empresa = new Empresa("1", "Empresa Teste", "12345678901234");
        Veiculos veiculo = new Veiculos("ddas", "Seila", "Uni", false, false, 10.23f, empresa);
        
        //Foi salvo a entidade
        empresaRepository.save(empresa);
        veiculosRepository.save(veiculo);

            // Alterar os campos do veículo
        veiculo.setMarca("Nova Marca");
        veiculo.setNome("Novo Nome");
        veiculo.setManutencao(true);
        veiculo.setMultado(true);
        veiculo.setValorMulta(20.50f);

        // Salvar as alterações
        veiculosRepository.save(veiculo);

            // Buscar o veículo atualizado do banco de dados
        Veiculos veiculoAtualizado = veiculosRepository.findById("ddas")
            .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

        // Verificar se todos os campos foram alterados com sucesso
        assertThat(veiculoAtualizado.getMarca()).isEqualTo("Nova Marca");
        assertThat(veiculoAtualizado.getNome()).isEqualTo("Novo Nome");
        assertThat(veiculoAtualizado.isManutencao()).isTrue();
        assertThat(veiculoAtualizado.isMultado()).isTrue();
        assertThat(veiculoAtualizado.getValorMulta()).isEqualTo(20.50f);

        // Verificar se os campos que não deveriam mudar permaneceram inalterados
        assertThat(veiculoAtualizado.getId()).isEqualTo("ddas");
        assertThat(veiculoAtualizado.getEmpresa().getId()).isEqualTo("1");
    }
}
