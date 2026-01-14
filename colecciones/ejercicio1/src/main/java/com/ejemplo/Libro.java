package com.ejemplo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Libro {
    private String titulo;
    private String autor;
    private int anioPublicacion;
    private String genero;

    @Override
    public String toString() {
        return "Titulo: " + titulo +
                " | Autor: " + autor +
                " | Año publicacion: " + anioPublicacion +
                " | Genero: " + genero;
    }
}
