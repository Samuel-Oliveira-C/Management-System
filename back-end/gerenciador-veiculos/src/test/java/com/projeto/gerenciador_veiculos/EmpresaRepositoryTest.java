package com.projeto.gerenciador_veiculos;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.seuprojeto.models.Empresa;
import com.seuprojeto.repositories.EmpresaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class EmpresaRepositoryTest {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Test
    public void testSalvarEmpresa() {
        Empresa empresa = new Empresa("1", "Empresa Teste", "12345678901234");
        //Criar o Repository da empresa antes de fazer o teste;

        assertThat(empresaEncontrada).isEqualTo("1");
        assertThat(empresaEncontrada.getNome()).isEqualTo("Empresa Teste");
        assertThat(empresaEncontrada.getSenha()).isEqualTo("12345678901234");
    }
}