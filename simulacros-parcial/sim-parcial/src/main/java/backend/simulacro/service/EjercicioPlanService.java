package backend.simulacro.service;

import java.util.HashMap;
import java.util.Map;

import backend.simulacro.entity.EjercicioPlan;
import backend.simulacro.repository.EjercicioPlanRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EjercicioPlanService implements Service<String, EjercicioPlan> {
    private final EjercicioPlanRepository repository;
    private final Map<String, EjercicioPlan> memoryMap = new HashMap<>();

    @Override
    public EjercicioPlan findOrCreate(String id, String[] args) {
        if (memoryMap.containsKey(id)) {
            return memoryMap.get(id);
        }
        EjercicioPlan newPlan = create(args);
        repository.save(newPlan);
        memoryMap.put(id, newPlan);
        return newPlan;
    }


    @Override
    public EjercicioPlan create(String[] args) {
        return EjercicioPlan.builder()
        .nombreMaquina(args[0])
        .descripcion(args[1])
        .series(Integer.parseInt(args[2]))
        .repeticiones(Integer.parseInt(args[3]))
        .build();
    }

    
}
