import java.util.Scanner;

public class MenuIterativo {
    public static void main(String[] args){
        Scanner escaner = new Scanner(System.in);
        int opcion;

        do{
            opcion = escaner.nextInt();
            switch(opcion){
                case 1:
                    System.out.println("Ingrese dos números");
                    int a = escaner.nextInt();
                    int b = escaner.nextInt();
                    System.out.println("Suma = " + (a+b));
                    break;
                case 3:
                    System.out.println("Ingrese el número");
                    int p = escaner.nextInt();
                    System.out.println("Potencia = " + Math.pow(p, 2));
                    break;
                case 9:
                    System.out.println("Fin de menú");
                    break;
                default:
                    System.out.println("Opción inválida");
                    break;
            }
        }while(opcion != 9);

        escaner.close();
    }
}
