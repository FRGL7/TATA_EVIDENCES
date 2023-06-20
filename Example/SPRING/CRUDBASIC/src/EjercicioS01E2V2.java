import java.util.Scanner;

public class EjercicioS01E2V2 {
    public static void main(String[] args){
        Scanner escaner = new Scanner(System.in);
        
        int i = 1;
        int nVehiculosInfrac = 0;
        double velocidad;

        do{
            System.out.print("Ingrese la velocidad del vehículo #" + i + ": ");
            velocidad = escaner.nextDouble();
            if(velocidad != -1){
                if(velocidad > 60)
                    nVehiculosInfrac++;
                i++;
            }
        }while(velocidad != -1);

        System.out.println("RESUMEN");
        System.out.println("Vehículos que incumplieron el Reglamento: " + nVehiculosInfrac);
        double porcInfrac = 100.0 * nVehiculosInfrac / (i-1);
        System.out.println("Porcentaje de vehículos que incumplieron el Reglamento: " + (porcInfrac) + " %");

        escaner.close();
    }
}
