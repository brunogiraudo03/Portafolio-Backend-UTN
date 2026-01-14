package com.ejemplo;

public class App {
    public static void main(String[] args) {
        MascotaVirtual m1 = new MascotaVirtual("firulais");
        
        System.out.println("Estado inicial: " + m1);

        System.out.println("\n--- Acciones de ingesta ---");
        m1.comer();
        System.out.println("Después de comer 1 vez: " + m1);
        m1.comer();
        System.out.println("Después de comer 2 veces: " + m1);
        m1.comer(); // Aquí el humor debería bajar
        System.out.println("Después de comer 3 veces (regla 1): " + m1);
        m1.beber();
        System.out.println("Después de beber: " + m1);
        
        System.out.println("\n--- Acciones de actividad ---");
        m1.saltar();
        System.out.println("Después de saltar 1 vez: " + m1);
        m1.saltar();
        System.out.println("Después de saltar 2 veces: " + m1);
        m1.saltar(); // Aquí la mascota se duerme por 3 actividades
        System.out.println("Después de saltar 3 veces (regla 4): " + m1);

        System.out.println("\n--- Intentando acciones en estado durmiendo ---");
        m1.correr(); // No debería hacer nada
        System.out.println("Intentando correr (durmiendo): " + m1);
        m1.despertar();
        System.out.println("Después de despertar: " + m1);
        
        System.out.println("\n--- Forzando la muerte ---");
        m1.correr(); // Esto debería bajar la energía
        System.out.println("Después de correr 1 vez: " + m1);
        m1.correr();
        System.out.println("Después de correr 2 veces: " + m1);
        m1.correr();
        System.out.println("Después de correr 3 veces: " + m1);
    }
}