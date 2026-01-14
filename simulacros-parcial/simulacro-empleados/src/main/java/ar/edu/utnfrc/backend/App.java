package ar.edu.utnfrc.backend;

import java.net.URL;

import ar.edu.utnfrc.backend.infrastructure.DbInit;
import ar.edu.utnfrc.backend.menu.ApplicationContext;
import ar.edu.utnfrc.backend.menu.ItemMenu;
import ar.edu.utnfrc.backend.menu.Menu;
import ar.edu.utnfrc.backend.services.DepartamentoService;
import ar.edu.utnfrc.backend.services.EmpleadoService;
import ar.edu.utnfrc.backend.services.PuestoService;

public class App {
    public static void main(String[] args) throws Exception {
        //FORMA 1: Agregando al proyecto la infrastructure y ejecutando el schema al iniciar la app
        //DbInit.run();

        var ctx = ApplicationContext.getInstance();
        Menu menu = new Menu();
        menu.setTitulo("Menu de Opciones de Empleados");

        Acciones acciones = new Acciones();

        URL folderPath = App.class.getResource("/files");
        ctx.put("path", folderPath);

        ctx.registerService(EmpleadoService.class, new EmpleadoService());
        ctx.registerService(DepartamentoService.class, new DepartamentoService());
        ctx.registerService(PuestoService.class, new PuestoService());
        
        menu.addOpcion(new ItemMenu(1, "Cargar Empleados",acciones::importarCsv));
        menu.addOpcion(new ItemMenu(2, "Mostrar Cantidad de empleados Fijos y Contratados",acciones::mostrarCantidadDeEmpleadosFijosYNoFijos));
        menu.addOpcion(new ItemMenu(3, "Mostrar Empleados Fijos y Contratados",acciones::mostrarEmpleadosFijosYContratados));
        menu.addOpcion(new ItemMenu(4, "Mostrar Cantidad de Empleados por Departamento",acciones::mostrarCantidadDeEmpleadosPorDepartamento));
        menu.addOpcion(new ItemMenu(5, "Mostrar salario promedio por puesto",acciones::mostrarSalarioPromedioPorPuesto));       
    
        menu.ejecutar(ctx);
    }
}
