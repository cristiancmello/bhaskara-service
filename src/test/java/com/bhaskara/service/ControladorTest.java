package com.bhaskara.service;

import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.ImageComparisonUtil;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import com.github.romankh3.image.comparison.model.ImageComparisonState;
import com.testautomationguru.utility.CompareMode;
import com.testautomationguru.utility.PDFUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
    void given_ComandoMathReport_when_ComandoExecutado_then_CriarPDFComPropriedadesDaEquacao() throws IOException {
        String comando = "mathreport";
        String inputA = "1", inputB = "-2", inputC = "-3";
        setEntrada(comando, inputA, inputB, inputC);

        Controlador.main(new String[0]);

        PDFUtil pdfUtil = new PDFUtil();
        pdfUtil.setCompareMode(CompareMode.VISUAL_MODE);

        assertThat(pdfUtil.compare("bhaskaraDescription.pdf" , "compareDescription.pdf")).isTrue();
    }

    @Test
    void given_ComandoGraph_when_ComandoExecutado_then_CriarGraficoPNG() throws IOException {
        String comando = "graph";
        String inputA = "1", inputB = "-2", inputC = "-3";
        setEntrada(comando, inputA, inputB, inputC);

        Controlador.main(new String[0]);

        BufferedImage graficoEsperado = ImageComparisonUtil.readImageFromResources("graficoEsperado.png");
        BufferedImage grafico = ImageComparisonUtil.readImageFromResources("grafico.png");

        ImageComparisonResult resultadoComparacao = new ImageComparison(graficoEsperado, grafico).compareImages();

        assertThat(ImageComparisonState.MATCH).isEqualTo(resultadoComparacao.getImageComparisonState());
    }

    @Test
    void given_ComandoDesconhecido_when_ComandoExecutado_then_ExibirComandoDesconhecido() {
        String comando = "comandodesconhecido";
        setEntrada(comando);

        Controlador.main(new String[0]);

        assertThat(erro.toString()).contains("Comando desconhecido");
    }

}
