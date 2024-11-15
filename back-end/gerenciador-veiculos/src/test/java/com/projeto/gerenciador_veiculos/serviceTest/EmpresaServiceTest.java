package com.projeto.gerenciador_veiculos.serviceTest;

import com.projeto.gerenciador_veiculos.dto.EmpresaDTO;
import com.projeto.gerenciador_veiculos.models.Empresa;
import com.projeto.gerenciador_veiculos.repositories.EmpresaRepository;
import com.projeto.gerenciador_veiculos.service.EmpresaService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class EmpresaServiceTest {

    @Mock
    private EmpresaRepository empresaRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private EmpresaService empresaService;

    @Test
    public void testEmpresaRegisterSuccess() {
        EmpresaDTO request = new EmpresaDTO("1","Empresa Teste","senha123");

        // Mockando o comportamento do repositório
        when(empresaRepository.existsByNome("Empresa Teste")).thenReturn(false);
        when(passwordEncoder.encode("senha123")).thenReturn("senhaEncriptada");
        when(empresaRepository.save(any(Empresa.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Chamando o método a ser testado
        Empresa empresaSalva = empresaService.empresaRegister(request);

        // Verificando se os valores estão corretos
        assertNotNull(empresaSalva);
        assertEquals("Empresa Teste", empresaSalva.getNome());
        assertEquals("senhaEncriptada", empresaSalva.getSenha());

        // Verificando interações com o mock
        verify(empresaRepository, times(1)).existsByNome("Empresa Teste");
        verify(empresaRepository, times(1)).save(any(Empresa.class));
    }

    @Test
    public void testEmpresaRegisterUsernameAlreadyExists() {
        EmpresaDTO request = new EmpresaDTO("1","Empresa Teste","senha123");

        // Mockando o comportamento do repositório para simular um nome já existente
        when(empresaRepository.existsByNome("Empresa Teste")).thenReturn(true);

        // Verificando se a exceção é lançada
        assertThrows(RuntimeException.class, () -> {
            empresaService.empresaRegister(request);
        });

        verify(empresaRepository, times(1)).existsByNome("Empresa Teste");
        verify(empresaRepository, times(0)).save(any(Empresa.class));
    }

    @Test
    public void testVerificarSenhaSuccess() {
        String senhaDoFront = "senha123";
        Empresa empresaMock = new Empresa("1", "Empresa Teste", "senhaEncriptada");

        // Mockando o comportamento do repositório
        when(empresaRepository.existsByNome("Empresa Teste")).thenReturn(true);
        when(empresaRepository.findByNome("Empresa Teste")).thenReturn(empresaMock);
        when(passwordEncoder.matches(senhaDoFront, "senhaEncriptada")).thenReturn(true);

        // Chamando o método verificarSenha
        boolean resultado = empresaService.verificarSenha("Empresa Teste", senhaDoFront);

        // Verificando o resultado
        assertTrue(resultado);

        // Verificando interações com os mocks
        verify(empresaRepository, times(1)).existsByNome("Empresa Teste");
        verify(empresaRepository, times(1)).findByNome("Empresa Teste");
        verify(passwordEncoder, times(1)).matches(senhaDoFront, "senhaEncriptada");
    }

    @Test
    public void testVerificarSenhaEmpresaNaoEncontrada() {
        String senhaDoFront = "senha123";

        // Mockando o comportamento do repositório para simular empresa não encontrada
        when(empresaRepository.existsByNome("Empresa Teste")).thenReturn(false);

        // Verificando se a exceção é lançada
        assertThrows(RuntimeException.class, () -> {
            empresaService.verificarSenha("Empresa Teste", senhaDoFront);
        });

        // Verificando interações com os mocks
        verify(empresaRepository, times(1)).existsByNome("Empresa Teste");
        verify(empresaRepository, times(0)).findByNome(anyString());
        verify(passwordEncoder, times(0)).matches(anyString(), anyString());
    }
}
