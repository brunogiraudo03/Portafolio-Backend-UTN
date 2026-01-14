package backend.simulacro.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import backend.simulacro.entity.Abonado;
import backend.simulacro.entity.EjercicioPlan;
import backend.simulacro.entity.Sucursal;
import backend.simulacro.repository.AbonadoRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AbonadoService implements Service<String, Abonado> {
    
    private final AbonadoRepository repository;
    private final SucursalService sucursalService;
    private final EjercicioPlanService ejercicioPlanService;
    private final Map<String, Abonado> memoryMap =  new HashMap<>();

    @Override
    public Abonado findOrCreate(String id, String[] args) {
        if (memoryMap.containsKey(id)) {
            return memoryMap.get(id);
        }

        Abonado newAbonado = create(args);
        repository.save(newAbonado);
        memoryMap.put(id, newAbonado);
        return newAbonado;
    }

    @Override
    public Abonado create(String[] args) {
        String sucursalDesc = args[5];
        String[] sucData = new String[] {sucursalDesc};
        Sucursal suc = sucursalService.findOrCreate(sucursalDesc, sucData);

        String nombreMaquina = args[6];
        String descEjerc = args[7];
        String series = args[8];
        String rep = args[9];
        String[] ejerData = new String[] {nombreMaquina, descEjerc, series, rep};
        String ejercKey = String.join("-", ejerData);
        EjercicioPlan ejercicioPlan = ejercicioPlanService.findOrCreate(ejercKey, ejerData);
        List<EjercicioPlan> plan = new ArrayList<>();
        plan.add(ejercicioPlan);
        return Abonado.builder()
        .dni(args[0])
        .nombre(args[1])
        .email(args[2])
        .telefono(args[3])
        .precio(Double.valueOf(args[4]))
        .sucursal(suc)
        .planEjercicios(plan)
        .build();
    }
    
}
