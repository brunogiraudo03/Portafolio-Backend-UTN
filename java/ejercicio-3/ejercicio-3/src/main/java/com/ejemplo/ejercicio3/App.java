package com.ejemplo.ejercicio3;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.out.println("Ejercicio 3!");
        Scanner miEscaner = new Scanner(System.in);
        int numero;

        // Bucle para pedir el número hasta que sea válido (mayor que 0).
        // Se usará un 'do-while' para que el código se ejecute al menos una vez.
        do {
            System.out.println("Ingrese un número entero positivo: ");
            numero = miEscaner.nextInt();

            if (numero <= 0) {
                System.out.println("Error: el número debe ser mayor que cero.");
            }
        } while (numero <= 0);

        // Bucle para recorrer todos los números desde 1 hasta el número ingresado.
        System.out.println("Números que son múltiplos de 3 o 5, pero no de ambos:");
        for (int i = 1; i <= numero; i++) {
            
            // Lógica para verificar la condición:
            // Un número es válido si:
            // 1) Es múltiplo de 3 Y NO es múltiplo de 5 (i % 3 == 0 && i % 5 != 0)
            // O
            // 2) Es múltiplo de 5 Y NO es múltiplo de 3 (i % 5 == 0 && i % 3 != 0)
            
            if ((i % 3 == 0 && i % 5 != 0) || (i % 5 == 0 && i % 3 != 0)) {
                System.out.println(i);
            }
        }

        miEscaner.close();
    }
}