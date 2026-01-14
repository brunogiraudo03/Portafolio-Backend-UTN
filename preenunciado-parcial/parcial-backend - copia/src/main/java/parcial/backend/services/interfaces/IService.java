package parcial.backend.services.interfaces;

import java.util.List;
import java.util.stream.Stream;

/**
 * Interfaz genérica para la capa de servicios.
 * Define operaciones comunes de búsqueda y listado.
 * T -> Entidad (Category, Designer, etc.)
 * K -> Tipo de la clave primaria (Integer)
 */
public interface IService<T, K> {

    /**
     * Busca una entidad por su ID.
     */
    T getById(K id);

    /**
     * Busca una entidad por su nombre, y si no existe la crea.
     * Útil para llenar tablas de catálogo (Designers, Publishers, Categories).
     */
    T getOrCreateByName(String name);

    /**
     * Listar todos los objetos de esta entidad como lista.
     */
    List<T> getAll();

    /**
     * Listar todos los objetos de esta entidad como Stream (para operaciones funcionales).
     */
    Stream<T> getAllStream();
}