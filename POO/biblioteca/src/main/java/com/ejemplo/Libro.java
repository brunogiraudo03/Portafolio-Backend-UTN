package com.ejemplo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Libro {
    private String isbn;
    private String titulo;
    private int nroEstante;
    private int paginas;
    private double precioPorDia;
    private Autor autor;

    @Override
    public String toString() {
        return "ISBN: " + isbn + " titulo: " + titulo + " nro estante: " + nroEstante 
        + " paginas: " + paginas + " precio: " + precioPorDia + " Autor: " + autor.getNombre();
    }
}