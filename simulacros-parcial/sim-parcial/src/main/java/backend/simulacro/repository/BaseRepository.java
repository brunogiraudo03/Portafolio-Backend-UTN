package backend.simulacro.repository;

import java.util.List;

import backend.simulacro.entity.ModelEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class BaseRepository<K, V extends ModelEntity> implements Repository<K, V> {
    
    private static EntityManager em = RepositoryContext.getEntityManager();
    private Class<V> entityClass;

    protected BaseRepository(Class<V> entitiyClass) {
        this.entityClass = entitiyClass;
    }

    @Override
    public void save(V entity) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            if (entity.getId() == null) {
                em.persist(entity);
            } else {
                em.merge(entity);
            }
            em.getTransaction()
            .commit();
        } catch (Exception e) {
            System.out.println("Error al guardar el abonado: " + e.getMessage());
            transaction.rollback();
        }
    }

    @Override
    public V findById(K id) {
        return em.find(entityClass, id);
    }

    @Override
    public List<V> findAll() {
        String query = "SELECT e from " + entityClass.getSimpleName() + " e";
        return em.createQuery(query, entityClass)
        .getResultList();
    }
    
}
