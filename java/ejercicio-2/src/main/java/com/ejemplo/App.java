package com.ejemplo;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "International Standard Book Number" );
        Scanner miEscaner = new Scanner(System.in);
        String numero;
        System.out.println("ingrese el numero isbn: ");
        numero = miEscaner.nextLine();
        String isbnLimpio = "";
        for (int i = 0; i < numero.length(); i++) {
            char caracter = numero.charAt(i);
            if (caracter != '-') {
                isbnLimpio += caracter;
            }    
        }

        if (isbnLimpio.length() != 10) {
            System.out.println("inválido");
            miEscaner.close();
            return; 
        }

        
        int suma = 0;
        for (int i = 0; i < isbnLimpio.length(); i++) {
            char num = isbnLimpio.charAt(i);
            int digito;
            String numString = String.valueOf(num);
            digito = Integer.parseInt(numString);
            suma += digito * (10 - i);


        }

                if (suma % 11 == 0) {
            System.out.println("válido");
        } else {
            System.out.println("inválido");
        }

        miEscaner.close();
        


        //(x1 * 10 + x2 * 9 + x3 * 8 + x4 * 7 + x5 * 6 + x6 * 5 + x7 * 4 + x8 * 3 + x9 * 2 + x10 * 1) mod 11 == 0
        
        



    }
}
