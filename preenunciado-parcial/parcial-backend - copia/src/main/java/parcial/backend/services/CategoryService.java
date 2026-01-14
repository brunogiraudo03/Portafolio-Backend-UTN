package parcial.backend.services;

import java.util.List;
import java.util.stream.Stream;

import parcial.backend.services.interfaces.IService;
import parcial.backend.entities.Category;
import parcial.backend.repositories.CategoryRepository;

public class CategoryService implements IService<Category, Integer> {

    private final CategoryRepository repository;

    public CategoryService() {
        this.repository = new CategoryRepository();
    }

    @Override
    public Category getById(Integer id) {
        return repository.getById(id);
    }

    /**
     * Busca por nombre. Si no existe, crea una nueva categoría y la persiste.
     */
    @Override
    public Category getOrCreateByName(String name) {
        Category c = repository.getByName(name);
        if (c == null) {
            c = new Category(name); // Usamos el constructor sin ID de Lombok
            repository.create(c);
        }
        return c;
    }

    @Override
    public List<Category> getAll() {
        return repository.getAllList();
    }

    @Override
    public Stream<Category> getAllStream() {
        return repository.getAllStream();
    }
}