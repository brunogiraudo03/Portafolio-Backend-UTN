package parcial.backend.app;

import parcial.backend.repositories.PublisherRepository;
import parcial.backend.repositories.context.DbContext;
import parcial.backend.services.*;
import parcial.backend.menu.Menu;
import parcial.backend.menu.ItemMenu;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        
        // ===============================================
        // FASE 1: SMOKE TEST DE INFRAESTRUCTURA (REQUISITO 3.5)
        // ===============================================
        
        System.out.println("==================================================");
        System.out.println("  INICIO PRE-ENUNCIADO - VERIFICACIÓN INFRAESTRUCTURA ");
        System.out.println("==================================================");

        try {
            // 1. Ejecutar Inicializador de DB (DB_CLOSE_DELAY=-1)
            DbInitializer initializer = new DbInitializer();
            initializer.initialize(); 

            // 2. Verificar que el EntityManagerFactory está activo
            DbContext dbContext = DbContext.getInstance();
            if (!dbContext.getManager().isOpen()) {
                 throw new IllegalStateException("El EntityManagerFactory no se pudo abrir.");
            }
            
            // 3. Smoke Test de Repositorios (Consulta mínima)
            long publisherCount = new PublisherRepository().getAllList().size();
            System.out.println("\n[OK] DB Init + Mapeos JPA y Repositorios Verificados.");
            System.out.printf("   > Registros iniciales en PUBLISHERS: %d\n", publisherCount);

        } catch (Exception e) {
            System.out.println("\n[FAIL] Falló la infraestructura base. Corregir antes de continuar.");
            System.err.println("Error: " + e.getMessage());
            System.exit(1); 
            return; // Detiene la ejecución si falla la infraestructura
        }

        // ===============================================
        // FASE 2: INICIALIZACIÓN DEL CONTEXTO Y MENÚ
        // ===============================================
        
        System.out.println("\n==================================================");
        System.out.println("    INICIANDO APP CONTEXT Y MENÚ DE OPCIONES");
        System.out.println("==================================================");

        // 1. Inicializar context global de la app
        AppContext context = AppContext.getInstance();

        // 2. Registrar todos los servicios en el AppContext
        context.registerService(BoardGameService.class, new BoardGameService());
        context.registerService(CategoryService.class, new CategoryService());
        context.registerService(DesignerService.class, new DesignerService());
        context.registerService(PublisherService.class, new PublisherService());
        
        // 3. Inicializar el Scanner (necesario para la clase Menu)
        Scanner sc = new Scanner(System.in);
        context.put("scanner", sc);
        
        // 4. Configurar las Acciones y el Menú
        Actions actions = new Actions();
        Menu<AppContext> menu = new Menu<>();
        
        // === REGISTRO DE OPCIONES (Esqueleto para el parcial) ===
        // Recuerda: menu.addOption(NUMERO_OPCION, new ItemMenu<>("TEXTO", actions::METODO_A_INVOCAR));
        
        menu.addOption(1, new ItemMenu<>(
            "Cargar Datos desde CSV", 
            actions::cargarDatosCSV // Método que implementaste en Actions
        ));
        
        // OPCIONES DE FILTRADO/CONSULTA REQUERIDAS EN EL PARCIAL
        menu.addOption(2, new ItemMenu<>(
            "Listar Todos los Juegos", 
            actions::listarTodosLosJuegos
        ));
        

        // 5. Iniciar el ciclo del menú
        menu.runMenu(context);
        
        // Al salir del menú, cerrarmos el Scanner.
        sc.close();
        System.out.println("Aplicación finalizada. ¡Éxitos en el parcial!");
    }
}