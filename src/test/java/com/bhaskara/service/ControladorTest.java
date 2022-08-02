package com.bhaskara.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

class ControladorTest {
    private ByteArrayOutputStream saida;
    private ByteArrayOutputStream erro;

    @BeforeEach
    void setSaida() {
        saida = new ByteArrayOutputStream();
        System.setOut(new PrintStream(saida));
        erro = new ByteArrayOutputStream();
        System.setErr(new PrintStream(erro));
    }

    void setEntrada(String inputString) {
        var entrada = new ByteArrayInputStream(inputString.getBytes());
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
        String inputA = "1";
        String inputB = "-2";
        String inputC = "-3";

        String inputString = comando + "\n" + inputA + "\n" + inputB + "\n" + inputC;
        setEntrada(inputString);

        Controlador.main(new String[0]);

        assertThat(saida.toString()).contains("3.0", "-1.0");
    }

    @Test
    void given_ComandoDesconhecido_when_ComandoExecutado_then_ExibirComandoDesconhecido() {
        String inputString = "comandodesconhecido";
        setEntrada(inputString);

        Controlador.main(new String[0]);

        assertThat(erro.toString()).contains("Comando desconhecido");
    }

}
