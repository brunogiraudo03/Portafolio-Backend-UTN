package parcial.backend.app;

import parcial.backend.repositories.PublisherRepository;
import parcial.backend.repositories.CategoryRepository;
import parcial.backend.repositories.context.DbContext;

public class App {

    public static void main(String[] args) {
        
        System.out.println("==================================================");
        System.out.println("  INICIO PRE-ENUNCIADO - VERIFICACIÓN INFRAESTRUCTURA ");
        System.out.println("==================================================");

        try {
            // 1. Ejecutar Inicializador de DB (carga el DDL)
            DbInitializer initializer = new DbInitializer();
            initializer.initialize(); 

            // 2. Intentar obtener el EntityManager: esto verifica que persistence.xml sea válido
            DbContext dbContext = DbContext.getInstance();
            if (dbContext.getManager().isOpen()) {
                 System.out.println("-> EntityManagerFactory y Contexto de Persistencia [OK].");
            } else {
                 throw new IllegalStateException("El EntityManager no se pudo abrir.");
            }
            
            // 3. Smoke Test de Repositorios: Contar registros de seed o verificar la tabla.
            PublisherRepository publisherRepo = new PublisherRepository();
            CategoryRepository categoryRepo = new CategoryRepository();
            
            long publisherCount = publisherRepo.getAllList().size();
            long categoryCount = categoryRepo.getAllList().size();

            // Si el DDL solo crea tablas (sin seed), la cuenta será 0. Si tiene seed, será > 0.
            // Lo importante es que la consulta NO tire excepción de "Tabla no encontrada".
            if (publisherCount >= 0 && categoryCount >= 0) {
                 System.out.println("\n[OK] DB Init + Mapeos JPA y Repositorios Verificados.");
                 System.out.printf("   > Registros iniciales en PUBLISHERS: %d\n", publisherCount);
                 System.out.printf("   > Registros iniciales en CATEGORIES: %d\n", categoryCount);
            } else {
                 throw new IllegalStateException("Fallo en el conteo de registros (Valor negativo?).");
            }
            
            System.out.println("\n==================================================");
            System.out.println("    ¡PRE-ENUNCIADO DE INFRAESTRUCTURA LISTO! 👍");
            System.out.println("    (Añadir aquí el parseo de CSV y el menú)");
            System.out.println("==================================================");
            
            // Aquí llamarías a tu menú:
            // AppMenu menu = new AppMenu(); 
            // menu.start();

        } catch (Exception e) {
            System.out.println("\n[FAIL] Falló la inicialización, el mapeo de JPA o el acceso a datos.");
            System.err.println("Error: " + e.getMessage());
            // Detener la ejecución si falla la infraestructura
            System.exit(1); 
        }
    }
}