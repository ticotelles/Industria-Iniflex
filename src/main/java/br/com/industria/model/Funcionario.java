package br.com.industria.model;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Funcionario extends  Pessoa{


    private BigDecimal salario;

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    private String funcao;

    public BigDecimal getSalario() {
        return salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public Funcionario(String nome, LocalDate dataNascimento,BigDecimal salario, String funcao) {

        super(nome, dataNascimento);

        this.salario = salario;
        this.funcao = funcao;

    }




}

