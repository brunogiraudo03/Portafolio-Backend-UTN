// Archivo: src/main/java/parcial/backend/repositories/PublisherRepository.java
package parcial.backend.repositories;

import parcial.backend.entities.Publisher;

// K=Integer para la clave primaria
public class PublisherRepository extends Repository<Publisher, Integer> {

    @Override
    protected Class<Publisher> getEntityClass(){
        return Publisher.class;
    }
}