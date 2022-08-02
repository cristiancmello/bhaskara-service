package com.bhaskara.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

class ControladorTest {
    private final ByteArrayOutputStream saida = new ByteArrayOutputStream();
    private final ByteArrayOutputStream erro = new ByteArrayOutputStream();

    @BeforeEach
    void setSaida() {
        System.setOut(new PrintStream(saida));
        System.setErr(new PrintStream(erro));
    }

    void setEntrada(String comando, String ...argumentos) {
        StringBuilder inputString = new StringBuilder(comando);
        for (String argumento : argumentos) {
            inputString.append("\n").append(argumento);
        }

        var entrada = new ByteArrayInputStream(inputString.toString().getBytes());
        System.setIn(entrada);
    }

    @AfterEach
    void restoreEntradaSaida() {
        System.setIn(System.in);
        System.setOut(System.out);
        System.setErr(System.err);
    }

    @Test
    void given_ComandoCalcBhaskara_when_ComandoExecutado_then_CalcularRaizes() {
        String comando = "calcbhaskara";
        String inputA = "1", inputB = "-2", inputC = "-3";
        setEntrada(comando, inputA, inputB, inputC);

        Controlador.main(new String[0]);

        assertThat(saida.toString()).contains("3.0", "-1.0");
    }

    @Test
    void given_ComandoCalcBhaskara_when_ComandoExecutado_and_ParametroInvalido_then_ExibirErro() {
        String comando = "calcbhaskara";
        String inputA = "g", inputB = "-2", inputC = "-3";
        setEntrada(comando, inputA, inputB, inputC);

        Controlador.main(new String[0]);

        assertThat(erro.toString()).contains("Por favor insira numeros reais!");
    }

    @Test
    void given_ComandoCalcBhaskara_when_ComandoExecutado_and_ParametroAIgualZero_then_ExibirErro() {
        String comando = "calcbhaskara";
        String inputA = "0", inputB = "-2", inputC = "-3";
        setEntrada(comando, inputA, inputB, inputC);

        Controlador.main(new String[0]);

        assertThat(erro.toString()).contains("Nao existem raizes reais");
    }

    @Test
    void given_ComandoDesconhecido_when_ComandoExecutado_then_ExibirComandoDesconhecido() {
        String comando = "comandodesconhecido";
        setEntrada(comando);

        Controlador.main(new String[0]);

        assertThat(erro.toString()).contains("Comando desconhecido");
    }

}
