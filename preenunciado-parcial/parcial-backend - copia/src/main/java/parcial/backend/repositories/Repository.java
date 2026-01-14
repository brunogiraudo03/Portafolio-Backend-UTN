package parcial.backend.repositories;

import java.util.Set;
import java.util.List;
import java.util.stream.Stream;
import jakarta.persistence.TypedQuery;
import java.util.stream.Collectors;

import parcial.backend.repositories.context.DbContext; // Importar el paquete correcto
import jakarta.persistence.EntityManager;

public abstract class Repository<T, K> {

    protected EntityManager manager;

    public Repository() {
        manager = DbContext.getInstance().getManager();
    }

    // =========================
    // MÉTODOS CRUD BÁSICOS
    // =========================

    public void create(T entity) {
        var transaction = manager.getTransaction();
        transaction.begin();
        manager.persist(entity);
        transaction.commit();
    }

    public void update(T entity) {
        var transaction = manager.getTransaction();
        transaction.begin();
        manager.merge(entity);
        transaction.commit();
    }

    public T delete(K id) {
        var transaction = manager.getTransaction();
        transaction.begin();
        var entity = this.getById(id);
        manager.remove(entity);
        transaction.commit();
        return entity;
    }

    // =========================
    // MÉTODOS DE LECTURA GENÉRICOS
    // =========================

    public Set<T> getAllSet() {
        TypedQuery<T> query = manager.createQuery(
            "SELECT e FROM " + getEntityClass().getSimpleName() + " e", getEntityClass()
        );
        return query.getResultList().stream().collect(Collectors.toSet());
    }

    public List<T> getAllList() {
        TypedQuery<T> query = manager.createQuery(
            "SELECT e FROM " + getEntityClass().getSimpleName() + " e", getEntityClass()
        );
        return query.getResultList();
    }

    public Stream<T> getAllStream() {
        TypedQuery<T> query = manager.createQuery(
            "SELECT e FROM " + getEntityClass().getSimpleName() + " e", getEntityClass()
        );
        return query.getResultStream();
    }

    public T getById(K id) {
        return manager.find(getEntityClass(), id);
    }

    public T getByName(String name) {
        String jpql = "SELECT e FROM " + getEntityClass().getSimpleName() + " e WHERE e.name = :name"; // Nota: Usamos 'e.name'
        
        TypedQuery<T> query = manager.createQuery(jpql, getEntityClass());
        query.setParameter("name", name);
        List<T> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    // =========================
    // MÉTODO ABSTRACTO OBLIGATORIO
    // =========================

    protected abstract Class<T> getEntityClass();
}