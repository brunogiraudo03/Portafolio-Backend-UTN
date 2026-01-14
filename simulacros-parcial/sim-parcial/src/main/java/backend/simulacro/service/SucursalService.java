package backend.simulacro.service;

import java.util.HashMap;
import java.util.Map;

import backend.simulacro.entity.Sucursal;
import backend.simulacro.repository.SucursalRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SucursalService implements Service<String, Sucursal> {
    private final SucursalRepository repository;
    private final Map<String, Sucursal> memoryMap = new HashMap<>();

    @Override
    public Sucursal findOrCreate(String description, String[] args) {
        if (memoryMap.containsKey(description)) {
            return memoryMap.get(description);
        }
        Sucursal newSucursal = create(args);
        repository.save(newSucursal);
        memoryMap.put(newSucursal.getDescripcion(), newSucursal);
        return newSucursal;
    }

    @Override
    public Sucursal create(String[] args) {
        return Sucursal.builder()
        .descripcion(args[0])
        .build();
    }

    

    
}
