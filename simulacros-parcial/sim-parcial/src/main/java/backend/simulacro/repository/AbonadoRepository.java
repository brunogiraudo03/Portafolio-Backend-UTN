package backend.simulacro.repository;

import backend.simulacro.entity.Abonado;

public class AbonadoRepository extends BaseRepository<Long, Abonado> {
    
    public AbonadoRepository() {
        super(Abonado.class);
    }

    
}
