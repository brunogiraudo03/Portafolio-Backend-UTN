package com.ejemplo;


public class App 
{
    public static void main( String[] args )
    {
        // figura 1
        System.out.println("figura 1");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print("*");
                System.out.print(" ");
            }
        System.out.println();
        }
        System.out.println();

        System.out.println("figura 2");
        //figura 2
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                boolean band = true;
                if (((i % 2) != 0) && band) {
                    System.out.print(" *");
                    band = false;
                } else {
                    System.out.print("* ");
                }
                
            }
        System.out.println();
        }
        System.out.println();

        //figura 3
        System.out.println("figura 3");
        int filas = 5;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print("* ");
            }
        System.out.println();
        }
        
        //figura 4
        System.out.println("figura 4");
        int filas1 = 5; // altura máxima

        // Parte 1: crecimiento
        for (int i = 0; i < filas1; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }

        // Parte 2: decrecimiento
        for (int i = filas1 - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }







    
    }
}
