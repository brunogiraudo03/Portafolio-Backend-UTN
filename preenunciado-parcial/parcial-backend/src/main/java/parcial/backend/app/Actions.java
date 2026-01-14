package parcial.backend.app;

/**
 * Clase que encapsula la lógica de negocio de la aplicación (las acciones del menú).
 * Utiliza el AppContext para acceder a los servicios.
 */
public class Actions {


    /**
     * Opción 1 del menú: Carga el dataset CSV en la base de datos.
     */
    public void cargarDatosCSV(AppContext context) {
        System.out.println("------------------------------------");
        System.out.println(" Invocando carga de datos desde CSV...");
        System.out.println("------------------------------------");
    }
    /**
     * Opción 2 del menú: listar todos los juegos (para verificación).
     */
    public void listarTodosLosJuegos(AppContext context) {
        System.out.println("------------------------------------");
        System.out.println(" Listado de todos los juegos (Ejemplo):");
        System.out.println("------------------------------------");

    }
}