package com.projeto.gerenciador_veiculos;

//import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
import com.projeto.gerenciador_veiculos.models.Empresa;
import com.projeto.gerenciador_veiculos.repositories.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)    //depois colocar um banco em memoria para testes
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
}

