// Archivo: src/main/java/parcial/backend/repositories/DesignerRepository.java
package parcial.backend.repositories;

import parcial.backend.entities.Designer;

// K=Integer para la clave primaria
public class DesignerRepository extends Repository<Designer, Integer> {

    @Override
    protected Class<Designer> getEntityClass(){
        return Designer.class;
    }
}