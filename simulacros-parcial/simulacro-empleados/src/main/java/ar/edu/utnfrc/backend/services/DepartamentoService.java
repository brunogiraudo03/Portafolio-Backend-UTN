package ar.edu.utnfrc.backend.services;

import java.util.HashMap;

import ar.edu.utnfrc.backend.entities.Departamento;
import ar.edu.utnfrc.backend.repositories.DepartamentoRepository;

public class DepartamentoService {
    private final HashMap<String, Departamento> departamentos;
    private final DepartamentoRepository departamentoRepository;

    public DepartamentoService() {
        departamentos = new HashMap<>();
        departamentoRepository = new DepartamentoRepository();
    }

    public Departamento getOrCreateDepartamento(String nombre) {
        if (departamentos.containsKey(nombre))
            return departamentos.get(nombre);
            
        Departamento nuevoDepartamento = new Departamento(nombre);
        departamentos.put(nombre, nuevoDepartamento);
        departamentoRepository.add(nuevoDepartamento);
        return nuevoDepartamento;        
    }
}
