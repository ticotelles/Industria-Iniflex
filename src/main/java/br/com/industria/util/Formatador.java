package br.com.industria.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Formatador {


    public static String formatarData(LocalDate data) {
        DateTimeFormatter formato =
                DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return data.format(formato);
    }

    public static String formatarSalario(BigDecimal salario) {
        NumberFormat formato =
                NumberFormat.getNumberInstance(new Locale("pt", "BR"));

        formato.setMinimumFractionDigits(2);
        formato.setMaximumFractionDigits(2);

        return formato.format(salario);
    }
}
