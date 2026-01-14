package parcial.backend.services;

import java.util.List;
import java.util.stream.Stream;

import parcial.backend.services.interfaces.IService;
import parcial.backend.entities.Designer;
import parcial.backend.repositories.DesignerRepository;

public class DesignerService implements IService<Designer, Integer> {

    private final DesignerRepository repository;

    public DesignerService() {
        this.repository = new DesignerRepository();
    }

    @Override
    public Designer getById(Integer id) {
        return repository.getById(id);
    }

    /**
     * Busca por nombre. Si no existe, crea un nuevo diseñador y lo persiste.
     */
    @Override
    public Designer getOrCreateByName(String name) {
        Designer d = repository.getByName(name);
        if (d == null) {
            d = new Designer(name);
            repository.create(d);
        }
        return d;
    }

    @Override
    public List<Designer> getAll() {
        return repository.getAllList();
    }

    @Override
    public Stream<Designer> getAllStream() {
        return repository.getAllStream();
    }
}