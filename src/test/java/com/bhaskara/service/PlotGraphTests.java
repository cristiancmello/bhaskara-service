package com.bhaskara.service;

import com.github.sh0nk.matplotlib4j.PythonExecutionException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class PlotGraphTests {
    @Test
    void testing() throws PythonExecutionException, IOException {

        double a = 1;
        double b = -2;
        double c = -3;

        PlotGraph plotGraph = new PlotGraph();
        plotGraph.criarGrafico(a,b,c);


        //plotGraph.inserirGraficoEmPDF();






        //TODO: verificar se o md5 é igual ao modelo gerado.
        //calcule o md5 modelo do arquivo grafico.pdf (pesquisar no java o metodo de calcular md5 a partir de um arquivo)
        //calcule o md5 do grafico gerado pelo plotGraph
        //teste se o md5 modelo é igual ao md5 gerado pelo plotGraph
        //se for diferente indica que nao sao os mesmos arquivos o que pode dizer que a aplicacao nao esta gerando
        //o grafico no pdf corretamente, se for igual indica que esta funcionando tudo certo.




    }
}
