package com.ejemplo;

public class App {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        Libro l1 = new Libro("Cien años de soledad", "Gabriel García Márquez", 1967, "Novela");
        Libro l2 = new Libro("El principito", "Antoine de Saint-Exupéry", 1943, "Infantil");
        Libro l3 = new Libro("Rayuela", "Julio Cortázar", 1963, "Novela");

        biblioteca.agregar(l1);
        biblioteca.agregar(l2);
        biblioteca.agregar(l3);

        System.out.println("📚 Lista de libros:");
        biblioteca.listar();

        System.out.println("\n🔎 Buscar por autor 'Julio Cortázar':");
        biblioteca.buscarPorAutor("Julio Cortázar");

        System.out.println("\n📊 Promedio de año de publicación:");
        System.out.println(biblioteca.obtenerPromedioAnio());
    }
}
