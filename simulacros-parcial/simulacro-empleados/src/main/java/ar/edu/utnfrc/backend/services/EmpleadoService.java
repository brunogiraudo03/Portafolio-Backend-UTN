package ar.edu.utnfrc.backend.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;

import ar.edu.utnfrc.backend.entities.Empleado;
import ar.edu.utnfrc.backend.repositories.EmpleadoRepository;

public class EmpleadoService {
    private final EmpleadoRepository empleadoRepository;
    private final DepartamentoService departamentoService;
    private final PuestoService puestoService;

    public EmpleadoService() {
        empleadoRepository = new EmpleadoRepository();
        departamentoService = new DepartamentoService();
        puestoService = new PuestoService();
    }

    public void cantidadDeEmpleadosFijosYNoFijos() {
        Map<Boolean, Long> conteo = empleadoRepository.getAllStream()
                .collect(Collectors.groupingBy(
                        Empleado::isEmpleadoFijo,
                        Collectors.counting()
                ));

        conteo.forEach((esFijo, cantidad) -> {
            String tipo = esFijo ? "Fijos" : "No Fijos";
            System.out.println("Cantidad de empleados " + tipo + ": " + cantidad);
        });
    }

    public void mostrarEmpleadosFijosYContratados(){
        empleadoRepository.getAllStream()
                .forEach(empleado -> {
                    Double sueldoCalculado = empleado.calcularSalarioFinal();
                    System.out.println(empleado.getNombre() + " - Salario base: " + empleado.getSalario() + " - Es fijo: " + empleado.isEmpleadoFijo() + " - Salario final calculado: " + sueldoCalculado);
                });
    }

    public void cantidadDeEmpleadosPorDepartamento(){
        Map<String, Long> cantidadPorDepartamento = empleadoRepository.getAllStream()
                .collect(Collectors.groupingBy(
                        empleado -> empleado.getDepartamento().getNombre(),
                        Collectors.counting()
                ));
        cantidadPorDepartamento.forEach((departamento, cantidad) -> {
            System.out.println("Departamento: " + departamento + " - Cantidad de empleados: " + cantidad);
        });
    }

    public void salarioPromedioPorPuesto() {
        Map<String, Double> promedioPorPuesto = empleadoRepository.getAllStream()
                .collect(Collectors.groupingBy(
                        empleado -> empleado.getPuesto().getNombre(),
                        Collectors.averagingDouble(Empleado::calcularSalarioFinal)
                ));

        promedioPorPuesto.forEach((puesto, promedio) -> {
            System.out.println("Puesto: " + puesto + " - Salario promedio: " + promedio);
        });
    }

    public void bulkInsert(File fileToImport) throws IOException {
        Files.lines(Paths.get(fileToImport.toURI()))
                .skip(1)
                .forEach(linea -> {
                    Empleado empleado = procesarLinea(linea);
                    this.empleadoRepository.add(empleado);
                });
    }

    private Empleado procesarLinea(String linea) {
        String[] valores = linea.split(",");
        Empleado empleado = new Empleado();

        empleado.setNombre(valores[0]);
        empleado.setEdad(Integer.parseInt(valores[1]));
        empleado.setFechaIngreso(LocalDate.parse(valores[2]));
        empleado.setSalario(Double.parseDouble(valores[3]));
        empleado.setEmpleadoFijo(valores[4].equalsIgnoreCase("1"));
        empleado.setDepartamento(departamentoService.getOrCreateDepartamento(valores[5]));
        empleado.setPuesto(puestoService.getOrCreatePuesto(valores[6]));

        return empleado;
    }
}
