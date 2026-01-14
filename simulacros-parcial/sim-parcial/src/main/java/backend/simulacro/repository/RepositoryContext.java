package backend.simulacro.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class RepositoryContext {
    
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("simulacro-pu");
    private static EntityManager em;

    private RepositoryContext() {
    }

    public static EntityManager getEntityManager() {
        if (em == null || !em.isOpen()) {
            em = emf.createEntityManager();
        }
        return em;
    }
}
