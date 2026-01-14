package ar.edu.utnfrc.backend.repositories;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ar.edu.utnfrc.backend.entities.Puesto;

public class PuestoRepository extends Repository<Puesto, Integer> {
    public PuestoRepository() {
        super();
    }

    @Override
    public Puesto getById(Integer id) {
        return this.manager.find(Puesto.class, id);
    }

    @Override
    public Set<Puesto> getAll() {
        return this.manager.createQuery("SELECT p FROM Puesto p", Puesto.class)
                .getResultList()
                .stream().collect(Collectors.toSet());
    }

    @Override
    public Stream<Puesto> getAllStream() {
        return this.manager.createQuery("SELECT p FROM Puesto p", Puesto.class).getResultStream();
    }
}
