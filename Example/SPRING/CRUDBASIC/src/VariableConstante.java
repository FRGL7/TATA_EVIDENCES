import java.util.Scanner;

public class VariableConstante {
    public static void main(String[] args){
        // Variable
        double variable1 = 2.5;
        // Constante
        final double constante1 = 2.5;
        // Modificación de valores
        variable1 = 5;
        //constante1 = 6; // No es posible (constante)
        // 2da. Constante
        final double constante2;
        Scanner escaner = new Scanner(System.in);
        variable1 = escaner.nextDouble();
        constante2 = escaner.nextDouble();
        // Impresión
        System.out.println(variable1);
        System.out.println(constante1);
        System.out.println(constante2);
        escaner.close();
    }
}
