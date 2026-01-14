package com.ejemplo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.out.println("Ejercicio 6!");

        // El archivo que vamos a leer
        File f = new File("numeros.txt"); 

        // Variables estadísticas
        int cantidadValidas = 0;
        int lineasInvalidas = 0;
        int pares = 0;
        int impares = 0;
        long suma = 0;

        // Intentamos abrir el archivo
        Scanner miEscaner = null; // Inicializamos el Scanner fuera del try
        try {
            miEscaner = new Scanner(f); // Intentamos crear el Scanner
            
            // Bucle principal para leer el archivo línea por línea
            while (miEscaner.hasNextLine()) {
                String linea = miEscaner.nextLine();
                
                // Intentamos convertir la línea a un número.
                // Si falla (ej. si la línea está vacía o tiene texto),
                // el 'catch' se encargará de contar la línea como inválida.
                try {
                    int numero = Integer.parseInt(linea);

                    // Si la conversión funcionó, es un número válido.
                    cantidadValidas++;
                    suma += numero;

                    if (numero % 2 == 0) {
                        pares++;
                    } else {
                        impares++;
                    }

                } catch (NumberFormatException nfe) {
                    // Esta parte se ejecuta solo si parseInt() no puede convertir la línea a un número.
                    lineasInvalidas++;
                }
            }

        } catch (FileNotFoundException e) {
            // Este 'catch' se activa solo si el archivo 'numeros.txt' no existe.
            System.out.println("Error: no se encontró el archivo 'numeros.txt'.");
            return; // Termina el programa.
        } finally {
            // El bloque 'finally' siempre se ejecuta. 
            // Esto asegura que el Scanner se cierre, incluso si hay un error.
            if (miEscaner != null) {
                miEscaner.close();
            }
        }
        
        // --- Mostrar resultados (el resto del código es igual) ---
        System.out.println("\n--- Resultados ---");
        System.out.println("Cantidad total de números leídos (válidos): " + cantidadValidas);
        System.out.println("Cantidad total de líneas no válidas: " + lineasInvalidas);
        System.out.println("Cantidad de números pares: " + pares);
        System.out.println("Cantidad de números impares: " + impares);
        if (cantidadValidas > 0) {
            double promedio = (double) suma / cantidadValidas;
            System.out.printf("Promedio: %.2f%n", promedio);
        } else {
            System.out.println("Promedio: no hay números válidos para calcular.");
        }
    }
}