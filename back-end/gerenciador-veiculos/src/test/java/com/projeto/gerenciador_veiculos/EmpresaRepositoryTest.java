package com.projeto.gerenciador_veiculos;

//import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
import com.projeto.gerenciador_veiculos.models.Empresa;
import com.projeto.gerenciador_veiculos.repositories.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

// mvn test -Dspring.profiles.active=test utilize esse comando para rodar com perfil de test do application-test.properties

/*** Esse Teste Foi feito Para Testar a Entidade Empresa e o Repositorio dela.
 * @Funcionalidades Testadas:
 * @1 Salvar
 * @2 Deletar
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)  
@ActiveProfiles("test")
public class EmpresaRepositoryTest {
    @Autowired
    private EmpresaRepository empresaRepository;

    @Test
    public void testSalvarEmpresa() {
        // Criar nova empresa
        Empresa empresa = new Empresa("1", "Empresa Teste", "12345678901234");

        // Salvar empresa no repositório
        empresaRepository.save(empresa);

        // Buscar a empresa recém-salva pelo ID
        Empresa empresaEncontrada = empresaRepository.findById(empresa.getId())
            .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));;

        // Assegurar que a empresa foi encontrada e que os valores estão corretos
        assertThat(empresaEncontrada).isNotNull();
        assertThat(empresaEncontrada.getId()).isEqualTo("1");
        assertThat(empresaEncontrada.getNome()).isEqualTo("Empresa Teste");
        assertThat(empresaEncontrada.getSenha()).isEqualTo("12345678901234");
        
    }

    @Test 
    public void deletarEmpresa(){
        // Criar empresa
        Empresa empresa = new Empresa("1", "Empresa Teste", "12345678901234");

        // Salvar empresa no repositório
        empresaRepository.save(empresa);

        //Deletando a Empresa
        empresaRepository.deleteById(empresa.getId());

        assertThat(empresaRepository.findById(empresa.getId())).isEmpty();

    }

    @Test
    public void verificarEmpresaVerdadeiro(){
        // Criar empresa
        Empresa empresa = new Empresa("1", "Empresa Teste", "12345678901234");

        // Salvar empresa no repositório
        empresaRepository.save(empresa);

        //verificação
        boolean valorTrue = empresaRepository.existsByNome(empresa.getNome());

        //confirmação dos valores
        assertThat(valorTrue).isTrue();
    }

    @Test
    public void verificarEmpresaFalso(){
        //verificar
        boolean valorFalse = empresaRepository.existsByNome("aqui tem que dar resultado False");

        //confirmação dos valores
        assertThat(valorFalse).isFalse();
    }
}