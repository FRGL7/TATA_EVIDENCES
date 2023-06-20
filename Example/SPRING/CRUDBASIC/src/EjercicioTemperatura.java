import java.util.Scanner;

public class EjercicioTemperatura {
    public static void main(String[] args){
        Scanner escaner = new Scanner(System.in);
        int temperatura = escaner.nextInt();
        if(temperatura > 40)
            System.out.println("Caliente");
        // && -> Conjunciones (Y)
        // || -> Disyunciones (O)
        else if(temperatura >= 15 && temperatura <= 40)
            System.out.println("Templado");
        else
            System.out.println("Frío");
        escaner.close();
    }
}
