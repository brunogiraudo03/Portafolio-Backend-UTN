package com.ejemplo;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println("ejercicio 4!");
        Scanner miEscaner = new Scanner(System.in);
        System.out.println("ingrese un nombre: ");
        String nombre = miEscaner.nextLine();
        System.out.println("ingrese la cantidad de horas: ");
        int canthoras = miEscaner.nextInt();
        System.out.println("cantidad de tareas completadas");
        int cantcomp = miEscaner.nextInt();
        
        // Calcule un índice de productividad usando esta fórmula
        // (tareas completadas * 10) - (5 puntos por cada hora faltante si trabajó menos de 8 horas).
        // Si trabajó más de 8 horas, agregar 5 puntos extra
        int indice = 0;

        if (canthoras <= 8) {
            indice = (cantcomp * 10) - ( 5 * (8-canthoras));
        } else {
            indice = (cantcomp * 10) + 5;
        }

        System.out.println("hola " + nombre + " su indice es: " + indice);
        miEscaner.close();

    }
}
