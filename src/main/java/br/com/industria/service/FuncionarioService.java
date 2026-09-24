package br.com.industria.service;

import br.com.industria.model.Funcionario;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class FuncionarioService {

    private final List<Funcionario> funcionarios;

    public FuncionarioService(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public void removerFuncionario(String nome) {
        funcionarios.removeIf(
                funcionario -> funcionario.getNome().equalsIgnoreCase(nome)
        );
    }

//    public void aumentarSalarios(BigDecimal percentual) {
//        funcionarios.forEach(
//                funcionario -> funcionario.aumentarSalario(percentual)
//        );
//    }

    public Map<String, List<Funcionario>> agruparPorFuncao() {
        return funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));
    }

    public List<Funcionario> aniversariantes(Month... meses) {
        Set<Month> mesesSet = Set.of(meses);

        return funcionarios.stream()
                .filter(funcionario ->
                        mesesSet.contains(
                                funcionario.getDataNascimento().getMonth()
                        )
                )
                .toList();
    }

    public Funcionario funcionarioMaisVelho() {
        return funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento))
                .orElseThrow();
    }

    public List<Funcionario> ordenarPorNome() {
        return funcionarios.stream()
                .sorted(Comparator.comparing(
                        Funcionario::getNome,
                        String.CASE_INSENSITIVE_ORDER
                ))
                .toList();
    }

    public BigDecimal totalSalarios() {
        return funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<Funcionario> getFuncionarios() {
        return Collections.unmodifiableList(funcionarios);
    }


    public static void imprimirFuncionarios(List<Funcionario> funcionarios) {

        DateTimeFormatter formatoData =
                DateTimeFormatter.ofPattern("dd/MM/yyyy");

        DecimalFormat formatoSalario =
                new DecimalFormat("#,##0.00");

        for (Funcionario funcionario : funcionarios) {

            System.out.println("Nome: " + funcionario.getNome());

            System.out.println(
                    "Data de nascimento: "
                            + funcionario.getDataNascimento().format(formatoData)
            );

            System.out.println(
                    "Salário: R$ "
                            + formatoSalario.format(funcionario.getSalario())
            );

            System.out.println(
                    "Função: " + funcionario.getFuncao()
            );

            System.out.println("-------------------------");
        }
    }


}
