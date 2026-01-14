package backend.simulacro.repository;

import backend.simulacro.entity.EjercicioPlan;

public class EjercicioPlanRepository extends BaseRepository<Long, EjercicioPlan> {

    public EjercicioPlanRepository() {
        super(EjercicioPlan.class);
    }
}
