package com.bhaskara.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Controlador {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("O que deseja executar? >> ");
        String comando = scanner.nextLine();

        switch (comando) {
            case "calcbhaskara":
                calcBhaskara(scanner);
                break;
            case "mathreport":
                mathreport(scanner);
                break;
            case "graph":
                graph(scanner);
                break;
            default:
                System.err.println("Comando desconhecido");
        }

        scanner.close();
    }

    private static void calcBhaskara(Scanner scanner) {
        final String[] COEFICIENTES = {"A", "B", "C"};
        String[] valores = new String[3];

        for(int i = 0; i < 3; i++) {
            System.out.print("Por favor, insira o valor de " + COEFICIENTES[i] + " >> ");
            valores[i] = scanner.nextLine();
        }

        try
        {
            Bhaskara.setCoeficientes(valores[0], valores[1], valores[2]);
            ArrayList<Double> raizes = Bhaskara.calcularRaizes();

            String retorno = "X1: " + raizes.get(0) + " | X2: " + raizes.get(1);
            System.out.println(retorno);
        }
        catch (Throwable throwable)
        {
            System.err.println(throwable.getMessage());
        }
    }

    private static void mathreport(Scanner scanner) {
        final String[] COEFICIENTES = {"A", "B", "C"};
        Map<String, String> valores = new HashMap<>();

        for(int i = 0; i < 3; i++) {
            System.out.print("Por favor, insira o valor de " + COEFICIENTES[i] + " >> ");
            valores.put("Valor " + COEFICIENTES[i], scanner.nextLine());
        }

        try
        {
            BhaskaraReport.createPDF(valores);
        }
        catch (Throwable throwable)
        {
            System.err.println(throwable.getMessage());
        }
    }

    private static void graph(Scanner scanner) {
        final String[] COEFICIENTES = {"A", "B", "C"};
        double[] valores = new double[3];

        for(int i = 0; i < 3; i++) {
            System.out.print("Por favor, insira o valor de " + COEFICIENTES[i] + " >> ");
            valores[i] = Double.parseDouble(scanner.nextLine());
        }

        try
        {
            PlotGraph plotGraph = new PlotGraph();
            plotGraph.criarGrafico(valores[0], valores[1], valores[2]);
        }
        catch (Throwable throwable)
        {
            System.err.println(throwable.getMessage());
        }
    }
}
