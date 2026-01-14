package com.ejemplo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Biblioteca {
    private Libro[] catalogo;
    private final int CAPACIDAD_MAXIMA = 1000;
    private int cantidadLibros;

    public Biblioteca() {
        this.catalogo = new Libro[CAPACIDAD_MAXIMA];
        this.cantidadLibros = 0;
    }

    public void cargaDeLibros() {
        Scanner miEscaner = null;
        File f = new File("libros.csv");
        try {
            miEscaner = new Scanner(f);
            if (miEscaner.hasNextLine()) {
                miEscaner.nextLine();
            }

            while (miEscaner.hasNextLine()) {
                if (cantidadLibros >= CAPACIDAD_MAXIMA) {
                    System.err.println("Capacidad máxima del catálogo alcanzada.");
                    break;
                }

                String linea = miEscaner.nextLine();
                String[] datos = linea.split(",");

                if (datos.length != 9) {
                    System.err.println("Fila inválida, se ignora: " + linea);
                    continue;
                }

                try {
                    String autorId = datos[5];
                    String autorNombre = datos[6];
                    String autorApellido = datos[7];
                    int aniosCarrera = Integer.parseInt(datos[8]);
                    Autor autor = new Autor(autorId, autorNombre, autorApellido, aniosCarrera);

                    String isbn = datos[0];
                    String titulo = datos[1];
                    int nroEstante = Integer.parseInt(datos[2]);
                    int paginas = Integer.parseInt(datos[3]);
                    double precioPorDia = Double.parseDouble(datos[4]);

                    Libro libro = new Libro(isbn, titulo, nroEstante, paginas, precioPorDia, autor);
                    
                    this.catalogo[cantidadLibros] = libro;
                    this.cantidadLibros++;
                } catch (NumberFormatException e) {
                    System.err.println("Error de formato numérico, se ignora: " + linea);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
        } finally {
            if (miEscaner != null) {
                miEscaner.close();
            }
        }
    }

    public void mostrarCatalogo() {
        System.out.println("--- Catálogo de Libros ---");
        for (int i = 0; i < cantidadLibros; i++) {
            System.out.println(catalogo[i]);
        }
        System.out.println("--------------------------");
    }

    public void calcularRecaudacion() {
        int tiempoPromedio = 15;
        double cantidadTotal = 0;
        for (int i = 0; i < cantidadLibros; i++) {
            cantidadTotal += catalogo[i].getPrecioPorDia() * tiempoPromedio;
        }
        System.out.println("La recaudación estimada es de: " + cantidadTotal);
        
    }

    public void autoresMayores() {
        int cantidadAutores = 0;
        for (int i = 0; i < cantidadLibros; i++) {
            if (catalogo[i].getAutor().getAniosCarrera() > 18) {
                cantidadAutores++;
            }
        }

        System.out.println("Autores mayores: " + cantidadAutores);
    }

    public void promedioPaginas() {
        int cantidadEstantesPares = 0;
        int sumaPaginas = 0;
        for (int i = 0; i < cantidadLibros; i++) {
            if ((catalogo[i].getNroEstante() % 2) == 0)
                cantidadEstantesPares++;
                sumaPaginas += catalogo[i].getPaginas();
        }
        if (cantidadEstantesPares == 0) {
            System.out.println("No hay libros en estantes pares. El promedio es 0.");
            return; 
        }    
        double promedio = (double) sumaPaginas / cantidadEstantesPares;
        System.out.println("el promedio de paginas de estantes pares es: " + promedio);

        
    }
}

