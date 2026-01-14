package com.ejemplo;

public class App {
    public static void main(String[] args) {
        Biblioteca biblio = new Biblioteca();
        biblio.cargaDeLibros();
        biblio.mostrarCatalogo();
        biblio.calcularRecaudacion();
        biblio.autoresMayores();
        biblio.promedioPaginas();
    }
}