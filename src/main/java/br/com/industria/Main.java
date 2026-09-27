package br.com.industria;

import br.com.industria.model.Funcionario;
import br.com.industria.service.FuncionarioService;
import br.com.industria.util.Formatador;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static br.com.industria.service.FuncionarioService.removerFuncionario;
import static br.com.industria.service.FuncionarioService.imprimirFuncionarios;


public class Main {

    public static void main(String[] args) {

        List<Funcionario> funcionarios = new ArrayList<>();

        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

        removerFuncionario(funcionarios, "João");
        System.out.println("---------------------------------------------------------------");
        FuncionarioService service = new FuncionarioService(funcionarios);
        imprimirFuncionarios(funcionarios);

        service.aumentarSalario(funcionarios, new BigDecimal("10") );

        Map<String, List<Funcionario>> grupos = service.agruparPorFuncao();

        for (String funcao : grupos.keySet()) {
            System.out.println("Função: " + funcao);
            for (Funcionario funcionario : grupos.get(funcao)) {
                System.out.println("  - " + funcionario.getNome());
            }
            System.out.println();
        }


        System.out.println("-----------------------------------------");

        List<Funcionario> aniversariantes = service.aniversariantes(Month.OCTOBER, Month.DECEMBER);
        System.out.println("Aniversariantes de Outubro e Dezembro:");
        imprimirFuncionarios(aniversariantes);

        System.out.println("---------------------------------------------------------------");
        service.aniversatiante(funcionarios, 11);
        System.out.println("---------------------------------------------------------------");

        Funcionario maisVelho = service.funcionarioMaisVelho();
        System.out.println("---------------------------------------------------------------");

        imprimirFuncionarios(List.of(maisVelho));
        System.out.println("---------------------------------------------------------------");

        List<Funcionario> ordemAlfabetica = service.ordenarPorNome();
        imprimirFuncionarios(ordemAlfabetica);

        System.out.println("---------------------------------------------------------------");

        BigDecimal SalarioTotal = service.totalSalarios();
        System.out.println("---------------------------------------------------------------");
        System.out.println("Total de Salarios de Funcionarios: R$ " + Formatador.formatarSalario(SalarioTotal));

        System.out.println("---------------------------------------------------------------");
        service.imprimirSalarioMinimoFuncionarios();

        //TESTE UNITÁRIO FEITO
    }
}
