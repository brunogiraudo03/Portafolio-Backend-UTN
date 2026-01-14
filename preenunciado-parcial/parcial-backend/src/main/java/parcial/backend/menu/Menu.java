package parcial.backend.menu; // <-- Paquete ajustado

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

// Importar la clase AppContext correcta
import parcial.backend.app.AppContext;

public class Menu<T> implements IMenu<T> {
    private Map<Integer, ItemMenu<T>> options = new HashMap<>();
    
    // Almacenar el scanner aquí es más simple que usar el AppContext para una clase simple
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void addOption(int opcion, ItemMenu<T> action) {
        this.options.put(opcion, action);
        // Opcional: Quitar el System.out.println("Opción registrada") para un menú limpio.
    }

    @Override
    public void runMenu(T context) {
        while (true) {
            int choice = this.showMenu(context);

            if (choice == 0) {
                System.out.println("Saliendo...");
                break;
            }

            if (this.options.containsKey(choice)) {
                this.options.get(choice).ejecutar(context);
            } else {
                System.out.println("Opción no válida.");
            }
        }
    }

    private int showMenu(T context) {
        
        System.out.println("\n-----------------");
        System.out.println("  Opciones disponibles:");
        System.out.println("-----------------");

        // Mostrar usando key + value
        for (Map.Entry<Integer, ItemMenu<T>> entry : this.options.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
        System.out.println("0 - Salir.");
        System.out.print("Seleccione opción: ");

        while (!scanner.hasNextInt()) { // Usamos la instancia local
            System.out.println("Entrada inválida. Intente de nuevo:");
            scanner.next(); // consumir la entrada no válida
        }

        return scanner.nextInt();
    }
}