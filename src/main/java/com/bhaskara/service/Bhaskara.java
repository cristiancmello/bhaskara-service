package com.bhaskara.service;

import java.util.ArrayList;

public class Bhaskara {

    public static ArrayList<Double> calcularRaizes(double a, double b, double c) {
        if (a == 0) throw new IllegalArgumentException("Nao existem raizes reais");

        var conjuntoRaizes = new ArrayList<Double>();

        var delta = Math.pow(b, 2) + -4 * a * c; // b^2 -4ac
        var x1 = (-b + Math.sqrt(delta)) / 2 * a;
        var x2 = (-b - Math.sqrt(delta)) / 2 * a;

        conjuntoRaizes.add(x1);
        conjuntoRaizes.add(x2);

        return conjuntoRaizes;
    }
}
