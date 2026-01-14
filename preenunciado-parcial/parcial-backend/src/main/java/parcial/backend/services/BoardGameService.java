package parcial.backend.services;

import java.util.List;
import java.util.stream.Stream;

import parcial.backend.services.interfaces.IService;
import parcial.backend.entities.BoardGame;
import parcial.backend.repositories.BoardGameRepository;

public class BoardGameService implements IService<BoardGame, Integer> {

    private final BoardGameRepository repository;

    public BoardGameService() {
        this.repository = new BoardGameRepository();
    }

    @Override
    public BoardGame getById(Integer id) {
        return repository.getById(id);
    }

    @Override
    public BoardGame getOrCreateByName(String name) {
        // En un escenario real de BoardGame, este método solo buscaría.
        // La creación se haría con datos completos en un método 'saveFromCSVRow()'.
        BoardGame bg = repository.getByName(name);
        if (bg == null) {
             System.err.println("Advertencia: Se intentó crear BoardGame solo con nombre. Se devolverá null.");
        }
        return bg;
    }

    @Override
    public List<BoardGame> getAll() {
        return repository.getAllList();
    }

    @Override
    public Stream<BoardGame> getAllStream() {
        return repository.getAllStream();
    }

}