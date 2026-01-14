package parcial.backend.repositories.context;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DbContext {

    private final EntityManager manager;

    // Instancia estática (única) del Singleton
    public static DbContext instance = null;

    private DbContext() {
        // Usar el nombre de la unidad de persistencia definido en persistence.xml
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("board-games-pu");
        manager = emf.createEntityManager();
    }

    public static DbContext getInstance() {
        if (instance == null) {
            instance = new DbContext();
        }
        return instance;
    }

    public EntityManager getManager() {
        return this.manager;
    }
}