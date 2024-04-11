package fr.hetic.impl;

import fr.hetic.Interface.Operation;

public class Multiplication implements Operation {
    @Override
    public double calcul(double a, double b) {
        return a * b;
    }
}
