import java.util.Scanner;

public class EvaluadorMes {
    public static void main(String[] args){
        Scanner escaner = new Scanner(System.in);
        int mes = escaner.nextInt();
        if (mes >= 1 && mes <= 12){
            switch(escaner.nextInt()){
                case 4:
                case 6:
                case 9:
                case 11:
                    System.out.println("30 días");
                    break;
                case 2:
                    System.out.println("Ingrese el año");
                    int anho = escaner.nextInt();
                    if (anho%4==0 && anho%100!=0 || anho%400==0)
                        System.out.println("28 días");
                    else
                        System.out.println("29 días");
                    break;
                default:
                    System.out.println("31 días");

            }
        } else
            System.out.println("Incorrecto");

        escaner.close();
    }
}
