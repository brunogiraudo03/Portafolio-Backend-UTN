package parcial.backend.services;

import java.util.List;
import java.util.stream.Stream;

import parcial.backend.services.interfaces.IService;
import parcial.backend.entities.Publisher;
import parcial.backend.repositories.PublisherRepository;

public class PublisherService implements IService<Publisher, Integer> {

    private final PublisherRepository repository;

    public PublisherService() {
        this.repository = new PublisherRepository();
    }

    @Override
    public Publisher getById(Integer id) {
        return repository.getById(id);
    }

    /**
     * Busca por nombre. Si no existe, crea un nuevo publisher y lo persiste.
     */
    @Override
    public Publisher getOrCreateByName(String name) {
        Publisher p = repository.getByName(name);
        if (p == null) {
            p = new Publisher(name);
            repository.create(p);
        }
        return p;
    }

    @Override
    public List<Publisher> getAll() {
        return repository.getAllList();
    }

    @Override
    public Stream<Publisher> getAllStream() {
        return repository.getAllStream();
    }
}