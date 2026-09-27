package br.com.industria.service;

import br.com.industria.model.Funcionario;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import br.com.industria.util.Formatador;

public class FuncionarioService {

    private final List<Funcionario> funcionarios;

    public FuncionarioService(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }




    public void aumentarSalario(List<Funcionario> funcionarios, BigDecimal percentual) {

        BigDecimal porcentagem = BigDecimal.ONE.add(
                percentual.divide(new BigDecimal("100"))
        );

        System.out.println("Fator: " + porcentagem);;
        for (Funcionario funcionario : funcionarios) {
            BigDecimal novoSalario = funcionario.getSalario()
                    .multiply(porcentagem);

            funcionario.setSalario(novoSalario);
        }
    }


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

    public  List<Funcionario> aniversatiante(List<Funcionario> funcionarios, int mes){
        for (Funcionario funcionario : funcionarios) {

            int mesNascimento = funcionario.getDataNascimento().getMonthValue();
            String dataFormatada = Formatador.formatarData(funcionario.getDataNascimento());
            if (mesNascimento == mes) {
                System.out.println(funcionario.getNome() +" " +  dataFormatada);
            }
        }
        return funcionarios;
    }



    public Funcionario funcionarioMaisVelho() {


        return  funcionarios.stream()
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

    public void imprimirSalarioMinimoFuncionarios() {
        BigDecimal salarioMinimo = new BigDecimal("1212.00");

        for (Funcionario funcionario : funcionarios) {
            BigDecimal quantidadeMinimosSalarios = funcionario.getSalario()
                    .divide(salarioMinimo, 2);

            System.out.println(funcionario.getNome() + ": " + quantidadeMinimosSalarios + " Salarios Mínimos");
        }
    }

    public static void imprimirFuncionarios(List<Funcionario> funcionarios) {


        for (Funcionario funcionario : funcionarios) {

            String dataFormatada = Formatador.formatarData(funcionario.getDataNascimento()) ;
            String salarioFormatado = Formatador.formatarSalario(funcionario.getSalario());

            System.out.println("Nome: " + funcionario.getNome());

            System.out.println("Data de nascimento: " + dataFormatada);

            System.out.println("Salário: R$ " + salarioFormatado);

            System.out.println("Função: " + funcionario.getFuncao());

            System.out.println("-------------------------");
        }
    }

    public static void removerFuncionario(List<Funcionario> funcionarios, String nome) {

        funcionarios.removeIf(funcionario ->
                funcionario.getNome().equalsIgnoreCase(nome)
        );
    }

}
