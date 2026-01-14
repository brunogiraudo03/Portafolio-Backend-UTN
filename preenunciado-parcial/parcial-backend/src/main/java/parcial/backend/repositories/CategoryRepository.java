// Archivo: src/main/java/parcial/backend/repositories/CategoryRepository.java
package parcial.backend.repositories;

import parcial.backend.entities.Category;

// K=Integer para la clave primaria
public class CategoryRepository extends Repository<Category, Integer> {

    @Override
    protected Class<Category> getEntityClass(){
        return Category.class;
    }
}