package com.ejemplo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MascotaVirtualTest {
    private MascotaVirtual mascota;

    @BeforeEach
    void setUp() {
        mascota = new MascotaVirtual("Tomy");
    }

    @Test
    void testMascotaTieneNombre() {
        assertEquals("Tomy", mascota.getNombre());
    }

    @Test
    void testValoresInicialesCorrectos() {
        assertEquals(50, mascota.getEnergia());
        assertEquals(3, mascota.getHumor());
        assertFalse(mascota.isDuerme());
        assertTrue(mascota.isVive());
    }

    @Test
    void testComerAumentaEnergiaYHumor() {
        mascota.comer();
        // Energia: 50 + 10% = 55
        // Humor: 3 + 1 = 4
        assertEquals(55, mascota.getEnergia());
        assertEquals(4, mascota.getHumor());
    }

    @Test
    void testBeberAumentaEnergiaYHumor() {
        mascota.beber();
        // Energia: 50 + 5% = 52 (truncado)
        // Humor: 3 + 1 = 4
        assertEquals(52, mascota.getEnergia());
        assertEquals(4, mascota.getHumor());
    }

    @Test
    void testCorrerBajaEnergiaYSeDuermePorHumorBajo() {
        mascota.correr();
        // Energia: 50 - 35% = 32
        // Humor baja a 1 -> se duerme -> dormir() suma 25 energia y +2 humor
        assertEquals(58, mascota.getEnergia());
        assertEquals(3, mascota.getHumor());
        assertTrue(mascota.isDuerme());
    }

    @Test
    void testDormirYDespertar() {
        mascota.dormir();
        assertTrue(mascota.isDuerme());
        assertTrue(mascota.despertar());
        assertFalse(mascota.isDuerme());
    }

    @Test
    void testMuertePorEmpacho() {
        for (int i = 0; i < 5; i++) {
            mascota.comer();
        }
        assertFalse(mascota.isVive());
    }

    @Test
    void testMuertePorCansancio() {
        mascota.setEnergia(0); // fuerza a 0
        assertFalse(mascota.correr()); // al intentar correr debería morir
        assertFalse(mascota.isVive());
    }
    @Test
    void testSeDuermePorTresActividadesConsecutivas() {
        mascota.setHumor(5);
        mascota.correr(); // 1
        mascota.correr(); // 2
        // Ya en la tercera debería dormirse
        mascota.correr(); 
        assertTrue(mascota.isDuerme());
    }
}