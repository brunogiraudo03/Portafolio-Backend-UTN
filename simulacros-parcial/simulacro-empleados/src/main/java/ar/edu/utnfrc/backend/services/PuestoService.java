package ar.edu.utnfrc.backend.services;

import java.util.HashMap;

import ar.edu.utnfrc.backend.entities.Puesto;
import ar.edu.utnfrc.backend.repositories.PuestoRepository;

public class PuestoService {
    private final HashMap<String, Puesto> puestos;
    private final PuestoRepository puestoRepository;

    public PuestoService() {
        puestos = new HashMap<>();
        puestoRepository = new PuestoRepository();
    }

    public Puesto getOrCreatePuesto(String nombre) {
        if (puestos.containsKey(nombre))
            return puestos.get(nombre);
            
        Puesto nuevoPuesto = new Puesto(nombre);
        puestos.put(nombre, nuevoPuesto);
        puestoRepository.add(nuevoPuesto);
        return nuevoPuesto;        
    }
}
