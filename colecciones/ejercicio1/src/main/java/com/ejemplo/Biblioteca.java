package com.ejemplo;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Libro> libros;

    public Biblioteca() {
        this.libros = new ArrayList<>();
    }

    public boolean agregar(Libro l) {
        if (l == null) return false;
        libros.add(l);
        return true;
    }

    public void listar() {
        if (libros.isEmpty()) {
            System.out.println("La biblioteca está vacía.");
        } else {
            for (Libro libro : libros) {
                System.out.println(libro);
            }
        }
    }

    public void buscarPorAutor(String autor) {
        boolean encontrado = false;
        for (Libro libro : libros) {
            if (libro.getAutor().equalsIgnoreCase(autor)) {
                System.out.println(libro);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontraron libros del autor: " + autor);
        }
    }

    public double obtenerPromedioAnio() {
        if (libros.isEmpty()) return 0.0;
        int suma = 0;
        for (Libro libro : libros) {
            suma += libro.getAnioPublicacion();
        }
        return (double) suma / libros.size();
    }
}
