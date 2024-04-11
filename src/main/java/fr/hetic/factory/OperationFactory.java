package fr.hetic.factory;

import fr.hetic.impl.Addition;
import fr.hetic.impl.Multiplication;
import fr.hetic.impl.Substraction;
import fr.hetic.Interface.Operation;

public class OperationFactory {
    public static Operation getOperation(String operation) {
        switch (operation) {
            case "+":
                return new Addition();
            case "-":
                return new Substraction();
            case "*":
                return new Multiplication();
            default:
                throw new IllegalArgumentException("Unexpected value: " + operation);
        }
    }
}
