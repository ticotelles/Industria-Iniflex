package br.com.industria.service;

import br.com.industria.model.Funcionario;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FuncionarioServiceTest {

    private FuncionarioService funcionarioService;
    private List<Funcionario> funcionarios;

    @Before
    public void setUp() {
        funcionarios = new ArrayList<>();
        funcionarioService = new FuncionarioService(funcionarios);
    }

    @Test
    public void testTotalSalariosComUmFuncionario() {
        funcionarios.add(new Funcionario(
                "João Silva",
                LocalDate.of(1990, 5, 15),
                new BigDecimal("2000.00"),
                "Desenvolvedor"
        ));

        BigDecimal totalEsperado = new BigDecimal("2000.00");
        BigDecimal totalObtido = funcionarioService.totalSalarios();

        assertEquals(totalEsperado, totalObtido);
    }

    @Test
    public void testTotalSalariosComVariosFuncionarios() {
        funcionarios.add(new Funcionario(
                "João Silva",
                LocalDate.of(1990, 5, 15),
                new BigDecimal("2000.00"),
                "Desenvolvedor"
        ));

        funcionarios.add(new Funcionario(
                "Maria Santos",
                LocalDate.of(1988, 3, 22),
                new BigDecimal("3000.00"),
                "Analista"
        ));

        funcionarios.add(new Funcionario(
                "Pedro Oliveira",
                LocalDate.of(1992, 8, 10),
                new BigDecimal("2500.00"),
                "Designer"
        ));

        BigDecimal totalEsperado = new BigDecimal("7500.00");
        BigDecimal totalObtido = funcionarioService.totalSalarios();

        assertEquals(totalEsperado, totalObtido);
    }

    @Test
    public void testTotalSalariosComListaVazia() {
        BigDecimal totalEsperado = BigDecimal.ZERO;
        BigDecimal totalObtido = funcionarioService.totalSalarios();

        assertEquals(totalEsperado, totalObtido);
    }

    @Test
    public void testTotalSalariosComValoresAltos() {
        funcionarios.add(new Funcionario(
                "Carlos Mendes",
                LocalDate.of(1985, 1, 5),
                new BigDecimal("15000.00"),
                "Gerente"
        ));

        funcionarios.add(new Funcionario(
                "Ana Costa",
                LocalDate.of(1995, 11, 20),
                new BigDecimal("10000.00"),
                "Coordenador"
        ));

        BigDecimal totalEsperado = new BigDecimal("25000.00");
        BigDecimal totalObtido = funcionarioService.totalSalarios();

        assertEquals(totalEsperado, totalObtido);
    }

    @Test
    public void testTotalSalariosNaoEhNulo() {
        funcionarios.add(new Funcionario(
                "João Silva",
                LocalDate.of(1990, 5, 15),
                new BigDecimal("2000.00"),
                "Desenvolvedor"
        ));

        BigDecimal resultado = funcionarioService.totalSalarios();

        assertNotNull("O resultado não deve ser nulo", resultado);
    }
}
