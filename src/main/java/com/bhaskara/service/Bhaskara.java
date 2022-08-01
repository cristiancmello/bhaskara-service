package com.bhaskara.service;

import java.util.ArrayList;

public class Bhaskara {

    public static ArrayList<Double> calcularRaizes(double a, double b, double c) {
        var conjuntoRaizes = new ArrayList<Double>();

        var delta = Math.pow(b, 2) + -4 * a * c; // b^2 -4ac
        var x1 = (-b + Math.sqrt(delta)) / 2 * a;
        var x2 = (-b - Math.sqrt(delta)) / 2 * a;

        conjuntoRaizes.add(x1);
        conjuntoRaizes.add(x2);

        return conjuntoRaizes;
    }

    public static String verificaoInvalid(String valueA, String valueB, String valueC){

        int v = 0;

        try {
            double a =Double.parseDouble(valueA);
            double b = Double.parseDouble(valueB);
            double c = Double.parseDouble(valueC);
            v = 0;

        } catch (NumberFormatException exception){
            v = 1;
        }

        String verificacao = Integer.toString(v);
        return verificacao;
    }
}

