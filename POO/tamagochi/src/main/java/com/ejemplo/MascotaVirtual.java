package com.ejemplo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MascotaVirtual {
    private String nombre;
    private int energia;
    private int humor;
    private boolean duerme;
    private boolean vive;
    private int ingestasConsecutivas;
    private int actividadesConsecutivas;

    public MascotaVirtual(String nombre) {
        this.nombre = nombre;
        this.energia = 50;
        this.humor = 3;
        this.duerme = false;
        this.vive = true;
        this.ingestasConsecutivas = 0;
        this.actividadesConsecutivas = 0;
    }
    
    public boolean comer() {
        if (!vive || duerme) {
            return false;
        }
        
        ingestasConsecutivas++;
        actividadesConsecutivas = 0;
        
        if (ingestasConsecutivas >= 5) {
            vive = false;
            System.out.println(nombre + " murió de empacho.");
            return false;
        }
        
        this.energia += (int) (this.energia * 0.10);
        this.humor++;
        
        if (ingestasConsecutivas >= 3) {
            this.humor--;
        }
        
        // Aplicar límites
        if (this.energia > 100) {
            this.energia = 100;
        }
        if (this.humor > 5) {
            this.humor = 5;
        }
        if (this.humor <= 1) {
            this.humor = 1; // El humor no puede ser menor a 1
            dormir();
        }

        return true;
    }
    
    public boolean beber() {
        if (!vive || duerme) {
            return false;
        }

        ingestasConsecutivas++;
        actividadesConsecutivas = 0;

        if (ingestasConsecutivas >= 5) {
            vive = false;
            System.out.println(nombre + " murió de empacho.");
            return false;
        }

        this.energia += (int) (this.energia * 0.05);
        this.humor++;

        if (ingestasConsecutivas >= 3) {
            this.humor--;
        }
        
        // Aplicar límites
        if (this.energia > 100) {
            this.energia = 100;
        }
        if (this.humor > 5) {
            this.humor = 5;
        }
        if (this.humor <= 1) {
            this.humor = 1;
            dormir();
        }

        return true;
    }

    public boolean correr() {
        if (!vive || duerme) {
            return false;
        }
        
        actividadesConsecutivas++;
        ingestasConsecutivas = 0;
        
        this.energia -= (int) (this.energia * 0.35);
        this.humor -= 2;

        if (this.energia <= 0) {
            vive = false;
            System.out.println(nombre + " murió de cansancio.");
            return false;
        }
        
        if (this.humor <= 1) {
            this.humor = 1;
            dormir();
            return true;
        }

        if (actividadesConsecutivas >= 3) {
            dormir();
            return true;
        }
        
        return true;
    }

    public boolean saltar() {
        if (!vive || duerme) {
            return false;
        }
        
        actividadesConsecutivas++;
        ingestasConsecutivas = 0;

        this.energia -= (int) (this.energia * 0.15);
        this.humor -= 2;

        if (this.energia <= 0) {
            vive = false;
            System.out.println(nombre + " murió de cansancio.");
            return false;
        }

        if (this.humor <= 1) {
            this.humor = 1;
            dormir();
            return true;
        }

        if (actividadesConsecutivas >= 3) {
            dormir();
            return true;
        }

        return true;
    }

    public boolean dormir() {
        if (!vive) {
            return false;
        }
        if (duerme) {
            return false;
        }
        
        this.duerme = true;
        this.energia += 25;
        this.humor += 2;
        this.ingestasConsecutivas = 0;
        this.actividadesConsecutivas = 0;
        
        if (this.energia > 100) {
            this.energia = 100;
        }
        if (this.humor > 5) {
            this.humor = 5;
        }
        
        return true;
    }

    public boolean despertar() {
        if (!vive || !duerme) {
            return false;
        }
        
        this.duerme = false;
        this.humor--;
        
        if (this.humor < 1) {
            this.humor = 1;
        }

        return true;
    }
    
    @Override
    public String toString() {
        String estadoHumor;
        if (humor == 1) estadoHumor = "muy enojado";
        else if (humor == 2) estadoHumor = "enojado";
        else if (humor == 3) estadoHumor = "neutral";
        else if (humor == 4) estadoHumor = "contento";
        else estadoHumor = "chocho";
        
        return "MascotaVirtual: " +
                "nombre='" + nombre + '\'' +
                ", energia=" + energia +
                ", humor=" + estadoHumor +
                ", duerme=" + duerme +
                ", vive=" + vive;
    }
}