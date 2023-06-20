import java.util.Scanner;

public class EjercicioS01E2 {
    public static void main(String[] args){
        Scanner escaner = new Scanner(System.in);
        // Leer cantidad de lecturas
        System.out.print("Ingrese la cantidad de lecturas a procesar: ");
        int nLecturas = escaner.nextInt();
        
        int i = 0;
        int nVehiculosInfrac = 0;

        while(i < nLecturas){
            System.out.print("Ingrese la velocidad del vehículo #" + (i+1) + ": ");
            double velocidad = escaner.nextDouble();
            if(velocidad > 60)
                nVehiculosInfrac++;
            i++;
        }

        System.out.println("RESUMEN");
        System.out.println("Vehículos que incumplieron el Reglamento: " + nVehiculosInfrac);
        double porcInfrac = 100.0 * nVehiculosInfrac / nLecturas;
        System.out.println("Porcentaje de vehículos que incumplieron el Reglamento: " + (porcInfrac) + " %");

        escaner.close();
    }
}
