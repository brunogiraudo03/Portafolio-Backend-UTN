// Archivo: src/main/java/parcial/backend/repositories/BoardGameRepository.java
package parcial.backend.repositories;

import parcial.backend.entities.BoardGame;

// K=Integer para la clave primaria
public class BoardGameRepository extends Repository<BoardGame, Integer> {

    @Override
    protected Class<BoardGame> getEntityClass(){
        return BoardGame.class;
    }


}