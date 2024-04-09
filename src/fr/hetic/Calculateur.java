package fr.hetic;

public class Calculateur {
    public static void main(String[] args) {

        Double num1 ;
        Double num2 ;
        try {
              num1 = Double.parseDouble(args[0]);
              num2 = Double.parseDouble(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Les deux premiers arguments doivent être des nombres");
            return;
        }
//
        String operator = args[2];
//
        Double resultat ;

        switch (operator) {
            case "+":
                resultat = num1 + num2;
                break;
            case "-":
                resultat = num1 - num2;
                break;
            case "*":
                resultat = num1 * num2;
                break;
            case "/":
                resultat = num1 / num2;
                break;
            default:
                System.out.println("Opérateur inconnu");
                return;
        }

        System.out.println(resultat);
    }
}