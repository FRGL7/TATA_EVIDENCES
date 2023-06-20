import java.util.Scanner;

public class SaludoConEntrada {
    public static void main(String[] args) throws Exception {
        System.out.println("Escriba su nombre");
        // Abriendo el escáner
        Scanner escaner = new Scanner(System.in);
        // nextLine() -> Lee una línea y la interpreta como cadena de caracteres (String).
        //String nombre = escaner.nextLine();
        String nombre;
        nombre = escaner.nextLine();
        System.out.println("Hola " + nombre + ". Bienvenido(a) al curso.");
        escaner.close();
    }
}
