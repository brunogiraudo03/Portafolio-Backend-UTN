package com.ejemplo;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Ejercicio 5!" );
        Scanner miEscaner = new Scanner(System.in);
        int max = 0;
        int min = 10;
        float suma = 0;
        int contador = 0;
        int aprobados = 0;
        int desaprobados = 0;
        while (true) {
            System.out.println("ingrese una nota: ");
            int nota = miEscaner.nextInt();
            if (nota == -1) {
                break;
            } 
            if (nota <= 10 && nota >= 0) {
                if (nota >= max) {
                    max = nota;
                }
                if (nota <= min) {
                    min = nota;
                }
                suma += nota;
                contador += 1;
                if (nota >= 6) {
                    aprobados += 1;
                } else {
                    desaprobados += 1;
                }
                
            } else {
                System.out.println("ingrese un numero entre 0 y 10");
            }
        }
        float promedio = suma / contador;


        System.out.println("nota maxima: " + max);
        System.out.println("nota minima: " + min );
        System.out.println("el promedio de notas es de: " + promedio);
        System.out.println("la cantidad de aprobados es de: " + aprobados);
        System.out.println("la cantidad de desaprobados es de: " + desaprobados);
        miEscaner.close();

    }
}
