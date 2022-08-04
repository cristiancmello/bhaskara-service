package com.bhaskara.service;

import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.ImageComparisonUtil;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import com.github.romankh3.image.comparison.model.ImageComparisonState;
import com.github.sh0nk.matplotlib4j.PythonExecutionException;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class PlotGraphTests {
    @Test
    void given_Coeficientes_whenConjuntoDeRaizes_thenGerarGrafico() throws PythonExecutionException, IOException {

        double a = 1;
        double b = -2;
        double c = -3;

        PlotGraph plotGraph = new PlotGraph();
        plotGraph.criarGrafico(a,b,c);

        BufferedImage graficoEsperado = ImageComparisonUtil.readImageFromResources("graficoEsperado.png");
        BufferedImage grafico = ImageComparisonUtil.readImageFromResources("grafico.png");

        ImageComparisonResult resultadoComparacao = new ImageComparison(graficoEsperado, grafico).compareImages();

        assertThat(ImageComparisonState.MATCH).isEqualTo(resultadoComparacao.getImageComparisonState());

    }
}
