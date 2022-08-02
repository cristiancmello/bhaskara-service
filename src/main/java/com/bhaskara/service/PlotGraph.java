package com.bhaskara.service;

import com.github.sh0nk.matplotlib4j.NumpyUtils;
import com.github.sh0nk.matplotlib4j.Plot;
import com.github.sh0nk.matplotlib4j.PythonExecutionException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlotGraph {

    public void criarGrafico(double a, double b, double c) throws PythonExecutionException, IOException {
        // f(x) = x^2 −2x - 3   (coef: 1, -2, -3)
        // x1 = -1, x2 = 3
        List<Double> axisX = NumpyUtils.linspace(-1 + (-5), 3 + (5), 100);
        List<Double> axisY = new ArrayList<>();
        List<Double> constY = new ArrayList<>();
        List<Double> constX = new ArrayList<>();
        List<Double> rootX1 = new ArrayList<>();
        List<Double> rootX2 = new ArrayList<>();

        axisX.forEach(x -> {
            axisY.add(Math.pow(x*a, 2) + b * x + c);
            constY.add(0.0);
            constX.add(0.0);

            rootX1.add(-1.0);
            rootX2.add(3.0);
        });

        Plot plt = Plot.create();
        plt.figure("");

        plt.xlabel("X");
        plt.ylabel("Y");

        plt.subplot(1, 1, 1);

        plt.plot().add(axisX, constY).color("black"); // Eixo X
        plt.plot().add(constX, axisY).color("black"); // Eixo Y

        plt.plot().add(axisX, axisY);

        plt.plot().linestyle("dotted").color("black")
                .add(rootX1, axisY)
                .add(rootX2, axisY);

        plt.savefig("quadratic_polynomial.png");
        plt.executeSilently();
    }



}
