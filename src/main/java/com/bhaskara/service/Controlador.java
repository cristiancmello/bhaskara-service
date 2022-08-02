package com.bhaskara.service;

import java.util.ArrayList;
import java.util.Scanner;

public class Controlador {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("O que deseja executar? >> ");
        String comando = scanner.next();

        switch (comando) {
            case "calcbhaskara":
                calcBhaskara(scanner);
                break;
            default:
                System.err.println("Comando desconhecido");
        }

        scanner.close();
    }

    private static void calcBhaskara(Scanner scanner) {
        System.out.print("Por favor, insira o valor de A >> ");
        String inputA = scanner.next();
        System.out.print("Por favor, insira o valor de B >> ");
        String inputB = scanner.next();
        System.out.print("Por favor, insira o valor de C >> ");
        String inputC = scanner.next();
        scanner.close();

        try
        {
            Bhaskara.setCoeficientes(inputA, inputB, inputC);
            ArrayList<Double> raizes = Bhaskara.calcularRaizes();

            String retorno = "X1: " + raizes.get(0) + " | X2: " + raizes.get(1);
            System.out.println(retorno);
        }
        catch (Throwable throwable)
        {
            System.err.println(throwable.getMessage());
        }
    }
}
