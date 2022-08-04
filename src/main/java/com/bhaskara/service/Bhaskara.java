package com.bhaskara.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class Bhaskara {
    static Double a;
    static Double b;
    static Double c;

    static Double delta;

    static Double x1;
    static Double x2;

    public static ArrayList<Double> calcularRaizes(double a, double b, double c) {
        if (a == 0) throw new IllegalArgumentException("Nao existem raizes reais");

        var conjuntoRaizes = new ArrayList<Double>();

        delta = Math.pow(b, 2) + -4 * a * c; // b^2 -4ac
        x1 = (-b + Math.sqrt(delta)) / 2 * a;
        x2 = (-b - Math.sqrt(delta)) / 2 * a;

        conjuntoRaizes.add(x1);
        conjuntoRaizes.add(x2);

        return conjuntoRaizes;
    }

    public static ArrayList<Double> calcularRaizes() {
        return calcularRaizes(a, b, c);
    }

    public static void setCoeficientes(String inputA, String inputB, String inputC) {

        try{
            a = Double.parseDouble(inputA);
            b = Double.parseDouble(inputB);
            c = Double.parseDouble(inputC);
        }catch (NumberFormatException exception){
            throw new NumberFormatException("Por favor insira numeros reais!");
        }
    }

    public static Map<String, String> takePropriedades(){

        Map<String, String> conteudo = new HashMap<>();

        conteudo.put("Valor A", String.valueOf(a));
        conteudo.put("Valor B", String.valueOf(b));
        conteudo.put("Valor C", String.valueOf(c));
        conteudo.put("Valor Delta", String.valueOf(delta));
        conteudo.put("Valor X1", String.valueOf(x1));
        conteudo.put("Valor X2", String.valueOf(x2));

        return conteudo;
    }
}
