package backend.simulacro.service;

public interface Service <K, V> {
    V findOrCreate(K id, String[] args);
    V create(String[] args);
}
