package backend.simulacro.repository;

import java.util.List;

public interface Repository<K, V> {
    void save(V entity);
    V findById(K id);
    List<V> findAll();
}
